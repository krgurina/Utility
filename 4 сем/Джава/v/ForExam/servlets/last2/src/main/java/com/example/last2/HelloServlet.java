package com.example.last2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    public void destroy() { }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final int a = Integer.parseInt(request.getParameter("a"));
            final int b = Integer.parseInt(request.getParameter("b"));
            final int c = Integer.parseInt(request.getParameter("c"));
            final int x = Integer.parseInt(request.getParameter("x"));

            PrintWriter out = response.getWriter();
            double y = a + b*x + c * x * x;
            out.println("<h1>" + y + "</h1>");
        }
        catch(NumberFormatException exception) {
            PrintWriter out = response.getWriter();
            out.println("<h1>" + "dodik you" + "</h1>");
        }
    }
}