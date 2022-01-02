package com.landian.web.adminServlet;

import com.landian.domain.PageBean;
import com.landian.domain.User;
import com.landian.service.AdminService;
import com.landian.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findAllUserServlet")
public class FindAllUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage)<1){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "8";
        }
        AdminService service = new AdminServiceImpl();
        PageBean<User> pb = service.findAllUserByPage(currentPage,rows);

        request.setAttribute("AllUserpb",pb);
        request.getRequestDispatcher("/adminIndex.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
