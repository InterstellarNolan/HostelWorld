<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/16
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hostel World</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/hostel/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/request">开店申请</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/edit">修改申请</a></li>
            <li><a href="#">所有房间</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/addroom">新增房间</a></li>

            <li><a href="<%=request.getContextPath()%>/hostel/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/checkIn">旅客入住</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/checkOut">旅客退房结账</a></li>
        </ul>
    </nav>
</div>
<div>
    <table border="2">
        <tr>
            <td>房间编号</td>
            <td>房间类型</td>
            <td>房间定价</td>
            <td>客栈编号</td>
            <td>客栈名称</td>


        </tr>

        <tr>
            <td>${room.id}</td>
            <td>${room.name}</td>
            <td>${room.price}</td>
            <td>${hostel.id}</td>
            <td>${hostel.name}</td>
        </tr>

    </table>
</div>
</body>
</html>
