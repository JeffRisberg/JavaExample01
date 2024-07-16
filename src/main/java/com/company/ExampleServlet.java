package com.company;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Enumeration;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import lombok.extern.log4j.Log4j;

@Log4j
public class ExampleServlet extends HttpServlet implements Filter {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response
        .setHeader("Location", request.getParameter("target"));
    response
        .getOutputStream()
        .write(request.getRequestURL().getBytes());
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest response = (HttpServletResponse) servletResponse;

    response.addHeader("Access-Control-Allow-Origin", "*");

    chain.doFilter(servletRequest, response);
  }

  public void doFilter2(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    Enumeration<String> params = req.getParameterNames();
    while(params.hasMoreElements()){
      String name = params.nextElement();
      String value = request.getParameter(name);
      log(req.getRemoteAddr() + "::Request Params::{"+name+"="+value+"}");
    }

    Cookie[] cookies = req.getCookies();
    if(cookies != null){
      for(Cookie cookie : cookies){
        log(req.getRemoteAddr() + "::Cookie::{"+cookie.getName()+","+cookie.getValue()+"}");
      }
    }
    
    ((HttpRequest) req).addHeader("Access-Control-Allow-Origin", "*");

    // pass the request along the filter chain
    chain.doFilter(request, response);
  }
}