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

<h2>HostelWorld的开店申请</h2>
<table border="2">
    <tr>
        <td>客栈编号</td>
        <td>客栈名称</td>
        <td>客栈电话</td>
        <td>客栈地址</td>

    </tr>
    <c:forEach items="${Modifylist}" var="ciitem">
        <tr>
            <td>
                <a href="<%=request.getContextPath()%>/HostelManager/requestModifyDetail/${ciitem.id}">${ciitem.hostelLookId}</a>
            </td>
            <td>${ciitem.name_original}</td>
            <td>${ciitem.phone_original}</td>
            <td>${ciitem.address_original}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
