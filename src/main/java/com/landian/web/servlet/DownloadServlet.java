package com.landian.web.servlet;

import com.landian.dao.FileDao;
import com.landian.dao.impl.FileDaoImpl;
import com.landian.domain.File;
import com.landian.util.DownloadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");


        FileDao fileDao= new FileDaoImpl();
        String id= request.getParameter("id");
        File file = fileDao.findFileById(id);

        //解决中文文件名问题
        //设置user-agent请求头
        String agent = request.getHeader("user-agent");//新加的代码
        //使用工具类编码文件名
        String filename = DownloadUtil.getFileName(agent, file.getFileName());//新加的代码
        String mimType = getServletContext().getMimeType(filename);//获取文件类型
        //设置文件MIME类型
        response.setHeader("content-type",mimType);

        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        //读取目标文件，通过response将目标文件写到客户端
        InputStream in = new FileInputStream(file.getPath());
        OutputStream out = response.getOutputStream();

        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }

        in.close();
        out.close();
        //String id = request.getParameter("id");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
