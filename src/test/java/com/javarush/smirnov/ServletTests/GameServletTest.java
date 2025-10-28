package com.javarush.smirnov.ServletTests;

import com.javarush.smirnov.Scene;
import com.javarush.smirnov.servlets.GameServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServletTest {

    private GameServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        servlet = new GameServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGet_WithNewPlayerName_ShouldSaveNameAndClearStats() throws Exception {
        String newPlayerName = "NewPlayer";
        when(request.getParameter("playerName")).thenReturn(newPlayerName);
        when(session.getAttribute("currentSceneId")).thenReturn(1);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(session).setAttribute("playerName", newPlayerName.trim());
        verify(session).removeAttribute("gameStats");
        verify(request).setAttribute(eq("scene"), any(Scene.class));
        verify(request).setAttribute("playerName", newPlayerName);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGet_WithExistingPlayerName_ShouldUseSessionName() throws Exception {
        String existingPlayerName = "ExistingPlayer";
        when(request.getParameter("playerName")).thenReturn(null);
        when(session.getAttribute("playerName")).thenReturn(existingPlayerName);
        when(session.getAttribute("currentSceneId")).thenReturn(1);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(session, never()).setAttribute(eq("playerName"), any());
        verify(session, never()).removeAttribute("gameStats");
        verify(request).setAttribute("playerName", existingPlayerName);
        verify(request).setAttribute(eq("scene"), any(Scene.class));
    }

    @Test
    void testDoGet_WithoutPlayerName_ShouldRedirectToStart() throws Exception {
        when(request.getParameter("playerName")).thenReturn(null);
        when(session.getAttribute("playerName")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response).sendRedirect("start");
        verify(request, never()).getRequestDispatcher(anyString());
    }



    @Test
    void testDoGet_WithEmptyPlayerName_ShouldRedirectToStart() throws Exception {

        when(request.getParameter("playerName")).thenReturn("   ");
        when(session.getAttribute("playerName")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response).sendRedirect("start");
    }

    @Test
    void testDoPost_WithValidOption_ShouldUpdateScene() throws Exception {
        when(request.getParameter("selectedOption")).thenReturn("0");
        when(session.getAttribute("currentSceneId")).thenReturn(1);

        servlet.doPost(request, response);

        verify(session).setAttribute("currentSceneId", 2);
        verify(response).sendRedirect("game");
    }

    @Test
    void testDoPost_WithGameOverWin_ShouldUpdateStats() throws Exception {
        when(request.getParameter("selectedOption")).thenReturn("0");
        when(session.getAttribute("currentSceneId")).thenReturn(7);

        Map<String, Integer> existingStats = new HashMap<>();
        existingStats.put("totalGames", 2);
        existingStats.put("wins", 1);
        existingStats.put("losses", 1);
        when(session.getAttribute("gameStats")).thenReturn(existingStats);

        servlet.doPost(request, response);

        verify(session).setAttribute(eq("gameStats"), any(Map.class));
        verify(response).sendRedirect("game");
    }

    @Test
    void testDoPost_WithGameOverLose_ShouldUpdateStats() throws Exception {
        when(request.getParameter("selectedOption")).thenReturn("0");
        when(session.getAttribute("currentSceneId")).thenReturn(4);

        Map<String, Integer> existingStats = new HashMap<>();
        existingStats.put("totalGames", 2);
        existingStats.put("wins", 1);
        existingStats.put("losses", 1);
        when(session.getAttribute("gameStats")).thenReturn(existingStats);

        servlet.doPost(request, response);

        verify(session).setAttribute(eq("gameStats"), any(Map.class));
    }

    @Test
    void testDoPost_WithNullCurrentScene_ShouldUseDefaultScene() throws Exception {

        when(request.getParameter("selectedOption")).thenReturn("0");
        when(session.getAttribute("currentSceneId")).thenReturn(null);

        servlet.doPost(request, response);

        verify(session).setAttribute(eq("currentSceneId"), anyInt());
        verify(response).sendRedirect("game");
    }

    @Test
    void testDoPost_WithNullSelectedOption_ShouldRedirectWithoutChanges() throws Exception {

        when(request.getParameter("selectedOption")).thenReturn(null);

        servlet.doPost(request, response);

        verify(session, never()).setAttribute(eq("currentSceneId"), anyInt());
        verify(response).sendRedirect("game");
    }

    @Test
    void testDoPost_WithNewStats_ShouldInitializeStats() throws Exception {

        when(request.getParameter("selectedOption")).thenReturn("0");
        when(session.getAttribute("currentSceneId")).thenReturn(7);
        when(session.getAttribute("gameStats")).thenReturn(null);

        servlet.doPost(request, response);

        verify(session).setAttribute(eq("gameStats"), any(Map.class));
    }
}