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
        <div class="content-show">
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

                </table>
            </div>
        </div>

    </div>
</body>
<script>
    var selectH = document.querySelector('.showCaHis');
    function selectHis() {
        $.ajax({
            url:'/parkingSystem/getCalculateHis?page=1',
            dataType:'json',
            success:function (data) {
                console.log(data);
            }
        });
    }
    function showCalculate() {

    }
</script>
</html>
