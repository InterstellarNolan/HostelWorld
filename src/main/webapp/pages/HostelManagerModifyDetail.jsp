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

<h2>开店申请</h2>
<div>
    <form action="<%=request.getContextPath()%>/HostelManager/requestModify/${Modifylist.id}" method="post">
        <table border="2">
            <tr>
                <td>原客栈名称 ${Modifylist.name_original}</td>
                <td>现客栈名称 ${Modifylist.name_new}</td>
            </tr>
            <tr>
                <td>原客栈地址 ${Modifylist.address_original}</td>
                <td>现客栈地址 ${Modifylist.address_new}</td>
            </tr>
            <tr>
                <td>原客栈电话 ${Modifylist.phone_original}</td>
                <td>现客栈电话 ${Modifylist.phone_new}</td>
            </tr>
            <tr>
                <td>是否批准</td>
                <td><input type="text" name="state" placeholder="请输入approved或denied"></td>
            </tr>
        </table>
        <input type="submit" value="完成审批">
    </form>
</div>

</body>
</html>
