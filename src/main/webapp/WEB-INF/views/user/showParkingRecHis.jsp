<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/25
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showParkingHis</title>
</head>
<body>
<h3>停车记录历史查询</h3>
<table>
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

</body>
</html>
