package com.landian.web.servlet;

import com.landian.dao.impl.UserDaoImpl;
import com.landian.domain.User;
import com.landian.service.UserService;
import com.landian.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = null;
        String password = null;
        if(request.getParameter("username")!="" && request.getParameter("password")!=""){
            username = request.getParameter("username");
            password = request.getParameter("password");
        }
        User loginUser = new User();
        User user = null;
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        UserService userService = new UserServiceImpl();
        user = userService.login(loginUser);
        if(user != null){
            System.out.println(user.getStatus());
            if("1".equals(user.getStatus())){
                request.getSession().setAttribute("user",user);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }else{
                request.setAttribute("login_msg","用户已被冻结");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
        }else {
            //提示信息
            //跳转页面
            request.setAttribute("login_msg","用户名或密码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
