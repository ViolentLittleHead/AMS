<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 暴走的小脑袋瓜
  Date: 2022/1/1
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script>
        window.onload = function () {
            //全选按钮
            document.getElementById("firstCb").onclick = function () {
                var cbs = document.getElementsByName("id");
                for (var i = 0;i < cbs.length; i++){
                    cbs[i].checked = this.checked;
                }
            }

            //表单提交（解冻）
            document.getElementById("thaw").onclick = function () {
                if (confirm("确定要解冻账号吗？")){

                    var flag = false;
                    var cbs = document.getElementsByName("id");
                    for (var i = 0;i < cbs.length; i++){
                        if(cbs[i].checked){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        document.getElementById("form").action = "${pageContext.request.contextPath}/thawServlet";
                        document.getElementById("form").submit();
                    }
                }
            }
            //表单提交（冻结）
            document.getElementById("frozen").onclick = function () {
                if (confirm("确定要冻结账号吗？")){

                    var flag = false;
                    var cbs = document.getElementsByName("id");
                    for (var i = 0;i < cbs.length; i++){
                        if(cbs[i].checked){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        document.getElementById("form").action = "${pageContext.request.contextPath}/frozenServlet";
                        document.getElementById("form").submit();
                    }
                }
            }
            //退出
            document.getElementById("a_exit").onclick = function () {
                if(confirm("确定退出吗？")){
                    location.replace("${pageContext.request.contextPath}/adminExitServlet");
                }
            }
        }
    </script>

    <style>
        #div1{
            margin: 30px;
        }
        #page{
            position: absolute;
            top:100%;
            right: 40%;
        }
    </style>
</head>
<body>
<center>
    <h1>欢迎管理员使用文章管理系统</h1>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=1&rows=8">用户管理</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/findAllFileServlet?currentPage=1&rows=8">所有文件管理</a></li>
        <li role="presentation"><a href="javascript:void(0);" id="a_exit">退出</a></li>
    </ul>
    <br>
    <div style="float: right;margin-right: 12%;">
        <a class="btn btn-primary" href="javascript:void(0);" id="thaw">解冻账号</a>
    </div>
    <div style="float: right;margin-right: 5%;">
        <a class="btn btn-primary" href="javascript:void(0);" id="frozen">冻结账号</a>
    </div>
    <br>

    <div id="div1">
        <form id="form" method="post">
            <table class="table table-bordered" style="width: 80%;">
                <tr>
                    <td width="5%"><input type="checkbox" id="firstCb"></td>
                    <td width="10%">序号</td>
                    <td width="25%">用户名</td>
                    <td width="20%">密码</td>
                    <td width="20%">邮箱</td>
                    <td width="20%">状态</td>
                </tr>
                <c:forEach items="${AllUserpb.list}" var="user" varStatus="i">
                    <tr>
                        <td width="5%"><input type="checkbox" name="id" value="${user.username}"></td>
                        <td width="10%">${8*(AllUserpb.currentPage-1)+i.count}</td>
                        <td width="25%">${user.username}</td>
                        <td width="20%"><abbr title="${user.password}">******</abbr></td>
                        <td width="20%">${user.email}</td>
                        <td width="20%">
                            <c:choose>
                                <c:when test="${user.status == 1}">
                                    <font color="green">激活</font>
                                </c:when>
                                <c:otherwise>
                                    <font color="red">冻结</font>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </div>
    <nav aria-label="Page navigation">
        <ul id="page" class="pagination" >
            <c:if test="${AllUserpb.currentPage == 1}">
            <li class="disabled">
                </c:if>
                <c:if test="${AllUserpb.currentPage != 1}">
            <li>
                </c:if>

                <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${AllUserpb.currentPage - 1}&rows=8" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <c:forEach begin="1" end="${AllUserpb.totalPage}" var="i">
                <c:choose>
                    <c:when test="${AllUserpb.currentPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${i}&rows=8">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${i}&rows=8">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${AllUserpb.currentPage == AllUserpb.totalPage}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${AllUserpb.totalPage}&rows=8" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${AllUserpb.currentPage + 1}&rows=8" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>



            <span style="font-size: 20px;margin-left: 5px;">
                共${AllUserpb.totalCount}条记录，共${AllUserpb.totalPage}页
            </span>
        </ul>
    </nav>

</center>
</body>
</html>
