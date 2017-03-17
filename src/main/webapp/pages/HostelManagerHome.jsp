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
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/HostelManager/home">基本统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/HostelManager/requestOpen">审批开店申请</a></li>
            <li><a href="<%=request.getContextPath()%>/HostelManager/requestModify">审批客栈修改申请</a></li>
            <li><a href="<%=request.getContextPath()%>/HostelManager/count">结算会员卡支付</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
</div>
<h2>您的信息</h2>
<table border="2">
    <tr>
        <td>经理编号</td>
        <td>姓名</td>
        <td>联系电话</td>
        <td>地址</td>
        <td>银行卡号</td>
        <td>银行卡余额</td>
    </tr>

    <tr>
        <td>${managerboss.userId}</td>
        <td>${managerboss.name}</td>
        <td>${managerboss.phone}</td>
        <td>${managerboss.address}</td>
        <td>${uservo.bankId}</td>
        <td>${uservo.bankMoney}</td>
    </tr>
</table>
<h2>HostelWorld的入住记录</h2>
<table border="2">
    <tr>
        <td>客栈编号</td>
        <td>客栈名称</td>
        <td>入住人数</td>
    </tr>
    <c:forEach items="${liveIn}" var="ciitem">
        <tr>
            <td>${ciitem.hostelId}</td>
            <td>${ciitem.name}</td>
            <td>${ciitem.y}</td>
        </tr>
    </c:forEach>
</table>
<div id="livein">总入住人次：${num}</div>
<h2>HostelWorld的付款记录</h2>
<table border="2">
    <tr>
        <td>客栈编号</td>
        <td>客栈名称</td>
        <td>收款金额</td>
    </tr>
    <c:forEach items="${Income}" var="diitem">
        <tr>
            <td>${diitem.hostelId}</td>
            <td>${diitem.name}</td>
            <td>${diitem.value}</td>
        </tr>
    </c:forEach>
</table>
<div id="income">总收款金额：${income}</div>
</body>
</html>
