package com.landian.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("*.jsp")
public class AllFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String path = request.getRequestURI();
        if(request.getSession().getAttribute("user") != null ||request.getSession().getAttribute("admin") != null ||
                path.contains("/login.jsp") || path.contains("/adminLogin.jsp") || path.contains("/register.jsp") ||
                path.contains("/css") || path.contains("/loginServlet")){
            chain.doFilter(req, resp);
            return;
        }
        response.sendRedirect("/login.jsp");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
