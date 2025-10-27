package com.javarush.smirnov.servlets;


import com.javarush.smirnov.Data;
import com.javarush.smirnov.Option;
import com.javarush.smirnov.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Получаем имя игрока из параметра (новое имя) или из сессии (текущее имя)
        String playerName = request.getParameter("playerName");
        if (playerName != null && !playerName.trim().isEmpty()) {
            // Если пришло новое имя, сохраняем его и очищаем статистику
            session.setAttribute("playerName", playerName.trim());
            session.removeAttribute("gameStats"); // Новая статистика для нового игрока
        } else {
            // Иначе берем имя из сессии
            playerName = (String) session.getAttribute("playerName");
        }

        // Если имени все еще нет, перенаправляем на ввод имени
        if (playerName == null) {
            response.sendRedirect("start");
            return;
        }

        // Получаем текущую сцену
        Integer currentSceneId = (Integer) session.getAttribute("currentSceneId");
        if (currentSceneId == null) {
            currentSceneId = 1;
        }

        Scene scene = Data.get(currentSceneId);
        request.setAttribute("scene", scene);
        request.setAttribute("playerName", playerName);

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Обрабатываем выбор игрока
        String selectedOption = request.getParameter("selectedOption");
        if (selectedOption != null) {
            int optionIndex = Integer.parseInt(selectedOption);

            // Получаем текущую сцену
            Integer currentSceneId = (Integer) session.getAttribute("currentSceneId");
            if (currentSceneId == null) {
                currentSceneId = 1;
            }

            Scene currentScene = Data.get(currentSceneId);

            // Получаем выбранный вариант
            Option chosenOption = currentScene.getOptions().get(optionIndex);

            // Переходим к следующей сцене
            session.setAttribute("currentSceneId", chosenOption.getNextSceneId());

            // Получаем новую сцену для проверки на gameOver
            Scene newScene = Data.get(chosenOption.getNextSceneId());

            // Обновляем статистику если игра окончена
            if (newScene.isGameOver()) {
                // Получаем или создаем статистику
                Map<String, Integer> stats = (Map<String, Integer>) session.getAttribute("gameStats");
                if (stats == null) {
                    stats = new HashMap<>();
                    stats.put("totalGames", 0);
                    stats.put("wins", 0);
                    stats.put("losses", 0);
                }

                // Обновляем статистику
                stats.put("totalGames", stats.get("totalGames") + 1);
                if ("win".equals(newScene.getEndType())) {
                    stats.put("wins", stats.get("wins") + 1);
                } else if ("lose".equals(newScene.getEndType())) {
                    stats.put("losses", stats.get("losses") + 1);
                }

                session.setAttribute("gameStats", stats);
            }
        }

        response.sendRedirect("game");
    }
}
