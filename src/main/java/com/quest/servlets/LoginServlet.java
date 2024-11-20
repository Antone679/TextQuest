package com.quest.servlets;

import com.quest.repository.UserRepository;
import com.quest.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService service = new UserService(new UserRepository());


        if (checkLogin(resp, service, username, session)) return;

        checkPassword(resp, service, password, session);
    }

     static void checkPassword(HttpServletResponse resp, UserService service, String password, HttpSession session) throws IOException {
        if (service.checkPassword(password)){
            session.setAttribute("auth", true);
            resp.sendRedirect("/welcome");
        }
        else {
            session.setAttribute("auth", false);
            resp.sendRedirect("/login.jsp");
        }
    }

    static boolean checkLogin(HttpServletResponse resp, UserService service, String name, HttpSession session) throws IOException {
        if (!service.checkLogin(name)) {
            session.setAttribute("auth", false);
            resp.sendRedirect("/login.jsp");
            return true;
        }
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
