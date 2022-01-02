<%--
  Created by IntelliJ IDEA.
  User: 暴走的小脑袋瓜
  Date: 2021/12/24
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>adminLgoin</title>
</head>
<body>
<center>
    管理员登录
    <form action="${pageContext.request.contextPath}/adminLoginServlet">
        账号：<input type="text" required="required" placeholder="账号" name="adminname"></input><br>
        密码：<input type="password" required="required" placeholder="密码" name="password"></input><br>
        <button type="submit">登录</button><br>
        <div style="color: red">${login_msg}</div>
    </form>
</center>
</body>
</html>
