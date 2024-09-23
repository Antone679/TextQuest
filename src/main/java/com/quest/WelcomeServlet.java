package com.quest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(true);
        Boolean isCorrect = (Boolean) session.getAttribute("isCorrect");

        System.out.println("isCorrect: " + isCorrect);
        System.out.println("Counter: " + session.getAttribute("counter"));

        if (isCorrect != null && !isCorrect) {
            resp.sendRedirect("gameover.jsp");
            System.out.println("isCorrect: " + isCorrect);
            System.out.println("Counter: " + session.getAttribute("counter"));
            return;
        }

        ServletContext context = getServletContext();

        if (session.getAttribute("username") == null) {
            String username = req.getParameter("username");
            session.setAttribute("username", username);
        }

        String path = context.getRealPath("/WEB-INF/resources/questions.txt");
        UnitRepository.setFileWithQuestions(Path.of(path));
        Map<Integer, Unit> questions = (Map<Integer, Unit>) session.getAttribute("questions");
        questions = (questions == null) ? new UnitRepository().getQuestions() : questions;

        Integer counter = (Integer) session.getAttribute("counter");
        counter = (counter == null) ? 0 : counter + 1;

        Unit unit = questions.get(counter);

        if (unit == null) {
            resp.sendRedirect("gameover.jsp");
            return;
        }

        List<String> answers = new ArrayList<>();
        answers.add(unit.getCorrectAnswer());
        answers.add(unit.getWrongAnswer());
        Collections.shuffle(answers);

        session.setAttribute("answers", answers);
        session.setAttribute("questions", questions);
        session.setAttribute("counter", counter);

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
