<%--
  Created by IntelliJ IDEA.
  User: 暴走的小脑袋瓜
  Date: 2021/12/21
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
        window.onload = function () {
            document.getElementById("a_exit").onclick = function () {
                if(confirm("确定退出吗？")){
                    location.replace("${pageContext.request.contextPath}/exitServlet");
                }
            }
        }
    </script>
</head>
<body>

<center>
    <h1>欢迎使用文章管理系统</h1>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="index.jsp">主页</a></li>
        <li role="presentation"><a href="user.jsp">我的信息</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/findFileByPageServlet?currentPage=1&rows=8">文件管理</a></li>
        <li role="presentation"><a href="editing.jsp">在线编辑</a></li>
        <li role="presentation"><a href="javascript:void(0);" id="a_exit">退出</a></li>
    </ul>

</center>

</body>
</html>