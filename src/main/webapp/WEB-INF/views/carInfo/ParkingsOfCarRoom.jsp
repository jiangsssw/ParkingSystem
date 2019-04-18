<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/18
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show</title>
    <script src="<c:url value="/js/jquery.js"/>"></script>
</head>
<body>
    <div class="content">
            车库号：<input type="text" name="roomId"/>
            车位号：<input type="text" name="parkingId">
            <input type="button" value="提交" onclick="checkParamAndSend()">
    </div>

    <div class="content2">
        <h4>查询结果</h4>
        <table>
            <tr>
                <th>车库号</th>
                <th>车位号</th>
                <th>用户</th>
                <th>车位空闲状态</th>
                <th>车位商用形式</th>
                <th>用户使用时间</th>
                <th>租用形式</th>
                <th>用户车牌号</th>
                <th>车辆类型</th>
                <th>预约状态</th>
                <th>操作</th>
            </tr>
            <tbody>
            <c:forEach items="${list}" var="li">
                <tr>
                    <td>${li.roomId}</td>
                    <td>${li.parkingId}</td>
                    <td>${li.userId}</td>
                    <td>${li.parkingStatus}</td>
                    <td>${li.payType}</td>
                    <td>${li.useStartTime}</td>
                    <td>${li.useType}</td>
                    <td>${li.userCarId}</td>
                    <td>${li.carType}</td>
                    <td>${li.isSubscription}</td>
                    <td>
                        <input type="button" value="修改" >
                        <input type="button" value="删除" >
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</body>
<script>
    function checkParamAndSend() {
        var roomId = $("[name='roomId']")[0].value;
        var parkingId = $("[name='parkingId']")[0].value;
        console.log("roomId"+roomId+"parking"+parkingId);
        if (roomId==""&&parkingId==""){
            alert("车库号和车位编号必须输入一个")
        }
        if (parkingId==""){
            location.href="/parkingSystem/getParkingsOfCarRoom?roomId="+roomId+"&parkingId=";
        }else {
            location.href="/parkingSystem/getParkingsOfCarRoom?roomId=0&parkingId="+parkingId;
        }
    }

    //添加修改的部分


</script>
</html>
