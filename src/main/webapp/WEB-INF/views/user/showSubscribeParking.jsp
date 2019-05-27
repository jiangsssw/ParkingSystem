<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/5/8
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预约界面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
    <%--二级菜单--%>
    <%--选择车辆--%>
        <%--请选择预约车辆：<select>--%>
            <%--<option value="chooseCar" selected="selected">选择车辆</option>--%>
            <%--<c:forEach items="${cars}" var="car" >--%>
                <%--<option value="${car.user_car_id}">${car.user_car_id}</option>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
        选择车库：<select id="selectValue">
        <option value="1" selected="selected">1</option>
        <c:forEach items="${roomList}" var="room">
            <option value="${room.intValue()}">${room.intValue()}</option>
        </c:forEach>
    </select>号车库
    <input id="queryRoom" class="btn btn-success" type="button" value="查询">
    <%--选择预约--%>
        <div class="show-main">
            <%--可预约：<div style="width: 50px;height: 50px;background:#5ae814a3 "></div>--%>
            <ul class="list-group">
                <c:forEach items="${parkings}" var="parking">
                    <li class="pull-left" style="margin-top: 40px;margin-left: 40px;list-style: none">
                        <div style="height: 120px;width: 120px">
                        <div STYLE="display: block;height: 100px;width: 100px;background: <c:choose>
                        <c:when test="${parking.parking_status=='01'}">
                                url('/parkingSystem/image/noparking.jpg');
                                background-size: contain;
                        </c:when>
                        <c:when test="${parking.parking_status=='02'}">
                                url('/parkingSystem/image/parking.jpg');
                                background-size: contain;
                        </c:when>
                        <c:when test="${parking.is_subscription==1}">
                                url('/parkingSystem/image/parking.jpg');
                                background-size: contain;
                        </c:when>
                        <c:otherwise>
                                #ff5722
                        </c:otherwise>
                        </c:choose>;">
                            </div>
                        <div>
                            车位号：<a href="">${parking.car_parking_id}</a>
                            <c:choose>
                                <c:when test="${parking.parking_status=='01'}">
                                    <a style="margin-left: 20%" href="/parkingSystem/subscribeParking?parkingId=${parking.car_parking_id}"><input class="btn btn-success" type="button" value="预约"/></a>
                                </c:when>
                                <c:when test="${parking.parking_status=='02'}">
                                </c:when>
                                <c:when test="${parking.is_subscription==1}">

                                </c:when>
                                <c:otherwise>

                                </c:otherwise>
                            </c:choose>
                        </div>
                        <span >
                    <%--<c:choose>--%>
                        <%--<c:when test="${parking.parking_status=='01'}">--%>
                        <%--01 车位空闲--%>
                    <%--</c:when>--%>
                        <%--<c:when test="${parking.parking_status=='02'}">--%>
                            <%--02 车位占用--%>
                        <%--</c:when>--%>
                        <%--<c:when test="${parking.is_subscription==1}">--%>
                            <%--车辆预约中--%>
                        <%--</c:when>--%>
                        <%--<c:otherwise>--%>
                            <%--不可以用--%>
                        <%--</c:otherwise>--%>
                    <%--</c:choose>--%>

                </span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
</body>
<script>
   var room = document.querySelector("#queryRoom");

   room.onclick=function(){
       var roomId =document.querySelector("#selectValue").value;
       window.location.href="/parkingSystem/showSubscribeParking?number="+roomId;
   }
</script>
</html>
