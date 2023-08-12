package com.example.servletgpp;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        writer.println("<p>" + request.getProtocol() + "<p>");
        writer.println("<p>" + request.getContentType() + "<p>");
        writer.println("<p>" + request.getRemoteUser() + "<p>");

        setCookies(request, response);

      //  response.addCookie(c);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        Enumeration< String > e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getHeader(name);
            out.println(name + " = " + value);
        }
//        String resp = request.getMethod();
//
//        Cookie c = new Cookie("protocol", resp);
//
//        response.addCookie(c);

    }

    private void setCookies(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("protocol", request.getMethod()));
    }

    public void destroy() {
    }
}