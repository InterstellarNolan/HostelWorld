<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hostel World</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/hostel/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/hostel/request">开店申请</a></li>
            <li><a href="#">修改申请</a></li>
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
    <form action="<%=request.getContextPath()%>/hostel/edit" method="post">
        <table border="2">
            <tr>
                <td>客栈名称</td>
                <td><input type="text" name="name" value="${hostel.name}"></td>
            </tr>
            <tr>
                <td>客栈地址</td>
                <td><input type="text" name="address" value="${hostel.address}"></td>
            </tr>
            <tr>
                <td>客栈联系电话</td>
                <td><input type="text" name="phone" value="${hostel.phone}"></td>
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
                <td>会员卡未结算金额</td>
                <td><input type="text" readonly="readonly" value="${hostel.moneyUncounted}"></td>
            </tr>
        </table>
        <input type="submit" value="修改申请">
    </form>

    <div id="message">${message}</div>

</div>
</body>
</html>
