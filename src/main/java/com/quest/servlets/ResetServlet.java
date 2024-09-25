package com.quest.servlets;

import com.quest.utils.IdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        Integer timesPlayed = (Integer) session.getAttribute("timesPlayed");
        timesPlayed = getTimesPlayed(timesPlayed);
        setAllAttributes(session, timesPlayed);

        resp.sendRedirect("/welcome");
    }

    static void setAllAttributes(HttpSession session, Integer timesPlayed) {
        session.removeAttribute("answers");
        session.removeAttribute("questions");
        session.removeAttribute("counter");
        session.removeAttribute("gameWon");
        session.removeAttribute("failure");
        session.removeAttribute("correctAnswers");
        session.removeAttribute("isCorrect");
        session.setAttribute("timesPlayed", timesPlayed);
        IdGenerator.setGeneratorId(0);
    }

    static Integer getTimesPlayed(Integer timesPlayed) {
        if (timesPlayed == null){
            timesPlayed = 1;
        } else {
            timesPlayed++;
        }
        return timesPlayed;
    }
}
