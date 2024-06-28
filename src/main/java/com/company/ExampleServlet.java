package com.company;

import java.io.IOException;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

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
}