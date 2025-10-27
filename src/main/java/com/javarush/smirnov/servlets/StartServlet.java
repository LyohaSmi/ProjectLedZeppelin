package com.javarush.smirnov.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Очищаем прогресс при начале новой игры
        session.removeAttribute("currentSceneId");
        session.removeAttribute("playerName");
        session.removeAttribute("gameStats");

        request.getRequestDispatcher("/start.jsp").forward(request, response);
    }
}