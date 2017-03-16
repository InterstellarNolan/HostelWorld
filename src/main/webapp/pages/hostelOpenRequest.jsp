<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/16
  Time: 16:21
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
            <li><a href="<%=request.getContextPath()%>/hostel/home">基本信息</a></li>
            <li><a href="#">开店申请</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/edit">修改申请</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/rooms">所有房间</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/addroom">新增房间</a></li>

            <li><a href="<%=request.getContextPath()%>/hostel/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/checkIn">旅客入住</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/checkOut">旅客退房结账</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
</div>
<div>

    <form action="<%=request.getContextPath()%>/hostel/request" method="post">
        <table border="2">
            <tr>
                <td>客栈名称</td>
                <td><input type="text" readonly="readonly" value="${hostel.name}"></td>
            </tr>
            <tr>
                <td>客栈地址</td>
                <td><input type="text" readonly="readonly" value="${hostel.address}"></td>
            </tr>
            <tr>
                <td>客栈联系电话</td>
                <td><input type="text" readonly="readonly" value="${hostel.phone}"></td>
            </tr>
            <tr>
                <td>银行卡号</td>
                <td><input type="text" readonly="readonly" value="${user.bankId}"></td>
            </tr>
            <tr>
                <td>银行卡余额</td>
                <td><input type="text" readonly="readonly" value="${user.bankMoney}"></td>
            </tr>
            <tr>
                <td>总房间数量</td>
                <td><input type="text" readonly="readonly" value="${roomNum}"></td>
            </tr>
            <tr>
                <td>可用房间数量</td>
                <td><input type="text" readonly="readonly" value="${validroomNum}"></td>
            </tr>
            <tr>
                <td>开业申请状态</td>
                <td><input type="text" readonly="readonly" value="${state}"></td>
            </tr>
        </table>
        <input type="submit" value="申请开业">
    </form>

    <div id="message">${message}</div>

</div>
</body>
</html>
