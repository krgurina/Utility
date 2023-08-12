package com.example.servlet1;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.log4j.Logger;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private FilterDataValidate filter = new FilterDataValidate();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String data = req.getParameter("data");

        filter.data = Integer.parseInt(data);

        filter.doFilter(req, resp, Integer.parseInt(data));


        PrintWriter writer = resp.getWriter();
        writer.println(data);
    }
}