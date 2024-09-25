package com.quest.servlets;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.quest.entity.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WelcomeServletTest {
    private WelcomeServlet welcomeServlet;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext context;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        welcomeServlet = new WelcomeServlet();
    }

    @Test
    public void testDoPostWithCorrectAnswer() throws IOException, ServletException {
        when(req.getSession(true)).thenReturn(session);
        when(req.getServletContext()).thenReturn(context);
        when(session.getAttribute("isCorrect")).thenReturn(true);

        boolean result = welcomeServlet.checkIfAnswerIsIncorrect(resp, true);

        assertFalse(result);
    }

    @Test
    public void testDoPostWithIncorrectAnswer() throws IOException {
        when(req.getSession(true)).thenReturn(session);
        when(req.getServletContext()).thenReturn(context);
        when(session.getAttribute("isCorrect")).thenReturn(false);

        boolean result = welcomeServlet.checkIfAnswerIsIncorrect(resp, false);

        assertTrue(result);
        verify(resp).sendRedirect("index.jsp");
    }

    @Test
    public void testCheckIfAnswerIsIncorrectWithIncorrectAnswer() throws IOException {
        boolean result = welcomeServlet.checkIfAnswerIsIncorrect(resp, false);

        assertTrue(result);
        verify(resp).sendRedirect("index.jsp");
    }

    @Test
    public void testCheckIfAnswerIsIncorrectWithCorrectAnswer() throws IOException {
        boolean result = welcomeServlet.checkIfAnswerIsIncorrect(resp, true);

        assertFalse(result);
        verify(resp, never()).sendRedirect("index.jsp");
    }

    @Test
    public void testInitQuestionsNewSession() {
        when(session.getAttribute("questions")).thenReturn(null);
        when(context.getRealPath("/WEB-INF/resources/questions.txt")).thenReturn("src/test/resources/questions.txt");

        Map<Integer, Unit> questions = WelcomeServlet.initQuestions(context, session);

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }

    @Test
    public void testSetUsernameIfNull() {
        when(session.getAttribute("username")).thenReturn(null);
        when(req.getParameter("username")).thenReturn("TestUser");

        WelcomeServlet.setUsernameIfNull(req, session);

        verify(session).setAttribute("username", "TestUser");
        verify(session).setAttribute("counter", null);
    }

    @Test
    public void testMaintainIpAddress() {
        when(req.getRemoteAddr()).thenReturn("192.168.1.1");
        when(req.getSession(true)).thenReturn(session);

        welcomeServlet.maintainIpAddress(req, session);

        verify(session).setAttribute("ipAddress", "192.168.1.1");
    }

}