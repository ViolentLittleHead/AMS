package com.landian.web.servlet;

import com.landian.domain.File;
import com.landian.domain.PageBean;
import com.landian.domain.User;
import com.landian.service.FileService;
import com.landian.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findFileByPageServlet")
public class FindFileByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage)<1){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "8";
        }
        String username = ((User)request.getSession().getAttribute("user")).getUsername();
        FileService service = new FileServiceImpl();
        PageBean<File> pb = service.findFileByPage(currentPage,rows,username);

        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/fileManage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
