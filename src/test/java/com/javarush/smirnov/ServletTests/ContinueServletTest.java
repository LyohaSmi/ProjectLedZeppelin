package com.javarush.smirnov.ServletTests;

import com.javarush.smirnov.servlets.ContinueServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ContinueServletTest {

    private ContinueServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        servlet = new ContinueServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    void testDoGet_WhenPlayerNameExists_ShouldRedirectToGame() throws Exception {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn("TestPlayer");

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(session).setAttribute("currentSceneId", 1);
        verify(response).sendRedirect("game");
    }

    @Test
    void testDoGet_WhenPlayerNameNull_ShouldRedirectToStart() throws Exception {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn(null);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(response).sendRedirect("start");
        verify(session, never()).setAttribute(anyString(), any());
    }
}