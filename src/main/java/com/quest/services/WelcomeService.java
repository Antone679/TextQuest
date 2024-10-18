package com.quest.services;

import com.quest.entity.Unit;
import com.quest.repository.UnitRepository;
import com.quest.utils.IdGenerator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WelcomeService implements ServletService {

    @Override
    public void setAllAttributes(HttpSession session, List<String> answers, Map<Integer, Unit> questions, Integer counter, String failureDescription) {
        session.setAttribute("answers", answers);
        session.setAttribute("questions", questions);
        session.setAttribute("counter", counter);
        session.setAttribute("gameWon", false);
        session.setAttribute("failure", failureDescription);
    }

    @Override
    public List<String> getAnswers(Unit unit) {
        List<String> answers = new ArrayList<>();
        answers.add(unit.getCorrectAnswer());
        answers.add(unit.getWrongAnswer());
        Collections.shuffle(answers);
        return answers;
    }

    @Override
    public void redirectIfWin(HttpServletResponse resp, HttpSession session) throws IOException {
        session.setAttribute("gameWon", true);
        resp.sendRedirect("index.jsp");
    }

    @Override
    public void incrementCorrectAnswers(HttpSession session) {
        Integer correctAnswers = (Integer) session.getAttribute("correctAnswers");
        session.setAttribute("correctAnswers", (correctAnswers == null ? 1 : correctAnswers + 1));
    }

    @Override
    public Integer processCounter(HttpSession session) {
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
        } else {
            counter++;
        }
        return counter;
    }

    @Override
    public Map<Integer, Unit> initQuestions(ServletContext context, HttpSession session) {
        String path = context.getRealPath("/WEB-INF/resources/questions.txt");
        UnitRepository.setFileWithQuestions(Path.of(path));
        Map<Integer, Unit> questions = (Map<Integer, Unit>) session.getAttribute("questions");
        questions = (questions == null) ? new UnitRepository().getQuestions() : questions;
        return questions;
    }

    public void setUsernameIfNull(HttpServletRequest req, HttpSession session) {
        if (session.getAttribute("username") == null) {
            String username = req.getParameter("username");
            IdGenerator.setGeneratorId(0);
            session.setAttribute("username", username);
            session.setAttribute("counter", null);
        }
    }

    @Override
    public boolean checkIfAnswerIsIncorrect(HttpServletResponse resp, Boolean isCorrect) throws IOException {
        if (isCorrect != null && !isCorrect) {
            resp.sendRedirect("index.jsp");
            return true;
        }
        return false;
    }

    public void maintainIpAddress(HttpServletRequest req, HttpSession session) {
        String ipAddress = req.getRemoteAddr();
        session.setAttribute("ipAddress", ipAddress);
    }
}
