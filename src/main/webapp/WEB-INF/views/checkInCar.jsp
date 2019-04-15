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
    <title>Title</title>
</head>
<body>
<%--private String phoneId;--%>
<%--private String userName;--%>
<%--private String userCarId;--%>
<%--private String carType;--%>
<%--private String carStatus;--%>
    <div class="content">
        <form action="/parkingSystem/CarInformationLogin" method="post">
            手机号：<input type="text" name="phoneId"/>
            用户姓名：<input type="text" name="userName"/>
            车牌号：<input type="text" name="userCarId"/>
            车辆类型：<select name="carType">
            <option value="01">小轿车</option>
            <option value="02">SUV</option>
            <option value="03">超级跑车</option>
            <option value="04">面包车</option>
            <option value="05">小货车</option>
            <option value="06">中型货车及以上</option>
        </select>
            <input type="submit" value="提交"/>
        </form>
    </div>
</body>
</html>
