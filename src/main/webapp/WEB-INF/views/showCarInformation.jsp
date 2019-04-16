<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/15
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示添加车辆信息界面</title>
</head>
<body>
    <h4>返回结果：</h4>
    <div class="content">
        <c:out value="${result.msg}"></c:out>
    </div>
    <div class="content-main">
        <input type="button" value="返回主页" onclick="backToIndex()"/>
    </div>
</body>
<script>
    function backToIndex() {
        window.location.href="/parkingSystem/homeIndex";
    }
</script>
</html>
