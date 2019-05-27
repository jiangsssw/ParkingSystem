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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
    <div class="content">
            车库号：<input type="text" name="roomId"/>
            车位号：<input type="text" name="parkingId">
            <input type="button" value="提交" onclick="checkParamAndSend()">
    </div>

    <div class="content2">
        <h4>查询结果</h4>
        <table class="table table-hover">
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
            <tbody class="table-body">
            <c:forEach items="${list}" var="li">
                <tr>
                    <td>${li.roomId}</td>
                    <td>${li.parkingId}</td>
                    <td>${li.userId}</td>
                    <td><a>${li.parkingStatus}</a></td>
                    <td>${li.payType}</td>
                    <td>${li.useStartTime}</td>
                    <td>${li.useType}</td>
                    <td>${li.userCarId}</td>
                    <td>${li.carType}</td>
                    <td><a>${li.isSubscription}</a></td>
                    <td>
                        <input type="button" name="update" value="修改" >
                        <input type="button" matchValue="${li.parkingId}" name="delete" value="删除" >
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

    //定义当前修改窗口的值parkingId;
    var parkingIdPoint=null;
    var openDiv = null;

    //原来的值
    var orgNode1 = null;
    var orgNode2 = null;
    //替换的值
    var repNode1 = null;
    var repNode2 = null;

   // 父节点
    var parentNodeW = null;
    //添加修改的部分...............................>TODO 逻辑混乱需要重新想
    $(".content2")[0].onclick=function (e) {
        var target = e.target.getAttribute("name");
        var parkingValue = e.path[2].children[1].firstChild.data;
        if (target=="update"||target=="delete"){

            //如果当前的parkingId不对等则关闭修改窗口
            if(parkingValue!=parkingIdPoint){

                console.log('parkingIdPoint:'+parkingIdPoint);
                if (parkingIdPoint!=null) {

                    var reChail = openDiv;
                    if (reChail!=null&&reChail.nodeName == "DIV") {
                        if (e.path[3].hasChildNodes(reChail)) {
                            e.path[3].removeChild(reChail);

                            //将原来的值替换回来
                            parentNodeW.children[9].replaceChild(orgNode1,repNode1);
                            parentNodeW.children[3].replaceChild(orgNode2,repNode2);
                            //重新初始化
                            parkingIdPoint=null;
                            openDiv = null;
                            parentNodeW=null;
                            orgNode2=null;
                            repNode2=null;
                            orgNode1=null;
                            repNode1=null;
                        }
                    }
                }
            }
        }
        if (target=="update"){
            parkingIdPoint=parkingValue;
            //判断是否一点击过修改
            var nextBilli = e.path[2].nextElementSibling;
            if (nextBilli!=null&&nextBilli.nodeName=="DIV"&&nextBilli.style.display=="block"){
                return;
            }
            var isSub = e.path[2].children[9].firstChild;
            parentNodeW = e.path[2];
           //改车辆预约状态
            var isSubNew = document.createElement("input");
                isSubNew.type="text";
                isSubNew.placeholder=(isSub.data==null?"":isSub.data);
                orgNode1 = isSub;
                repNode1 = isSubNew;
            e.path[2].children[9].replaceChild(isSubNew,isSub);
            //改车辆状态
            var parkingStatus = e.path[2].children[3].firstChild;
            var parkingStatusNew = document.createElement("input");
                parkingStatusNew.type="text";
                parkingStatusNew.placeholder=(parkingStatus.data==null?"":parkingStatus.data);
                orgNode2 = parkingStatus;
                repNode2 = parkingStatusNew;
                e.path[2].children[3].replaceChild(parkingStatusNew,parkingStatus);
            var updateNode = document.createElement("input");
            updateNode.name="updateSubmit";
            updateNode.type="button";
            updateNode.value="提交修改";
            var cancelNode = document.createElement("input");
            cancelNode.name="cancel";
            cancelNode.type="button";
            cancelNode.value="取消";
            var divNode = document.createElement("div");
            divNode.style.display="block";
            divNode.appendChild(cancelNode);
            divNode.appendChild(updateNode);
            openDiv=divNode;
            var parent = e.path[3];
            parent.insertBefore(divNode,e.path[2].nextElementSibling);
        }

        //监听delete删除

        if(target=="delete"){
            var parkingId = e.target.getAttribute("matchValue");
            console.log("parkingId--->"+parkingId);
            var isSure = confirm("确定要删除吗？？？？");
            if(isSure){
                $.ajax({
                    type : "post",
                    dataType : "json",
                    data : "parkingId="+parkingId,
                    url :"/parkingSystem/deleteParkingInfo",
                    success : function (data) {
                        alert(data.msg);
                        window.location.reload()
                    },
                    error : function (data) {
                        alert(data.msg)
                    }
                });
            }
        }

        //监听提交修改
        if (target=="updateSubmit"){
            console.log(target);
            var submitNode = e.path[1].previousElementSibling.cells;
            var status = submitNode[3].firstChild.value;
            var subcriptStauts = submitNode[9].firstChild.value;
            var parkingId = submitNode[1].firstChild.data;
            if (status==""||subcriptStauts==""){
                alert("修改数据不能为空");
                return ;
            }
            $.ajax({
                type : "POST",
                dataType : "json",
                data : {
                    "parkingId":parkingId,
                    "status" : status,
                    "subscriptionStatus" : subcriptStauts
                },
                url : "/parkingSystem/modifyParkingInfo",
                success : function (data) {
                    alert(data.msg);
                    window.location.reload();
                },
                error : function (error) {
                    alert(error);
                }
            })
        }

        //监听<input name = "cancel>
        if (target=="cancel"){
            e.path[1].style.display="none";
            parentNodeW.children[9].replaceChild(orgNode1,repNode1);
            parentNodeW.children[3].replaceChild(orgNode2,repNode2);
            //重新初始化
            parkingIdPoint=null;
            openDiv = null;
            parentNodeW=null;
            orgNode2=null;
            repNode2=null;
            orgNode1=null;
            repNode1=null;
        }

    }

</script>
</html>
