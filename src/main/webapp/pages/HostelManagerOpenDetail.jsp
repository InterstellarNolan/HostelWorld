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

<h2>开店申请</h2>
<div>
    <form action="<%=request.getContextPath()%>/HostelManager/requestOpen/${Openlist.id}" method="post">
        <table border="2">
            <tr>
                <td>客栈编号</td>
                <td><input type="text" readonly="readonly" value="${hostel.userid}"></td>
            </tr>
            <tr>
                <td>客栈名称</td>
                <td><input type="text" readonly="readonly" value="${hostel.name}"></td>
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
