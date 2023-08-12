package com.example.last;

import java.io.*;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.getRequestDispatcher("/index.jsp").forward(request, response);

        PrintWriter out = response.getWriter();
        out.println(request.getProtocol());
        out.println(request.getRemotePort());
        out.println(request.getRemoteHost());

        setCookies(request, response);
    }

    private void setCookies(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("protocol", request.getMethod()));
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Enumeration<String> enumeration = request.getHeaderNames();

        PrintWriter out = response.getWriter();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            out.println(name + " = " + request.getHeader(name));
        }


        setCookies(request, response);
    }

    public void destroy() {
    }
}