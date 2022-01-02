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
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private String username;
    private String password;
    private String email;
    private PrintWriter out;
    private String vcode;
    private String checkvcode;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        checkvcode = (String)request.getSession().getAttribute("vcode");
        //确保验证码的一次性
        request.getSession().removeAttribute("vcode");

        if(request.getParameter("vcode")!=""){
            vcode = request.getParameter("vcode");
            System.out.println("vcode="+vcode);
            System.out.println("checkvcode="+checkvcode);
        }
        if(!(vcode.equalsIgnoreCase(checkvcode))){
            out.print(-1);
        }else{
            if(request.getParameter("username")!=""){
                username = request.getParameter("username");
            }
            if(request.getParameter("password")!=""){
                password = request.getParameter("password");
            }
            if(request.getParameter("email")!=""){
                email = request.getParameter("email");
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            UserService userService = new UserServiceImpl();
            if(userService.register(user)){
                out.print(1);
            }else{
                System.out.println("UserService出错");
            }
        }

        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
