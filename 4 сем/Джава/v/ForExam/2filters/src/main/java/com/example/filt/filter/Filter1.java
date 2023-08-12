package com.example.filt.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "Filter1")
public class Filter1 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String n = request.getParameter("nam");
        String p = request.getParameter("phone");

        if (p != null && !p.isEmpty() && n != null && !n.isEmpty()) {
            request.setAttribute("hello", n);
            request.getRequestDispatcher("/hello.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Так низя! Введите все данные");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }


        chain.doFilter(request, response);
    }
}
