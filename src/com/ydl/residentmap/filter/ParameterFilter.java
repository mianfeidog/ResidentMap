package com.ydl.residentmap.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class ParameterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsq = (HttpServletRequest)servletRequest;

        BufferedReader reader=servletRequest.getReader();
        String test5=reader.toString();

        String test6=hsq.getQueryString();


        String servletPath = hsq.getServletPath();
        String contextPath = hsq.getContextPath();

        String test3=hsq.getRequestURI();
        String test4=hsq.getRequestURL().toString();

        Map test = hsq.getParameterMap();
        Enumeration test2= hsq.getParameterNames();

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
