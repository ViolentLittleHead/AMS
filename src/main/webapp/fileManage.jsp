<%@ page import="com.landian.domain.User" %>
<%@ page import="com.landian.domain.File" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>FileManage</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>

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
    <script>

        window.onload = function () {
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
                    location.replace("${pageContext.request.contextPath}/exitServlet");
                }
            }

            //上传按钮
            document.getElementById("upfile").onclick = function(){
                document.getElementById("upform").submit();
            }
            //表单提交
            document.getElementById("delSelected").onclick = function () {
                if (confirm("确定要删除选中条目吗？")){

                    var flag = false;
                    var cbs = document.getElementsByName("id");
                    for (var i = 0;i < cbs.length; i++){
                        if(cbs[i].checked){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        document.getElementById("form").action = "${pageContext.request.contextPath}/delSelectedServlet";
                        document.getElementById("form").submit();
                    }
                }
            }

            //全选按钮
            document.getElementById("firstCb").onclick = function () {
                var cbs = document.getElementsByName("id");
                for (var i = 0;i < cbs.length; i++){
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>

</head>
<body>
<jsp:useBean id="fileDaoImpl" class="com.landian.dao.impl.FileDaoImpl"/>
<script>
    switch (${requestScope["msg"]}) {
        case -1 :alert('文件已存在');break;
        case -2 :alert('上传失败！');break;
        case 1 :alert('${requestScope["msg_s"]}');break;
        default :alert('错误！');
    }
</script>
<center>
    <h1>欢迎使用文章管理系统</h1>
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="index.jsp">主页</a></li>
        <li role="presentation"><a href="user.jsp">我的信息</a></li>
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/findFileByPageServlet?currentPage=1&rows=8">文件管理</a></li>
        <li role="presentation"><a href="editing.jsp">在线编辑</a></li>
        <li role="presentation"><a href="javascript:void(0);" id="a_exit">退出</a></li>
    </ul>
    <br>
    <form id="upform" action="${pageContext.request.contextPath}/uploadServlet" method="post" enctype="multipart/form-data">
        <div style="float: left;margin-left: 12%;">
            <input type="file" id="myFile" name="myFile" placeholder="请选择文件" multiple>&nbsp;&nbsp;
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

    <div id="div1">
        <form id="form" method="post">
            <table class="table table-bordered" style="width: 80%;">
                <tr>
                    <td width="5%"><input type="checkbox" id="firstCb"></td>
                    <td width="10%">序号</td>
                    <td width="45%">文件名</td>
                    <td width="30%">上传日期</td>
                    <td width="10%">操作</td>
                </tr>
                <c:forEach items="${pb.list}" var="file" varStatus="i">
                    <tr>
                        <td width="5%"><input type="checkbox" name="id" value="${file.id}"></td>
                        <td width="10%">${8*(pb.currentPage-1)+i.count}</td>
                        <td width="45%">${file.fileName}</td>
                        <td width="40%">${file.date}</td>
                        <td width="10%">
                            <div>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/editingFileServlet?id=${file.id}">查看/编辑</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </form>
    </div>

    <nav aria-label="Page navigation">
        <ul id="page" class="pagination" >
            <c:if test="${pb.currentPage == 1}">
                <li class="disabled">
            </c:if>
            <c:if test="${pb.currentPage != 1}">
                <li>
            </c:if>

                <a href="${pageContext.request.contextPath}/findFileByPageServlet?currentPage=${pb.currentPage - 1}&rows=8" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                <c:choose>
                    <c:when test="${pb.currentPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findFileByPageServlet?currentPage=${i}&rows=8">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.request.contextPath}/findFileByPageServlet?currentPage=${i}&rows=8">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${pb.currentPage == pb.totalPage}">
                     <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findFileByPageServlet?currentPage=${pb.totalPage}&rows=8" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                        </a>
                     </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/findFileByPageServlet?currentPage=${pb.currentPage + 1}&rows=8" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>



            <span style="font-size: 20px;margin-left: 5px;">
                共${pb.totalCount}条记录，共${pb.totalPage}页
            </span>
        </ul>
    </nav>

</center>

</body>
</html>
