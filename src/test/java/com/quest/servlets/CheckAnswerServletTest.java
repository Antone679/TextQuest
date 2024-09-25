package com.quest.servlets;

import com.quest.entity.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckAnswerServletTest {

    private CheckAnswerServlet checkAnswerServlet;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        checkAnswerServlet = new CheckAnswerServlet();
    }

    @Test
    public void testIfSessionIsNull() throws IOException, ServletException {
        when(req.getSession(false)).thenReturn(null);
        checkAnswerServlet.doPost(req, resp);

        verify(resp).sendRedirect("index.jsp");
    }

    @Test
    public void testRedirectToWelcomeIfAnswerIsCorrect() throws IOException, ServletException {
        Map<Integer, Unit> questions = new HashMap<>();
        questions.put(0, new Unit("Вы оказались на необитаемом острове после кораблекрушения. Что вы сделаете в первую очередь?",
                "Осмотреться и найти укрытие.", "Поискать других выживших.",
                "Вы начали звать других, но никто не ответил. Вскоре вы устали и не смогли найти укрытие. Вы проиграли."));

    when(req.getSession(false)).thenReturn(session);
    when(session.getAttribute("questions")).thenReturn(questions);
    when(req.getParameter("questionId")).thenReturn("0");
    when(req.getParameter("answer")).thenReturn("Осмотреться и найти укрытие.");

    checkAnswerServlet.doPost(req, resp);

    verify(session).setAttribute("isCorrect", true);
    verify(resp).sendRedirect("/welcome");
    }

}