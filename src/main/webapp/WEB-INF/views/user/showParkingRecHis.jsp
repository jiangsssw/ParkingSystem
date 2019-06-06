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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/date.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/moment-with-local.js"/>"></script>
    <script src="<c:url value="/js/bootstrap-dateTime.js"/>"></script>
</head>
<body>

<div class="row col-md-offset-2">
    <form action="/parkingSystem/getParkingRecHis" method="post">
    <div class='col-sm-3'>
        <div class="form-group">
            <label>选择起始日期：</label>
            <!--指定 date标记-->
            <div class='input-group date' id='datetimepicker1'>
                <input type='text' name="startTime" class="form-control" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class='col-sm-3'>
        <div class="form-group">
            <label>选择结束日期：</label>
            <!--指定 date标记-->
            <div class='input-group date' id='datetimepicker2'>
                <input type='text' name="endTime" class="form-control" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
        <div class="col-sm-3" style="margin-top: 24px;margin-left: 50px;">
            <input class="btn btn-primary" type="submit" value="查询"/>
        </div>
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

</body>
<script>
    var da = new Date().getTime()-1000*60*60*24*30;
    $(function () {
        $('#datetimepicker1').datetimepicker({
            format: 'YYYY-MM-DD HH:mm',
            locale: moment.locale('zh-cn'),
            defaultDate: new Date(da)
        });
        $('#datetimepicker2').datetimepicker({
            format: 'YYYY-MM-DD HH:mm',
            locale: moment.locale('zh-cn'),
            defaultDate: Date.now()
        });
    });
</script>
</html>
