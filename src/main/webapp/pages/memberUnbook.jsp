<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/15
  Time: 19:48
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
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/member/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/hostels">看看客栈</a></li>
            <li><a href="#">退订房间</a></li>
            <li><a href="<%=request.getContextPath()%>/member/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/edit">修改资料</a></li>
            <li><a href="<%=request.getContextPath()%>/member/charge">会员卡充值</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
</div>

<table border="2">
    <tr>
        <td>客栈名称</td>
        <td>客栈地址</td>
        <td>订房日期</td>
        <td>入住日期</td>
        <td>房间类型</td>
        <td>房间价格</td>
        <td>房间已订</td>
    </tr>
    <c:forEach items="${truelist}" var="bitem">
        <tr>
            <td>${bitem.hostelName}</td>
            <td>${bitem.hostelAddress}</td>
            <td>${bitem.createDate}</td>
            <td>${bitem.liveInDate}</td>
            <td>${bitem.roomName}</td>
            <td>${bitem.roomPrice}</td>
            <td><a href="<%=request.getContextPath()%>/member/unbook/${bitem.id}">退订，订金20元将退回会员卡</a></td>
        </tr>
    </c:forEach>
</table>
<div id="message">${message}</div>
</body>
</html>
