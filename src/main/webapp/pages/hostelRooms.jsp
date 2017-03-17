<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/15
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hostel World</title>
    <link rel="stylesheet" href="../css/style-desktop.css"/>
</head>
<body>
<!-- Header Wrapper -->
<div id="header-wrapper">
    <div class="container">
        <div class="row">
            <div class="12u">
                <!-- Header -->
                <section id="header">
                    <!-- Logo -->
                    <nav id="nav">
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
                </section>
            </div>
        </div>
    </div>
</div>
<h2>房间可用</h2>
<table border="2">
    <tr>
        <td>房间编号</td>
        <td>房间类型</td>
        <td>房间价格</td>
    </tr>
    <c:forEach items="${roomList}" var="item">
        <tr>
            <td><a href="<%=request.getContextPath()%>/hostel/room/${item.roomId}">${item.roomId}</a></td>
            <td>${item.name}</td>
            <td>${item.price}</td>
        </tr>
    </c:forEach>
</table>
<h2>房间暂不可用</h2>
<table border="2">
    <tr>
        <td>房间编号</td>
        <td>房间类型</td>
        <td>房间价格</td>
    </tr>
    <c:forEach items="${InvalidroomList}" var="iitem">
        <tr>
            <td><a href="<%=request.getContextPath()%>/hostel/roomInvalid/${iitem.roomId}">${iitem.roomId}</a></td>
            <td>${iitem.name}</td>
            <td>${iitem.price}</td>
        </tr>
    </c:forEach>
</table>
<div>注意，请不要启用有客人入住的房间，具体住宿情况请在统计信息里查询</div>
</body>
</html>
