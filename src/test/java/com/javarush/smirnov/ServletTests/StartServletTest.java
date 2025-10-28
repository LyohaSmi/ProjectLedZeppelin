package com.javarush.smirnov.ServletTests;

import com.javarush.smirnov.servlets.StartServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class StartServletTest {

    private StartServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        servlet = new StartServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    void testDoGet_ShouldClearSessionAndForwardToStartPage() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/start.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(session).removeAttribute("currentSceneId");
        verify(session).removeAttribute("playerName");
        verify(session).removeAttribute("gameStats");
        verify(requestDispatcher).forward(request, response);
    }
}