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

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService service = new UserService(new UserRepository());

        if (service.checkLogin(name)) {
            session.setAttribute("existLogin", true);
            resp.sendRedirect("/registration.jsp");
            return;
        }

        // Проверка пароля
        if (service.checkPassword(password)) {
            session.setAttribute("existPassword", true);
            resp.sendRedirect("/registration.jsp");
            return;
        }

        // Если логин и пароль валидны, сохраняем пользователя
        service.saveUser(name, password, email);
        session.invalidate();
        resp.sendRedirect("/login.jsp");

    }
}
