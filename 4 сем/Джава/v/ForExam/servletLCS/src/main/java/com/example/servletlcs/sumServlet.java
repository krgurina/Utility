package com.example.servletlcs;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "InServlet", value = "/in-servlet")
public class sumServlet extends HttpServlet {
    private DB db;
    public List<User> areas = new ArrayList<>();


    public void init() {
        try {
            this.db = new DB();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        boolean isFoundUser = false;
        boolean isWrongPassword = false;
        boolean isNotFoundUser = false;

        try {
            areas = db.list();
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve areas", e);
        }

        String login = request.getParameter("login");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        session.setAttribute("userSignedIn", null);

        for(User user : areas) {
            if (user.Login.equals(login) && !user.Code.equals(code)) {
                isWrongPassword = true;
                request.setAttribute("ErrorMessage", "Неверный код");
                session.setAttribute("userSignedIn", null);
                break;
            }
            else if (user.Login.equals(login) && user.Code.equals(code)) {
                request.setAttribute("login", user.getLogin());
                request.setAttribute("code", user.getCode());
                request.setAttribute("usersList", areas);

                session.setAttribute("userSignedIn", user.getLogin());
                isFoundUser = true;
                break;
            }
            else {
                isNotFoundUser = true;
                session.setAttribute("userSignedIn", null);
                request.setAttribute("ErrorMessage", "Пользователя не существует");
            }
        }
        PrintWriter out = response.getWriter();
        if (isFoundUser)
        {
            request.getRequestDispatcher("/action.jsp").forward(request, response);
        }
        else if (isWrongPassword)
        {
           // out.println("Ne pravilno code");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        else if (isNotFoundUser)
        {
           // out.println("Ne pravilno login");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }


    }

    public void destroy() {
    }
}