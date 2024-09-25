package com.quest.servlets;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResetServletTest {

    private ResetServlet resetServlet;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resetServlet = new ResetServlet();
    }

    @Test
    public void testDoPostWithNullTimesPlayed() throws IOException, ServletException {
        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("timesPlayed")).thenReturn(null);

        resetServlet.doPost(req, resp);

        verify(session).setAttribute("timesPlayed", 1);
        verify(resp).sendRedirect("/welcome");
    }

    @Test
    public void testDoPostWithExistingTimesPlayed() throws IOException, ServletException {
        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("timesPlayed")).thenReturn(2);

        resetServlet.doPost(req, resp);

        verify(session).setAttribute("timesPlayed", 3);
        verify(resp).sendRedirect("/welcome");
    }

    @Test
    public void testSetAllAttributes() {
        when(req.getSession(false)).thenReturn(session);

        resetServlet.setAllAttributes(session, 1);

        verify(session).removeAttribute("answers");
        verify(session).removeAttribute("questions");
        verify(session).removeAttribute("counter");
        verify(session).removeAttribute("gameWon");
        verify(session).removeAttribute("failure");
        verify(session).removeAttribute("correctAnswers");
        verify(session).removeAttribute("isCorrect");
        verify(session).setAttribute("timesPlayed", 1);
    }

    @Test
    public void testGetTimesPlayedWithNull() {
        Integer result = resetServlet.getTimesPlayed(null);
        assertEquals(1, result);
    }

    @Test
    public void testGetTimesPlayedWithExistingValue() {
        Integer result = resetServlet.getTimesPlayed(2);
        assertEquals(3, result);
    }
}