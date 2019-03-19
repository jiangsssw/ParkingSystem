<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/1/3
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据</title>
</head>
<body>
<h1>数据如下：</h1>
<c:forEach items="${userList}" var="user">
    姓名：<c:out value="${user.username}"></c:out>
    等级：<c:out value="${user.userRank}"></c:out>
    ID :<c:out value="${user.userId}"></c:out>
    <br/>
</c:forEach>
</body>
</html>
