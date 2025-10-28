package com.javarush.smirnov.ServletTests;

import com.javarush.smirnov.servlets.RestartServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RestartServletTest {

    private RestartServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        servlet = new RestartServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    void testDoPost_ShouldRemoveCurrentSceneAndRedirect() throws Exception {
        // Arrange
        when(request.getSession()).thenReturn(session);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).removeAttribute("currentSceneId");
        verify(response).sendRedirect("game");
    }
}