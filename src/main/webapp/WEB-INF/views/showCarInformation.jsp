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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="panel panel-primary col-md-10 col-md-offset-1" style="margin-top: 10px">
    <div class="panel-heading">
    <h4>车辆信息</h4>
    </div>
    <div class="panel-body ">
        <ul class="list-group">
        <c:choose>
            <c:when test="${code==0}">
                <c:forEach items="${carList}" var="cars">
               <li class="list-group-item">  车牌号：${cars.user_car_id} <button class="btn btn-danger" ><a style="color: white" href="/parkingSystem/deleteCheckInCar?carId=${cars.user_car_id}">删除</a></button></li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                ${result}
            </c:otherwise>
        </c:choose>
        </ul>
    </div>
</div>
</body>

</html>
