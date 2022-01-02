package com.landian.web.adminServlet;

import com.landian.domain.Admin;
import com.landian.service.AdminService;
import com.landian.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String adminname = null;
        String password = null;
        if(request.getParameter("adminname")!="" && request.getParameter("password")!=""){
            adminname = request.getParameter("adminname");
            password = request.getParameter("password");
        }
        Admin loginAdmin = new Admin();
        loginAdmin.setAdminname(adminname);
        loginAdmin.setPassword(password);
        AdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.login(loginAdmin);
        if (admin != null){
            request.getSession().setAttribute("admin",admin);
            response.sendRedirect(request.getContextPath()+"/findAllUserServlet?currentPage=1&rows=8");
        }else {
            //提示信息
            //跳转页面
            request.setAttribute("login_msg","账号或密码错误！");
            request.getRequestDispatcher("/adminLogin.jsp").forward(request,response);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
