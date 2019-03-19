<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/2/21
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
自定义表单验证:
<!--<form name="f" action="/login" method="post">-->
<form name="f" >
    hellow
    <br/>
    用户名:
    <input id="userInput" type="text" name="userInput" placeholder="用户Id或手机号"><br/>
    密码:
    <input id="password" type="password" name="password" placeholder="password"><br/>
    <input id="submit" name="submit" type="button" value="提交">
</form>
</body>
<script>

    document.getElementById("submit").onclick=function () {
        var userInput = document.getElementById("userInput");
        var password = document.getElementById("password");
        var xml = new XMLHttpRequest();
        xml.open('post','/parkingSystem/login');
        xml.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xml.onload=function (data) {
            var a = xml.responseText;
            console.log(data);
            if (a==="false"){
                alert("请查看一下"+data)
            }
        };
        xml.send('userInput='+userInput.value+'&password='+password.value+'');
    }
</script>
</html>
