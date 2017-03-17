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
<div>
    <form action="<%=request.getContextPath()%>/HostelManager/count" method="post">
        <table border="2">
            <tr>
                <td>您的编号：</td>
                <td><input type="text" readonly="readonly" value="${IMmanager.userId}"></td>
            </tr>
            <tr>
                <td>请输入银行卡密码：</td>
                <td><input type="password" name="password" placeholder="注意不是登录密码"></td>
            </tr>
        </table>
        <input type="submit" value="全部结算">
    </form>
</div>
<div id="message">${message}</div>
</body>
</html>
