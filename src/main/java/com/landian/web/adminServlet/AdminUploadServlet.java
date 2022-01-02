package com.landian.web.adminServlet;

import com.landian.domain.User;
import com.landian.util.FileUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/adminUploadServlet")
public class AdminUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        //获取User
        String userName = null;
        boolean flag = ServletFileUpload.isMultipartContent(request);//enctype属性是否时multipart/form-data
        FileUtil fileUtil = new FileUtil();

        try {
            FileItemFactory fif = new DiskFileItemFactory();//工厂实例
            //创建文件上传对象
            ServletFileUpload upload = new ServletFileUpload(fif);
            //解析表单字段，封装成一个FileItem实例的集合
            List<FileItem> itemList = upload.parseRequest(request);
            Iterator<FileItem> it = itemList.iterator();//迭代器
            FileItem fileItem = it.next();
            if(fileItem.isFormField()) {
                userName = fileItem.getString("UTF-8");
            }
            if(flag){
                String msg_s = fileUtil.fileUpload(userName,itemList);
                if(msg_s == null){
                    request.setAttribute("msg",-1);
                }else if(msg_s == ""){
                    request.setAttribute("msg",-2);
                }else {
                    request.setAttribute("msg",1);
                    request.setAttribute("msg_s",msg_s);
                }
            }
            request.getRequestDispatcher(request.getContextPath()+"/findAllFileServlet?currentPage=1&rows=8").forward(request,response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
