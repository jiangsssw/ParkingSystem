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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <style>
        li{
            list-style: none;
        }
    </style>
</head>
<body>
    <div class="panel panel-info">
        <div class="panel-heading">
        <h4>添加车库</h4>
        </div>
        <div class="panel-body">
            <ul>
                <li>车库号：<input id="roomId" type="number" name="roomId"/></li>
                <li>车辆数量：<input id="parkingNum" type="number" name="parkingNum"/></li>
                <li>车库地址： <input id="remark" type="text" name="remark"/></li>
            </ul>
            <input class="btn btn-success" type="button" value="添加" onclick="addCarRoom()">
        </div>

    </div>
    <%--添加车位--%>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h4>添加车库</h4>
        </div>
        <div class="panel-body">
        <ul>
            <li>所属车库：<select id="roomNOV">
                <option selected="selected">请选择</option>
                <c:forEach items="${roomList}" var="list">
                    <option value="${list}">${list}</option>
                </c:forEach>
            </select>车库</li>
            <li>车位号：<input id="rooNumber" type="text" name="parkingId"/></li>
            <li>车位状态<select id="parkingStatus">
                <option selected="selected" value="01">空闲</option>
                <option  value="02">占用</option>
                <option  value="03">毁坏</option>
                <option  value="04">其他</option>
            </select></li>
            <li><input class="btn btn-success" type="button" value="添加" onclick="addParkingCar()"/></li>
        </ul>
        </div>
    </div>
    <div class="panel panel-success">
        <div class="panel-heading">
            <h4>添加信息展示</h4>
        </div>
        <div class="panel-body">
    <iframe class="dealWith" src="/parkingSystem/getAllCarRoom" width="700px">

    </iframe>
        </div>
    </div>
</body>
<script>

    function addParkingCar() {
        var roomId = $("#roomNOV")[0].value;
        var rooNumber = $("#rooNumber")[0].value;
        var parkingStatus = $("#parkingStatus")[0].value;
//        console.log("roomId"+roomId+"------"+rooNumber+"-----"+parkingStatus);
        if(roomId=="请选择"||rooNumber==""){
            alert("请填写所属车库和车位号")
        }
        $.ajax({
            type:'POST',
            dataType : "json",
            url : "/parkingSystem/addParkingInformation",
            data : {
               "roomId" : roomId,
                "parkingId" : rooNumber,
                "status" : parkingStatus
            },
            success:function (d) {
                if (d.code==0){
                    alert(d.msg);
                    //<iframe src="/parkingSystem/getAllCarRoom">
                    $(".dealWith")[0].src="/parkingSystem/getParkingsOfCarRoom?roomId=0&parkingId="+rooNumber;
                }else {
                    alert(d.msg);
                    return ;
                }
            },
            error : function (e) {
                alert(e)
            }

        });
    }
    function addCarRoom() {
        var roomId = $("#roomId")[0].value;
        var parkingNum = $("#parkingNum")[0].value;
        var remark = $("#remark")[0].value;
        if (roomId==""||parkingNum==""){
            alert("车位号或车位数量不能为空")
        }
        $.ajax({
            type : 'POST',
            url : '/parkingSystem/addCarRoom',
            dataType : "json",
            data: {
                "roomId" : roomId,
                "parkingNum" : parkingNum,
                "remark" : remark
            },
            success : function (data) {
                if (data.code==0){
                    alert(data.msg);
                }
                window.location.reload();
            },
            error : function (error) {
                console.log("error data"+error);
            }
        })
    }
</script>
</html>
