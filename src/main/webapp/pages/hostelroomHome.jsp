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
            <li><a href="<%=request.getContextPath()%>/hostel/checkOut">旅客退房</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/pay">旅客结账</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
</div>
<div>
    <form action="<%=request.getContextPath()%>/hostel/room/${room.id}" method="post">
        <table border="2">
            <tr>
                <td>房间编号</td>
                <td><input type="text" readonly="readonly" value="${room.id}"></td>
            </tr>
            <tr>
                <td>房间类型</td>
                <td><input type="text" name="name" value="${room.name}"></td>
            </tr>
            <tr>
                <td>房间价格</td>
                <td><input type="text" name="price" value="${room.price}"></td>
            </tr>
        </table>
        <input type="submit" value="更改房间">
    </form>

    <div id="message">${message}</div>
    <a href="<%=request.getContextPath()%>/hostel/rooms">返回房间列表</a>
    <a href="<%=request.getContextPath()%>/hostel/roomPaused/${room.id}">停用房间</a>
</div>
</body>
</html>
