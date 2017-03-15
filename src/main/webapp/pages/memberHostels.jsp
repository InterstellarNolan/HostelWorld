<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/15
  Time: 13:56
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
            <li><a href="<%=request.getContextPath()%>/member/home">基本信息</a></li>
            <li><a href="#">看看客栈</a></li>
            <li><a href="<%=request.getContextPath()%>/member/unbook">退订房间</a></li>
            <li><a href="<%=request.getContextPath()%>/member/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/edit">修改资料</a></li>
            <li><a href="<%=request.getContextPath()%>/member/charge">会员卡充值</a></li>
        </ul>
    </nav>
</div>

<table border="2">
    <tr>
        <td>客栈编号</td>
        <td>客栈名称</td>
        <td>客栈地址</td>
        <td>联系电话</td>
        <td>房间数量</td>

    </tr>
    <c:forEach items="${hostellist}" var="item">
        <tr>
            <td><a href="<%=request.getContextPath()%>/hostel/home/${item.id}">${item.userID}</a></td>
            <td>${item.name}</td>
            <td>${item.address}</td>
            <td>${item.phone}</td>
            <td>${item.roomnumber}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
