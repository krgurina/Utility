package com.example.filt.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;


@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {
    Logger logger;
    public void init(FilterConfig config) throws ServletException {
        this.logger = Logger.getLogger(LogFilter.class);
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        this.logger.info("Time: " + LocalDateTime.now());
        this.logger.info(request.getLocalAddr());
        chain.doFilter(request, response);
    }
}
