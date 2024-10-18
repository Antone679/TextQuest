package com.quest.controller;

import com.quest.entity.Unit;
import com.quest.services.WelcomeService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WelcomeController {
    private WelcomeService welcomeService;

    public WelcomeController() {
        this.welcomeService = new WelcomeService();
    }

    public WelcomeService getWelcomeService() {
        return welcomeService;
    }

    public void setWelcomeService(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    public void setAllAttributes(HttpSession session, List<String> answers, Map<Integer, Unit> questions, Integer counter, String failureDescription) {
        welcomeService.setAllAttributes(session, answers, questions, counter, failureDescription);
    }

    public List<String> getAnswers(Unit unit) {
        return welcomeService.getAnswers(unit);
    }

    public void redirectIfWin(HttpServletResponse resp, HttpSession session) throws IOException {
        welcomeService.redirectIfWin(resp, session);
    }

    public void incrementCorrectAnswers(HttpSession session) {
        welcomeService.incrementCorrectAnswers(session);
    }

    public Integer processCounter(HttpSession session) {
        return welcomeService.processCounter(session);
    }

    public Map<Integer, Unit> initQuestions(ServletContext context, HttpSession session) {
        return welcomeService.initQuestions(context, session);
    }

    public void setUsernameIfNull(HttpServletRequest req, HttpSession session) {
       welcomeService.setUsernameIfNull(req, session);
    }

    public boolean checkIfAnswerIsIncorrect(HttpServletResponse resp, Boolean isCorrect) throws IOException {
        return welcomeService.checkIfAnswerIsIncorrect(resp, isCorrect);
    }

    public void maintainIpAddress(HttpServletRequest req, HttpSession session) {
        welcomeService.maintainIpAddress(req, session);
    }
}
