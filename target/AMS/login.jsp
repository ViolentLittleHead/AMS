<%--
  Created by IntelliJ IDEA.
  User: 暴走的小脑袋瓜
  Date: 2021/12/13
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- 引入jQuery -->
    <script src="webjars/jquery/1.11.3/dist/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>

</head>
<body>
<div id="login">
    <h1 id="login1">Login</h1>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <input type="text" required="required" placeholder="用户名" name="username"></input>
        <input type="password" required="required" placeholder="密码" name="password"></input>
        <button class="but" type="submit">登录</button>
    </form>
    <a href="register.jsp">没有用户?点击注册</a><br><br>
    <div style="color: red">${login_msg}</div>

</div>
<a href="adminLogin.jsp">管理员请点击这里进入</a>
</body>
</html>
