package com.example.servlet1;

import jakarta.servlet.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class FilterDataLogger implements Filter {

     private static final Logger logger = Logger.getLogger(FilterDataLogger.class);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        logger.info("port = " + servletRequest.getLocalPort() + '\n' +  "loacal" + servletRequest.getLocale());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
