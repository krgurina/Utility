package com.example.servlet1;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ExportException;

public class FilterDataValidate implements Filter {

    public int data;

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         int data) throws ServletException, IOException {
        try {
            if(data > 7777) {
                throw new RuntimeException();
            }
        } catch (Exception e){
            servletRequest.getRequestDispatcher("/404.jsp").forward(servletRequest, servletResponse);
        }

    }
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
