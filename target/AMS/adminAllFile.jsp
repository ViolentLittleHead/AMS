<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 暴走的小脑袋瓜
  Date: 2022/1/2
  Time: 11:25
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
            //上传按钮
            document.getElementById("upfile").onclick = function(){
                document.getElementById("upform").submit();
            }
            //下载按钮
            document.getElementById("downfile").onclick = function (){
                if (confirm("确定要下载选中条目吗？")){

                    var j = 0;
                    var flag = false;
                    var cbs = document.getElementsByName("id");
                    for (var i = 0;i < cbs.length; i++){
                        if(cbs[i].checked){
                            flag=true;
                            j++;
                        }
                    }
                    if(flag){
                        if(j > 1){
                            alert("一次只能下载一个文件！");
                        }else {
                            document.getElementById("form").action = "${pageContext.request.contextPath}/downloadServlet";
                            document.getElementById("form").submit();
                            for (var i = 0;i < cbs.length; i++){
                                if(cbs[i].checked){
                                    cbs[i].checked = false;
                                }
                            }
                        }
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

<script>
    switch (${requestScope["msg"]}) {
        case -1 :alert('文件已存在');break;
        case -2 :alert('上传失败！');break;
        case 1 :alert('${requestScope["msg_s"]}');break;
        default :alert('error');
    }
</script>

<center>
    <h1>欢迎管理员使用文章管理系统</h1>
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=1&rows=8">用户管理</a></li>
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/findAllFileServlet?currentPage=1&rows=8">所有文件管理</a></li>
        <li role="presentation"><a href="javascript:void(0);" id="a_exit">退出</a></li>
    </ul>
    <br>
    <form id="upform" action="${pageContext.request.contextPath}/adminUploadServlet" method="post" enctype="multipart/form-data">
        <div style="float: left;margin-left: 12%;">
            <input type="text" name="username" placeholder="请输入用户名" ></input>
        </div>
        <div style="float: left;margin-left: 5%;">
            <input type="file" id="myFile" name="myFile" placeholder="请选择文件" multiple></input>
        </div>
        <div style="float: right;margin-right: 12%;">
            <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
        </div>
        <div style="float: right;margin-right: 5%;">
            <a class="btn btn-primary" href="javascript:void(0);" id="downfile">下载文件</a>
        </div>
        <div style="float: right;margin-right: 5%;">
            <a class="btn btn-primary" href="javascript:void(0);" id="upfile">上传文件</a>
        </div>
    </form>
    <table class="table table-bordered" style="width: 80%;">
        <tr>
            <td width="5%"><input type="checkbox" id="firstCb"></td>
            <td width="10%">序号</td>
            <td width="25%">用户名</td>
            <td width="30%">文件名</td>
            <td width="30%">上传日期</td>
        </tr>
        <c:forEach items="${AllFilepb.list}" var="file" varStatus="i">
            <tr>
                <td width="5%"><input type="checkbox" name="id" value="${file.id}"></td>
                <td width="10%">${8*(AllFilepb.currentPage-1)+i.count}</td>
                <td width="25%">${file.username}</td>
                <td width="30%">${file.fileName}</td>
                <td width="30%">${file.date}</td>
            </tr>
        </c:forEach>

    </table>

    <nav aria-label="Page navigation">
        <ul id="page" class="pagination" >
            <c:if test="${AllFilepb.currentPage == 1}">
            <li class="disabled">
                </c:if>
                <c:if test="${AllFilepb.currentPage != 1}">
            <li>
                </c:if>

                <a href="${pageContext.request.contextPath}/findAllFileServlet?currentPage=${AllFilepb.currentPage - 1}&rows=8" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <c:forEach begin="1" end="${AllFilepb.totalPage}" var="i">
                <c:choose>
                    <c:when test="${AllFilepb.currentPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findAllFileServlet?currentPage=${i}&rows=8">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.request.contextPath}/findAllFileServlet?currentPage=${i}&rows=8">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${AllFilepb.currentPage == AllFilepb.totalPage}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findAllFileServlet?currentPage=${AllFilepb.totalPage}&rows=8" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/findAllFileServlet?currentPage=${AllFilepb.currentPage + 1}&rows=8" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>



            <span style="font-size: 20px;margin-left: 5px;">
                共${AllFilepb.totalCount}条记录，共${AllFilepb.totalPage}页
            </span>
        </ul>
    </nav>

</center>
</body>
</html>
