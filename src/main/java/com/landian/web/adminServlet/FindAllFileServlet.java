package com.landian.web.adminServlet;

import com.landian.domain.File;
import com.landian.domain.PageBean;
import com.landian.service.FileService;
import com.landian.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/findAllFileServlet")
public class FindAllFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage)<1){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "8";
        }
        FileService service = new FileServiceImpl();
        PageBean<File> pb = service.findAllFileByPage(currentPage,rows);

        request.setAttribute("AllFilepb",pb);
        request.getRequestDispatcher("/adminAllFile.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
