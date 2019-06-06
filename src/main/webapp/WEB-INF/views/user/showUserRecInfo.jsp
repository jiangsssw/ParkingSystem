<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/25
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showUserRec</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/date.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/moment-with-local.js"/>"></script>
    <script src="<c:url value="/js/bootstrap-dateTime.js"/>"></script>
</head>
<body>

<div class="row col-md-offset-2">
    <form action="/parkingSystem/getUserRecInfo" method="post">
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

    <div class="content">
        <table class="table table-hover">
            <tr>
                <th>用户Id：</th>
                <th>停车位：</th>
                <th>车库:</th>
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
