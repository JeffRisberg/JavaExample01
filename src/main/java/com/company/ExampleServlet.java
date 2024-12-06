package com.company;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleServlet extends HttpServlet implements Filter {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String requestURL = request.getRequestURL().toString();

    String target = request.getParameter("target");

    target = target.replace("\n", "");
    target = target.replace("\r", "");

    // response.setHeader("Location", StringEscapeUtils.escapeHtml4(target));
    // response.getOutputStream().write(requestURL.getBytes());
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    Enumeration<String> params = req.getParameterNames();
    while (params.hasMoreElements()) {
      String name = params.nextElement();
      String value = request.getParameter(name);
      log(req.getRemoteAddr() + "::Request Params::{" + name + "=" + value + "}");
    }

    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        log(req.getRemoteAddr() + "::Cookie::{" + cookie.getName() + "," + cookie.getValue() + "}");
      }
    }

    // pass the request along the filter chain
    chain.doFilter(request, response);
  }
}
