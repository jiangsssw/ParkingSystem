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
                        <input type="button" name="delete" value="删除" >
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

    //添加修改的部分...............................>TODO 逻辑混乱需要重新想
    $(".content2")[0].onclick=function (e) {
        var target = e.target.getAttribute("name");
        var parkingValue = e.path[2].children[1].firstChild.data;
        if (target=="update"||target=="delete"){

            //如果当前的parkingId不对等则关闭修改窗口
            if(parkingValue!=parkingIdPoint){
                var reChail =e.path[2].nextElementSibling;
                console.log('parkingIdPoint:'+parkingIdPoint);
                if (reChail.nodeName=="DIV"){
                    e.path[3].removeChild(reChail);
                    return ;
                }
            }
        }
        if (target=="update"){
            parkingIdPoint=parkingValue;
            //判断是否一点击过修改
            if (e.path[2].nextElementSibling.nodeName=="DIV"){
                return;
            }
            var isSub = e.path[2].children[9].firstChild;
           //改车辆预约状态
            var isSubNew = document.createElement("input");
                isSubNew.type="text";
                isSubNew.placeholder=(isSub.data==null?"":isSub.data);
            e.path[2].children[9].replaceChild(isSubNew,isSub);
            //改车辆状态
            var parkingStatus = e.path[2].children[3].firstChild;
            var parkingStatusNew = document.createElement("input");
                parkingStatusNew.type="text";
                parkingStatusNew.placeholder=(parkingStatus.data==null?"":parkingStatus.data);
                e.path[2].children[3].replaceChild(parkingStatusNew,parkingStatus);
            var updateNode = document.createElement("input");
            updateNode.type="button";
            updateNode.value="提交修改";
            var cancelNode = document.createElement("input");
            cancelNode.type="button";
            cancelNode.value="取消";
            var divNode = document.createElement("div");
            divNode.appendChild(cancelNode);
            divNode.appendChild(updateNode);
            var parent = e.path[3];
            parent.insertBefore(divNode,e.path[2].nextElementSibling);
        }
    }

</script>
</html>
