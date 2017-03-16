<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <li><a href="<%=request.getContextPath()%>/hostel/rooms">所有房间</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/addroom">新增房间</a></li>
            <li><a href="#">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/checkIn">旅客入住</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/checkOut">旅客退房</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/pay">旅客结账</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
</div>
<h2>本客栈的房间订房记录</h2>
<table border="2">
    <tr>
        <td>会员编号</td>
        <td>客栈名称</td>
        <td>客栈地址</td>
        <td>订房日期</td>
        <td>入住日期</td>
        <td>房间类型</td>
        <td>房间价格</td>
        <td>订单状态</td>
    </tr>
    <c:forEach items="${bookbillVO}" var="bitem">
        <tr>
            <td>${bitem.vipId}</td>
            <td>${bitem.hostelName}</td>
            <td>${bitem.hostelAddress}</td>
            <td>${bitem.createDate}</td>
            <td>${bitem.liveInDate}</td>
            <td>${bitem.roomName}</td>
            <td>${bitem.roomPrice}</td>
            <td>${bitem.valid}</td>
        </tr>
    </c:forEach>
</table> 
<h2>在本客栈消费的的付款记录</h2>
<table border="2">
    <tr>
        <td>会员编号</td>
        <td>客栈名称</td>
        <td>客栈地址</td>
        <td>付款日期</td>
        <td>房间类型</td>
        <td>房间价格</td>
        <td>实付金额</td>
    </tr>
    <c:forEach items="${paybillVO}" var="pitem">
        <tr>
            <td>${pitem.vipId}</td>
            <td>${pitem.hostelName}</td>
            <td>${pitem.hostelAddress}</td>
            <td>${pitem.createDate}</td>
            <td>${pitem.roomName}</td>
            <td>${pitem.roomPrice}</td>
            <td>${pitem.money}</td>
        </tr>
    </c:forEach>
</table>
<h2>本客栈房间的入住记录</h2>
<table border="2">
    <tr>
        <td>会员编号</td>
        <td>客栈名称</td>
        <td>客栈地址</td>
        <td>客栈编号</td>
        <td>房间类型</td>
        <td>房间价格</td>
        <td>入住日期</td>
    </tr>
    <c:forEach items="${checkInbillVO}" var="ciitem">
        <tr>
            <td>${citem.vipId}</td>
            <td>${ciitem.hostelName}</td>
            <td>${ciitem.hostelAddress}</td>
            <td>${ciitem.hostelId}</td>
            <td>${ciitem.roomName}</td>
            <td>${ciitem.roomPrice}</td>
            <td>${ciitem.date}</td>
        </tr>
    </c:forEach>
</table>

<h2>本客栈房间的退房记录</h2>
<table border="2">
    <tr>
        <td>会员编号</td>
        <td>客栈名称</td>
        <td>客栈地址</td>
        <td>客栈编号</td>
        <td>房间类型</td>
        <td>房间价格</td>
        <td>退房日期</td>
    </tr>
    <c:forEach items="${checkOutbillVO}" var="coitem">
        <tr>
            <td>${coitem.vipId}</td>
            <td>${coitem.hostelName}</td>
            <td>${coitem.hostelAddress}</td>
            <td>${coitem.hostelId}</td>
            <td>${coitem.roomName}</td>
            <td>${coitem.roomPrice}</td>
            <td>${coitem.date}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
