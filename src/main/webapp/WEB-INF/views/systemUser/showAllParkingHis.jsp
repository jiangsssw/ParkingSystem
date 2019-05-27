<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/24
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>

<%--private int userId;--%>
<%--private String userCarId;--%>
<%--private String phoneId;--%>
<%--private String startTime;--%>
<%--private String endTime;--%>
<%--private String carType;--%>
<%--private int startCount;--%>

    <div class="content">
        <div class="header">
            <form action="/parkingSystem/getAllParkingRecHisInfo" method="post">
                用户id:<input type="text" name="userId">
                用户车牌号:<input type="text" name="userCarId">
                手机号:<input type="text" name="phoneId">
                开始时间:<input type="text" name="startTime">
                结束时间:<input type="text" name="endTime">
                车辆类型:<input type="text" name="carType">
                <input type="submit" value="查询">
            </form>
        </div>


        <h3>停车记录历史查询</h3>
        <table class="table table-hover">
            <tr>
                <th>用户</th>
                <th>车牌号</th>
                <th>车辆类型</th>
                <th>用户id</th>
                <th>车库号</th>
                <th>车位id</th>
                <th>支付方式</th>
                <th>停车开始时间</th>
                <th>停车结束时间</th>
                <th>停车时间</th>
                <th>停车类型</th>
            </tr>
            <tbody>
                <c:forEach items="${recHis}" var="map">
                    <tr>
                    <td>${map.userName}</td>
                    <td>${map.userCarId}</td>
                    <td>${map.carType}</td>
                    <td>${map.userId}</td>
                    <td>${map.carRoomNumber}</td>
                    <td>${map.carParkingId}</td>
                    <td>${map.payType}</td>
                    <td>${map.parkingStartTime}</td>
                    <td>${map.parkingEndTime}</td>
                    <td>${map.parkingTime}</td>
                    <td>${map.parkingType}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
