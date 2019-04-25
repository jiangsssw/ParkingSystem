<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 2019/4/12
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>计费规则管理</title>
    <script src="<c:url value="/js/jquery.js"/>"></script>
</head>
<body>
    <div class="main-content">
        <div class="main-nav">
            <input class="nav" id="selectH" type="button" value="查询历史计费规则" onclick="selectHis()"/>
            <input class="nav" type="button" value="添加新计费规则" onclick="showCalculate()">
        </div>
        <div class="content-show1">
            <div class="showCaHis">
                <table class="" align="center">
                    <tr>
                        <th>每小时</th>
                        <th>每天</th>
                        <th>每周</th>
                        <th>每月</th>
                        <th>每年</th>
                        <th>创建时间</th>
                        <th>创建人</th>
                        <th>创建id</th>
                    </tr>
                    <tbody id="trT">

                    </tbody>
                </table>
            </div>
        </div>

        <div class="content-show2" style="display: none">
            <div>
                <form method="post" action="/parkingSystem/addCalculateRuler">
                每小时收费： <input type="text" name="hourMount" placeholder="<c:out value="${map.hour_mount}"></c:out>"></br>
                每天收费： <input type="text" name="dayMount" placeholder="<c:out value="${map.day_mount}"></c:out>"></br>
                每周收费： <input type="text" name="weekMount" placeholder="<c:out value="${map.week_mount}"></c:out>"></br>
                每月收费： <input type="text" name="monthMount" placeholder="<c:out value="${map.month_mount}"></c:out>"></br>
                每年收费： <input type="text" name="yearMount" placeholder="<c:out value="${map.year_mount}"></c:out>"></br>
                修改人： <input type="text" name="modiflyPeople" placeholder="<c:out value="${map.modefiy_peple}"></c:out>"></br>
                修改id： <input type="text" name="modiflyId" placeholder="<c:out value="${map.modefiy_id}"></c:out>"></br>
                    <input type="submit" value="提交">
                </form>
            </div>
        </div>

    </div>
</body>
<script>
    var selectH = document.querySelector('.showCaHis');
    var html ="";
    var tr="<tr>";
    var trD="</tr>";
    function selectHis() {
        $.ajax({
            url:'/parkingSystem/getCalculateHis?page=1',
            dataType:'json',
            success:function (data) {
                console.log(data)
                var dataArry = data.rulers;

                for (var i = 0;i<dataArry.length;i++){
                    html+=tr+"<td>"+dataArry[i].hour_mount+"</td>"
                        +"<td>"+dataArry[i].day_mount+"</td>"
                        +"<td>"+dataArry[i].week_mount+"</td>"
                        +"<td>"+dataArry[i].month_mount+"</td>"
                        +"<td>"+dataArry[i].year_mount+"</td>"
                        +"<td>"+dataArry[i].creat_time+"</td>"
                        +"<td>"+dataArry[i].modefiy_peple+"</td>"
                        +"<td>"+dataArry[i].modefiy_id+"</td>"
                    +trD;
                }
                document.querySelector("#trT").innerHTML=html;
            },
            error:function (data) {
                console.log(data)
            }

        });
    }
    var open = false;
    var show2 = $(".content-show2");
    function showCalculate() {
        open = !open;
        if (open){
            console.log(show2);
            show2[0].style.display='block';
        }else {
            show2[0].style.display='none';
        }
    }
</script>
</html>
