package com.example.servlet2fil;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "FilterFirst")
public class FilterFirst implements Filter {

    private static Logger log = Logger.getLogger(FilterFirst.class);
    private String code;

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Айпишник: " + req.getRemoteAddr() + "\n " + "Время: " + new Date().toString());

        chain.doFilter(request, response);
    }


}
