<%--
  Created by IntelliJ IDEA.
  User: 暴走的小脑袋瓜
  Date: 2021/12/14
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Register</title>
    <!-- 引入jQuery -->
    <script src="webjars/jquery/1.11.3/dist/jquery.min.js"></script>
    <script>
        $(document).ready(function() {

            $("#submitbutton").click(function() {
                if ($("#password").val() != $("#checkpassword").val()) {
                    alert("两次密码不一致");
                } else {
                    $.ajax({
                        url: 'registerServlet',
                        type: 'post',
                        data: {
                            "username": $("#username").val(), "password": $("#password").val(),
                            "checkpasword": $("#checkpassword").val(), "vcode": $("#vcode").val(),
                            "email": $("#email").val()
                        },
                        success: function (fk) {
                            if (fk == -1) {
                                window.alert("验证码错误！");
                            } else if (fk == 1) {
                                alert("注册成功！");
                                location.replace("${pageContext.request.contextPath}/login.jsp");
                            } else {
                                alert("注册失败！邮箱已经注册过了");
                            }
                        },
                    });
                }
            });

        })
        var time0 = 60;
        var time = time0;
        var t;  // 用于验证按钮的60s计时
        function vcodebutton() {
            $.ajax({
                url: 'emailServlet',
                type: 'post',
                data: {"email": $("#email").val(), "method": "getVCode"},
                success: function (msg) {
                    if (msg == -1) {
                        window.alert("请输入正确的邮箱！");
                    } else {
                        useChangeBTN();  // 控制下一次重新获取验证码
                    }
                },
            });
        }
//修改按钮，控制验证码重新获取
            function changeBTN(){
                if(time > 0){
                    $("#btnGetVcode").text("("+time+"s)"+"重新获取");
                    time = time - 1;
                }
                else{
                    var btnGet = document.getElementById("btnGetVcode");
                    btnGet.disabled = false;
                    $("#btnGetVcode").text("获取验证码");
                    clearInterval(t);
                    time = time0;
                }
            }
            function useChangeBTN(){
                $("#btnGetVcode").text("("+time+"s)"+"重新获取");
                time = time - 1;
                t = setInterval("changeBTN()", 1000);  // 1s调用一次
            }


    </script>
</head>
<body>
    <center>
        <h2>新用户注册</h2>
        <form AUTOCOMPLETE="OFF">
            <table>
                <tr height="35px">
                    <td >用户名：</td>
                    <td>
                        <input type="text" required="required" placeholder="用户名" name="username" id="username" />
                    </td>
                </tr>
                <tr height="35px">
                    <td >密码：</td>
                    <td>
                        <input type="password" required="required" placeholder="密码" id="password" name="password" />
                    </td>
                </tr>
                <tr height="35px">
                    <td > 确认密码：</td>
                    <td>
                        <input type="password" required="required" placeholder="确认密码" id="checkpassword" name="checkpassword" />
                    </td>
                </tr>
                <tr height="35px">
                    <td >收件邮箱：</td>
                    <td>
                        <input type="text" required="required" name="email" id="email" placeholder="输入邮箱" />
                    </td>
                    <td>
                        <input type="button" id="btnGetVcode" name="btnGetVcode" onclick="vcodebutton();" value="获取验证码"/>
                    </td>
                </tr>
                <tr height="35px">
                    <td >验证码：</td>
                    <td>
                        <input type="text" required="required" name="vcode" id="vcode" placeholder="输入验证码"/>
                    </td>
                </tr>
            </table>
                <input type="button" value="注册" id="submitbutton" />
        </form>

    </center>
</body>
</html>
