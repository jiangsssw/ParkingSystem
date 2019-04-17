<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/17
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<c:url value="/js/jquery.js"/>"></script>
</head>
<body>
<div class="content">
    <table>
        <tr>
            <th>车库号</th>
            <th>车位数量</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        <tbody class="reflsh">
            <c:forEach items="${carList}" var="list">
                <tr>
                    <td>${list.car_room_number}</td>
                    <td>${list.car_parking_num}</td>
                    <td>${list.EXT1}</td>
                    <td><input type="button" value="删除"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script>
    document.querySelector(".reflsh").onclick=function (e) {
        if (e.target.nodeName=="INPUT"){
            var trNode =e.path[2];
            var data = trNode.children[0].firstChild.data;
            $.ajax({
                type : "POST",
                dataType : "json",
                data : "roomId="+data,
                url : "/parkingSystem/deleteRoomId",
                success : function (data) {
                    alert(data.msg);
                    if(data.code==0){
                        //操作成功
                        window.location.reload();
                    }
                }
            });
        }
    }
</script>
</html>
