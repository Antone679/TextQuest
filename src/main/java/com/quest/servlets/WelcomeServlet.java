package com.quest.servlets;

import com.quest.utils.IdGenerator;
import com.quest.entity.Unit;
import com.quest.repository.UnitRepository;

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
        HttpSession session = req.getSession(true);

        //устанавливаем IP в атрибуты
        maintainIpAddress(req, session);

        ServletContext context = getServletContext();
        Boolean isCorrect = (Boolean) session.getAttribute("isCorrect");

        //проверяем, правильный ли ответ
        if (checkIfAnswerIsCorrect(resp, isCorrect)) return;

        //устанавливаем имя, если null
        setUsernameIfNull(req, session);

        //инициализируем вопросы
        Map<Integer, Unit> questions = initQuestions(context, session);

        //проверка и инкремент счетчика вопросов
        Integer counter = processCounter(session);

        //работа со счетчиком и логикой количества правильных ответов
        Integer correctAnswers;
        if (isCorrect != null && isCorrect) {
            incrementCorrectAnswers(session);
        }
        correctAnswers = (Integer) session.getAttribute("correctAnswers");
        if (correctAnswers != null && correctAnswers >= 10) {
            redirectIfWin(resp, session);
            return;
        }
        //создаем коллекцию ответов и перемешиваем
        Unit unit = questions.get(counter);
        final List<String> answers = getAnswers(unit);

        String failureDescription = unit.getFailureDescription();
        setAllAttributes(session, answers, questions, counter, failureDescription);

        resp.sendRedirect("index.jsp");
    }

    private static void setAllAttributes(HttpSession session, List<String> answers, Map<Integer, Unit> questions, Integer counter, String failureDescription) {
        session.setAttribute("answers", answers);
        session.setAttribute("questions", questions);
        session.setAttribute("counter", counter);
        session.setAttribute("gameWon", false);
        session.setAttribute("failure", failureDescription);
    }

    private static List<String> getAnswers(Unit unit) {
        List<String> answers = new ArrayList<>();
        answers.add(unit.getCorrectAnswer());
        answers.add(unit.getWrongAnswer());
        Collections.shuffle(answers);
        return answers;
    }

    private static void redirectIfWin(HttpServletResponse resp, HttpSession session) throws IOException {
        session.setAttribute("gameWon", true);
        resp.sendRedirect("index.jsp");
    }

    private static void incrementCorrectAnswers(HttpSession session) {
        Integer correctAnswers = (Integer) session.getAttribute("correctAnswers");
        session.setAttribute("correctAnswers", (correctAnswers == null ? 1 : correctAnswers + 1));
    }

    private static Integer processCounter(HttpSession session) {
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
        } else {
            counter++;
        }
        return counter;
    }

    private static Map<Integer, Unit> initQuestions(ServletContext context, HttpSession session) {
        String path = context.getRealPath("/WEB-INF/resources/questions.txt");
        UnitRepository.setFileWithQuestions(Path.of(path));
        Map<Integer, Unit> questions = (Map<Integer, Unit>) session.getAttribute("questions");
        questions = (questions == null) ? new UnitRepository().getQuestions() : questions;
        return questions;
    }

    private static void setUsernameIfNull(HttpServletRequest req, HttpSession session) {
        if (session.getAttribute("username") == null) {
            String username = req.getParameter("username");
            IdGenerator.setGeneratorId(0);
            session.setAttribute("username", username);
            session.setAttribute("counter", null);
        }
    }

    private static boolean checkIfAnswerIsCorrect(HttpServletResponse resp, Boolean isCorrect) throws IOException {
        if (isCorrect != null && !isCorrect) {
            resp.sendRedirect("index.jsp");
            return true;
        }
        return false;
    }

    private static void maintainIpAddress(HttpServletRequest req, HttpSession session) {
        String ipAddress = req.getRemoteAddr();
        session.setAttribute("ipAddress", ipAddress);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
