<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/5/8
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车辆外出（车辆结算）</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
    <div class="content">
        <div class="panel panel-info">
            <div class="panel-heading">
        <h4>模拟车辆入库</h4>
            </div>
            <div class="panel-body">

        <form action="/parkingSystem/carIn" method="post">
            <h5>请输入车辆车牌号</h5>
            <input type="text" name="carId">
            <input class="btn btn-success" type="submit" value="提交">
        </form>
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4>模拟车辆出库</h4>
            </div>
            <div class="panel-body">
                <form action="/parkingSystem/outOfCarFromParking" method="post">
                    <h5>请输入车辆车牌号</h5>
                    <input type="text" name="carId">
                    <input class="btn btn-success" type="submit" value="提交">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
