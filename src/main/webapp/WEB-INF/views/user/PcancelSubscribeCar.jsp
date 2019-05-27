<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/5/19
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>取消预约车辆</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div>
    <ul>
<c:choose>
    <c:when test="${code==0}">
        用户有预约的车辆
        <c:forEach items="${carList}" var="car">
            <li style="list-style: none">
                <div style="width: 100px;height: 100px;background: url('/parkingSystem/image/noparking.jpg');background-size: contain"></div>
                预约车位号：${car.car_parking_id}<br/>
                <a class="btn btn-danger" href="/parkingSystem/cancelSubscribeCar?carId=${car.car_parking_id}">取消预约车位</a></li>
        </c:forEach>
    </c:when>
    <c:when test="${code==1}">
        <h4>用户没有正在预约状态中的车辆</h4>
    </c:when>
    <c:otherwise>
        未知错误
    </c:otherwise>
</c:choose>
    </ul>
</div>
</body>
</html>
