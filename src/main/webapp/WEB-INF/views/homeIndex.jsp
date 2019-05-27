<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/9
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <%--<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/test.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <%--<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>--%>
    <%--<!-- 包含了所有编译插件 -->--%>
    <%--<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
</head>
<body style="background-image: url('/parkingSystem/image/51.jpg');background-size: cover">
<div class="content panel-body col-sm-offset-2" style="width: 1080px;height: 960px;opacity: 0.85;background: white;margin-top: 50px">
    <div class="header">
        <div class="row">
            <div class="col-sm-2 col-sm-2">
                <a href="#">
                    <img style="width: 100px;height: 100px" src="/parkingSystem/image/head.jpg"
                         alt="头像">
                </a>
            </div>
            <p style="margin-top: 50px;font-size: 18px">欢迎用户：<span class="label label-info">${name}</span>使用本系统。</p>
            <button type="button" class="btn btn-danger btn-sm col-sm-offset-8"><a href="/parkingSystem/loginOut" style="color: white"><span class="glyphicon glyphicon-user"></span>注销</a></button>
    </div>
        <h5><span class="label label-info">用户Id: ${userId}</span></h5>
        <h6><span class="label label-primary">${type}</span></h6>

    </div>
    <div class="main-content">
        <nav class="navbar navbar-btn" role="navigation">
            <div class="container-fluid">
        <div>
            <ul id="navBar" class="nav navbar-nav">
                <li class="hover active"><a urlName="/parkingSystem/loginSuccess">主页</a></li>
            <c:forEach items="${museList}" var="muse">
                <li class="hover">
                <a urlName="<c:out value="${muse.muse_url}"></c:out>">
                        ${muse.muse_name}
                </a>
                </li>
            </c:forEach>
            </ul>
        </div>
            </div>
        </nav>
    </div>
    <div class="main-frame panel-body">
        <iframe src="/parkingSystem/loginSuccess" height="680px" width="1000px">

        </iframe>
    </div>

</div>
</body>
<script>
    var seccNode = document.querySelector('iframe');
    document.querySelector('#navBar').onclick=function (e) {
        console.log("url"+e.target.getAttribute("urlName"));
        seccNode.src=e.target.getAttribute("urlName");
    }
</script>
</html>
