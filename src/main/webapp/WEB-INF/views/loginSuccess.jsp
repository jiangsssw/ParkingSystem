<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/css/test.css"/> "/>--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="panel panel-info">
<div class="panel-heading">
计费规则
</div>
    <div class="panel-body">
停车时间=停车结束时间-停车结束时间<br/>
费用计算规则：临时车辆不满一天则按小时计费，超过一天则按天计费多出的部分按小时计费<br/>
临时车辆 超过一周按周计费，其余不满一周的另外按天收费<br/>
超过一月按月计费，其余不满月的按周计费<br/>
超过一年的按年计费，其余的按月计费<br/>
所有临时车辆凡是超过一天的 费用在原基础上再加10%<br/>
        <br/>
预约车辆停按照上面临时车辆计费规则，并低原元费用5%
    </div>
</div>
</body>
</html>