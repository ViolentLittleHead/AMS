package com.landian.web.servlet;

import com.landian.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/emailServlet")
public class EmailServlet extends HttpServlet {
    private String email;  // 获取的收件人邮箱
    private String vCode;  // 后台产生的验证码
    private String method;  // 要接收的方法
    private PrintWriter out;  // 输出流
    private EmailUtil emailUtil = EmailUtil.instance;
    public EmailServlet() {
        // TODO Auto-generated constructor stub
        System.out.println("初始化");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //语言编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        out = response.getWriter();
        // 获取来自前端的参数
        email = request.getParameter("email");
        method = request.getParameter("method");

        switch (method) {
            case "getVCode":
                String vcode = mGetVCode();
                request.getSession().setAttribute("vcode",vcode);
                break;
            default:
                break;
        }
        out.flush();
        out.close();
    }
    /*
     * 产生验证码，并发送邮件
     */
    private String mGetVCode() {
        // TODO Auto-generated method stub
        if(!isEmail(email)) {  // 邮箱不正确
            out.print(-1);
            return "";
        }
        try {
            emailUtil.sendEmail(email);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        vCode = emailUtil.getVCode();
        System.out.println("验证码为：" + vCode);
        out.print(1);
        return vCode;
    }
    private boolean isEmail(String email) {
        if(email.length() == 0 || email == null) {
            return false;
        }
        // 正则表达式验证邮箱
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        return pattern.matcher(email).matches();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
