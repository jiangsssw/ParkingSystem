<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div style="width: 100%;height: 100%;background-image: url('/parkingSystem/image/carBackground.jpg'); position: absolute">
<!--<form name="f" action="/login" method="post">-->
<form name="f" style="position: relative;top: 20%"  class="col-md-offset-5 ">
    <br/>
    <div class="row">
    <div class="col-lg-4">
    用户名:
    <input id="userInput" class="form-control" type="text" name="userInput" placeholder="用户Id或手机号">
    </div>
    </div>
    <div class="row">
    <div class="col-lg-4">
    密码:
    <input id="password" class="form-control" type="password" name="password" placeholder="password">
    </div>
    </div>
    <div class="row">
        <div class="col-lg-1">
    <input id="submit" class="btn btn-success" name="submit" type="button" value="提交">
        </div>
        <button class="btn btn-info"><a href="/parkingSystem/register">注册</a></button>
    </div>

</form>

</div>
</body>

<script>

    document.getElementById("submit").onclick=function () {
        var userInput = document.getElementById("userInput");
        var password = document.getElementById("password");
        if(userInput==""||password==""){
            alert("账户和密码不能为空！！！")
        }
        var xml = new XMLHttpRequest();
        xml.open('post','/parkingSystem/login');
        xml.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xml.onload=function (data) {
            var a = xml.responseText;
            console.log(data);
            if (a=="false"){
                alert("用户名或密码错误，请检查一下")
            }else {
                window.location.href='/parkingSystem/homeIndex'
            }
        };
        xml.send('userInput='+userInput.value+'&password='+password.value+'');
    }
</script>
</html>
