package com.example.cook;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.swing.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void init() { }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        deleteCookie(request, response);
    }

    private void deleteCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie clearCookie1 = new Cookie("course", "");
        Cookie clearCookie2 = new Cookie("group", "");
        clearCookie1.setMaxAge(0);
        clearCookie2.setMaxAge(0);

        response.addCookie(clearCookie1);
        response.addCookie(clearCookie2);

        request.setAttribute("message", "Куки очищены");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Group = req.getParameter("group");
        Course = req.getParameter("course");

        if(getCookie(req, "group") == null) {
            setCookie(req, resp);
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        } else if (!getCookie(req, "group").getValue().equals(Group) ||
                !getCookie(req, "course").getValue().equals(Course)) {
            req.setAttribute("message", "НЕВЕРНОЕ ЗНАЧЕНИЕ ОТ ВЕРТИЛЬ");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "ВЕРНОЕ");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
    String Group;
    String Course;
    private void setCookie(HttpServletRequest req, HttpServletResponse resp) {
        resp.addCookie(new Cookie("course", Course));
        resp.addCookie(new Cookie("group", Group));
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void destroy() { }
}