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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String playerName = (String) session.getAttribute("playerName");
        if (playerName == null) {
            response.sendRedirect("start");
            return;
        }

        session.setAttribute("currentSceneId", 1);
        response.sendRedirect("game");
    }
}
