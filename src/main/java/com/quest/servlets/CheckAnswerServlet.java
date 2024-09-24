package com.quest.servlets;

import com.quest.entity.Unit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/answer")
public class CheckAnswerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        Boolean isCorrect = getCorrect(req, session);
        session.setAttribute("isCorrect", isCorrect);

        resp.sendRedirect("/welcome");
    }

    private static Boolean getCorrect(HttpServletRequest req, HttpSession session) {
        String questionId = req.getParameter("questionId");
        String playerAnswer = req.getParameter("answer");

        Map<Integer, Unit> questions = (Map<Integer, Unit>) session.getAttribute("questions");
        Unit currentQuestion = questions.get(Integer.parseInt(questionId));

        return playerAnswer.trim().equalsIgnoreCase(currentQuestion.getCorrectAnswer().trim());
    }
}
