<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/16
  Time: 22:37
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
            <li><a href="<%=request.getContextPath()%>/HostelManager/home">基本统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/HostelManager/requestOpen">审批开店申请</a></li>
            <li><a href="<%=request.getContextPath()%>/HostelManager/requestModify">审批客栈修改申请</a></li>
            <li><a href="<%=request.getContextPath()%>/HostelManager/count">结算会员卡支付</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
                </section>
            </div>
        </div>
    </div>
</div>

<h2>HostelWorld的开店申请</h2>
<table border="2">
    <tr>
        <td>客栈编号</td>
        <td>客栈名称</td>
        <td>客栈电话</td>
        <td>客栈地址</td>

    </tr>
    <c:forEach items="${Openlist}" var="ciitem">
        <tr>
            <td>
                <a href="<%=request.getContextPath()%>/HostelManager/requestOpenDetail/${ciitem.id}">${ciitem.hostel_lookid}</a>
            </td>
            <td>${ciitem.hostel_name}</td>
            <td>${ciitem.hostel_phone}</td>
            <td>${ciitem.hostel_address}</td>
        </tr>
    </c:forEach>
</table>
<div id="message">${message}</div>
</body>
</html>
