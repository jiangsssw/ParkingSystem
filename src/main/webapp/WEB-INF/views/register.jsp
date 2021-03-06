<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/5/4
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div style="width: 100%;height: 100%;background-image: url('/parkingSystem/image/carBackground.jpg'); position: absolute">
        <ul class="list-group col-lg-2 col-md-offset-5" style="position: relative; top: 100px; opacity: .9">
            <li class="list-group-item">用户名称：<input type="text" name="userName"><strong>*</strong></li>
            <li class="list-group-item">密码：<input type="password" name="passWord"><strong>*</strong></li>
            <li class="list-group-item">再输一次密码：<input type="password" name="passWord2"><strong>*</strong></li>
            <li class="list-group-item">电话号码：<input type="text" name="phoneId"><strong>*</strong></li>
            <li class="list-group-item">电子邮箱：<input type="email" name="e-mail" placeholder="xxx@xxx.com"/><strong>*</strong> </li>
            <li class="list-group-item">地址：<input type="text" name="address"></li>
            <li class="list-group-item">
                <input type="button" id="submit" value="提交">
                <button class="col-md-offset-3"><a href="/parkingSystem/login">返回</a></button class="col-md-offset-3">
            </li>
        </ul>
</div>
</body>
<script>
   document.querySelector("#submit").onclick=function () {
        var userName = $('[name="userName"]')[0].value;
        var passWord = $('[name="passWord"]')[0].value;
        var passWord2 = $('[name="passWord2"]')[0].value;
        var phoneId = $('[name="phoneId"]')[0].value;
        var email = $('[name="e-mail"]')[0].value;
        var address = $('[name="address"]')[0].value;
        //参数校验
        if (userName==''||passWord==""||passWord2==""||phoneId==""||email==""){
            alert("标记 * 的为必填项，请检查！！！")
            return;
        }
        if (passWord!=passWord2){
            alert("两次密码不相同！！！");
            return;
        }
        console.log(phoneId.length);
        if (phoneId.length<11||isNaN(phoneId)){
            alert("手机号码格式不正确！！");
            return;
        }
        var index = email.indexOf("@");
        if (index==0||index<0){
            alert("邮箱格式不正确！！");
            return;
        }
        console.log(index);
        console.log("校验通过");
        //调用后台接口
        $.ajax({
            type : "post",
            url : '/parkingSystem/register',
            data : {
                "username":userName,
                "password":passWord,
                "phoneId" : phoneId,
                "emailAddress" : email,
                "address" : address
            },
            dataType : "json",
            success : function (d) {
                if (d.code==0){
                    //注册成功
                    confirm(d.msg+"  姓名："+d.userName+"  用户Id："+d.userId+"  电话号码："+phoneId);
                    window.location.href="/parkingSystem/login";
                    return ;
                }
                alert(d.msg)
            },
            error : function (e) {
                alert(e);
            }
        })

    }
</script>
</html>
