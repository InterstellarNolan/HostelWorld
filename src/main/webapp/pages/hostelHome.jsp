<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/15
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <li><a href="#">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/request">开店申请</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/edit">修改申请</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/rooms">所有房间</a></li>
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
<div>
    <table border="1">
        <tr>
            <td>客栈编号</td>
            <td>${hostel.userid}</td>
        </tr>
        <tr>
            <td>客栈状态</td>
            <td>${hostelstate}</td>
        </tr>
        <tr>
            <td>客栈名称</td>
            <td>${hostel.name}</td>
        </tr>
        <tr>
            <td>客栈地址</td>
            <td>${hostel.address}</td>
        </tr>
        <tr>
            <td>客栈电话</td>
            <td>${hostel.phone}</td>
        </tr>
        <tr>
            <td>银行卡号</td>
            <td>${user.bankId}</td>
        </tr>
        <tr>
            <td>银行卡余额</td>
            <td>${user.bankMoney}</td>
        </tr>
        <tr>
            <td>经理未结算金额</td>
            <td>${hostel.moneyUncounted}</td>
        </tr>
        <tr>
            <td>房间数量</td>
            <td>${roomnumber}</td>
        </tr>
    </table>
</div>
</body>
</html>
