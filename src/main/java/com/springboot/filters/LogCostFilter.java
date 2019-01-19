package com.springboot.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogCostFilter implements Filter {
    private Logger log = Logger.getLogger(LogCostFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        filterChain.doFilter(servletRequest, servletResponse);
        log.info(req.getRequestURI());
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}