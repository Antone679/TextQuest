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

        String ipAddress = req.getRemoteAddr();
        session.setAttribute("ipAddress", ipAddress);

        Boolean isCorrect = (Boolean) session.getAttribute("isCorrect");

        if (isCorrect != null && !isCorrect) {
            resp.sendRedirect("index.jsp");
            return;
        }

        ServletContext context = getServletContext();

        if (session.getAttribute("username") == null) {
            String username = req.getParameter("username");
            IdGenerator.setGeneratorId(0);
            session.setAttribute("username", username);
            session.setAttribute("counter", null);

        }

        String path = context.getRealPath("/WEB-INF/resources/questions.txt");
        UnitRepository.setFileWithQuestions(Path.of(path));
        Map<Integer, Unit> questions = (Map<Integer, Unit>) session.getAttribute("questions");
        questions = (questions == null) ? new UnitRepository().getQuestions() : questions;

        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
        } else {
            counter++;
        }

        Integer correctAnswers;
        if (isCorrect != null && isCorrect) {
            correctAnswers = (Integer) session.getAttribute("correctAnswers");
            session.setAttribute("correctAnswers", (correctAnswers == null ? 1 : correctAnswers + 1));
        }

        correctAnswers = (Integer) session.getAttribute("correctAnswers");
        if (correctAnswers != null && correctAnswers >= 10) {

            session.setAttribute("gameWon", true);
            resp.sendRedirect("index.jsp");
            return;
        }
        System.out.println(correctAnswers);
        Unit unit = questions.get(counter);

        System.out.println(counter);

        // Если вопрос не null, продолжаем
        List<String> answers = new ArrayList<>();
        answers.add(unit.getCorrectAnswer());
        answers.add(unit.getWrongAnswer());
        Collections.shuffle(answers);

        System.out.println(session.getAttribute("username"));

        String description = unit.getFailureDescription();

        session.setAttribute("answers", answers);
        session.setAttribute("questions", questions);
        session.setAttribute("counter", counter);
        session.setAttribute("gameWon", false);
        session.setAttribute("failure", description);


        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
