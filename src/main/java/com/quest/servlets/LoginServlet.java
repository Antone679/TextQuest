package com.quest.servlets;

import com.quest.repository.PasswordRepository;
import com.quest.utils.HashPassword;

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

        PasswordRepository repository = new PasswordRepository();
        if (checkLogin(resp, repository, username, session)) return;

        String hashPassword = HashPassword.hashPassword(password);

        checkPassword(resp, repository, username, hashPassword, session);
    }

     static void checkPassword(HttpServletResponse resp, PasswordRepository repository, String username, String hashPassword, HttpSession session) throws IOException {
        if (repository.getUsers().get(username).equals(hashPassword)){
            session.setAttribute("auth", true);
            resp.sendRedirect("/welcome");
        }
        else {
            session.setAttribute("auth", false);
            resp.sendRedirect("/login.jsp");
        }
    }

     static boolean checkLogin(HttpServletResponse resp, PasswordRepository repository, String username, HttpSession session) throws IOException {
        if (!repository.getUsers().containsKey(username)){

            session.setAttribute("auth", false);
            resp.sendRedirect("/login.jsp");
            return true;
        }
        return false;
    }
}
