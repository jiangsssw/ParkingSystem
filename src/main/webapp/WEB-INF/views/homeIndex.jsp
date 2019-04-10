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
    <title>Title</title>
</head>
<body>
<div class="content">
    <div class="header">

    </div>
    <div class="main-content">
        <div class="nav-bar">
            <c:forEach items="${museList}" var="muse">
                <input type="button" value="<c:out value="${muse.museName}"></c:out>"
                       urlName="<c:out value="${muse.museUrl}"></c:out>" />
            </c:forEach>
        </div>
    </div>
    <div class="main-frame">
        <iframe src="/parkingSystem/loginSuccess">

        </iframe>
    </div>
    <div class="footer">

    </div>

</div>
</body>
<script>
    var seccNode = document.querySelector('iframe');
    document.querySelector('.nav-bar').onclick=function (e) {
        console.log("url"+e.target.getAttribute("urlName"));
        seccNode.src=e.target.getAttribute("urlName");
    }
</script>
</html>
