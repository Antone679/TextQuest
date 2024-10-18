package com.quest.servlets;

import com.quest.controller.WelcomeController;
import com.quest.services.WelcomeService;
import com.quest.entity.Unit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        WelcomeController welcomeController = new WelcomeController();

        //устанавливаем IP в атрибуты
        welcomeController.maintainIpAddress(req, session);

        ServletContext context = getServletContext();
        Boolean isCorrect = (Boolean) session.getAttribute("isCorrect");

        //проверяем, правильный ли ответ
        if (welcomeController.checkIfAnswerIsIncorrect(resp, isCorrect)) return;

        //устанавливаем имя, если null
        welcomeController.setUsernameIfNull(req, session);

        //инициализируем вопросы
        Map<Integer, Unit> questions = welcomeController.initQuestions(context, session);

        //проверка и инкремент счетчика вопросов
        Integer counter = welcomeController.processCounter(session);

        //работа со счетчиком и логикой количества правильных ответов
        Integer correctAnswers;
        if (isCorrect != null && isCorrect) {
            welcomeController.incrementCorrectAnswers(session);
        }
        correctAnswers = (Integer) session.getAttribute("correctAnswers");
        if (correctAnswers != null && correctAnswers >= questions.size()) {
            welcomeController.redirectIfWin(resp, session);
            return;
        }
        //создаем коллекцию ответов и перемешиваем
        Unit unit = questions.get(counter);
        final List<String> answers = welcomeController.getAnswers(unit);

        String failureDescription = unit.getFailureDescription();
        welcomeController.setAllAttributes(session, answers, questions, counter, failureDescription);

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
