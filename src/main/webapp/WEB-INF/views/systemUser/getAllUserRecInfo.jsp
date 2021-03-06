<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/25
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员展示所有的信息</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="content">
    <table class="table table-hover">
        <tr>
            <th>用户Id</th>
            <th>用户姓名</th>
            <th>停车位</th>
            <th>车库</th>
            <th>消费方式</th>
            <th>停车开始时间</th>
            <th>停车结束时间</th>
            <th>停车时间</th>
            <th>消费</th>
            <th>租用方式</th>
            <th>电话号码</th>
        </tr>
        <tbody>
        <c:forEach items="${recs}" var="list">
            <tr>
                <td>${list.userId}</td>
                <td>${list.userName}</td>
                <td>${list.carParkingId}</td>
                <td>${list.carRoomNumber}</td>
                <td>${list.payType}</td>
                <td>${list.useStartTime}</td>
                <td>${list.useEndTime}</td>
                <td>${list.parkingTime}</td>
                <td>${list.countMoney}</td>
                <td>${list.useTime}</td>
                <td>${list.phoneId}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
