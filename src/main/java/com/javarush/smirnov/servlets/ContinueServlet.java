package com.javarush.smirnov.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/continue")
public class ContinueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Проверяем, есть ли сохраненное имя
        String playerName = (String) session.getAttribute("playerName");
        if (playerName == null) {
            // Если имени нет, идем на страницу ввода имени
            response.sendRedirect("start");
            return;
        }

        // Начинаем игру с сохраненным именем
        session.setAttribute("currentSceneId", 1);
        response.sendRedirect("game");
    }
}
