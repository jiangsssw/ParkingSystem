<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/15
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车辆登记</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<%--private String phoneId;--%>
<%--private String userName;--%>
<%--private String userCarId;--%>
<%--private String carType;--%>
<%--private String carStatus;--%>
    <div class="content col-md-offset-2">
        <form class="form-horizontal" role="form" action="/parkingSystem/CarInformationLogin" method="post">
            <div class="row">
            <div class="col-md-4" style="margin-left: 50px">
            用户姓名：<input class="form-control" type="text" name="userName"/>
            </div>
                <div class="col-md-4 visible-md-inline-block" style="margin-right: 50px">
            车牌号：<input class="form-control" type="text" name="userCarId"/>
                </div>
                    <div class="col-md-4" style="margin-left: 50px">
            车辆类型：<select class="form-control" name="carType">
            <option value="01">小轿车</option>
            <option value="02">SUV</option>
            <option value="03">超级跑车</option>
            <option value="04">面包车</option>
            <option value="05">小货车</option>
            <option value="06">中型货车及以上</option>
        </select>
                    </div>
            </div>
                        <div class="row col-md-3 pull-right" style="margin-top: 10px">
            <input class="btn btn-primary" type="submit" value="添加"/>
                        </div>
        </form>
    </div>
<jsp:include page="showCarInformation.jsp"/>
</body>
</html>
