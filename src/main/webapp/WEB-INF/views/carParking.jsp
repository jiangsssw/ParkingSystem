<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/15
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车位信息管理</title>
    <script src="<c:url value="/js/jquery.js"/>"></script>
</head>
<body>
    <div class="content">
        <div>
            <ul>
                <li>车库号：<input id="roomId" type="number" name="roomId"/></li>
                <li>车辆数量：<input id="parkingNum" type="number" name="parkingNum"/></li>
                <li>备注： <input id="remark" type="text" name="remark"/></li>
            </ul>
            <input type="button" value="添加" onclick="addCarRoom()" >
        </div>

    </div>
</body>
<script>
    var roomId = $("#roomId")[0].value;
    var parkingNum = $("#parkingNum")[0].value;
    var remark = $("#remark")[0].value;
    function addCarRoom() {
        console.log("value      "+roomId+"v   "+parkingNum+"remark   "+remark);
        $.ajax({
            type : 'post',
            url : '/parkingSystem/addCarRoom',
            dataType:'json',
            data: {
                "roomId" : roomId,
                "parkingNum" : parkingNum,
                "remark" : remark
            },
            success : function (data) {
                console.log("success data"+data);
            },
            error : function (error) {
                console.log("error data"+data);
            }
        })
    }
</script>
</html>
