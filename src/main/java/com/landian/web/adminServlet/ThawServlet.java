package com.landian.web.adminServlet;

import com.landian.service.AdminService;
import com.landian.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/thawServlet")
public class ThawServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] usernames = request.getParameterValues("id");
        AdminService service = new AdminServiceImpl();
        service.thaw(usernames);

        //重定向跳转
        response.sendRedirect(request.getContextPath()+"/findAllUserServlet?currentPage=1&rows=8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
