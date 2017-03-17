<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hostel World</title>
    <link rel="stylesheet" href="../css/style-desktop.css"/>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/member/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/hostels">看看客栈</a></li>
            <li><a href="<%=request.getContextPath()%>/member/unbook">退订房间</a></li>
            <li><a href="<%=request.getContextPath()%>/member/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/edit">修改资料</a></li>
            <li><a href="#">会员卡充值</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
</div>
<form action="<%=request.getContextPath()%>/member/charge" method="post">
    <table border="2">
        <tr>
            <td>会员卡余额</td>
            <td>${member.moneyLeft}</td>
        </tr>
        <tr>
            <td>等级</td>
            <td>${member.level}</td>
        </tr>
        <tr>
            <td>积分</td>
            <td>${member.score}</td>
        </tr>
        <tr>
            <td>充值金额</td>
            <td><input type="text" name="amount" value="0"></td>
        </tr>
        <tr>
            <td>兑换积分</td>
            <td><input type="text" name="score" value="0"></td>
        </tr>
        <tr>
            <td>银行卡密码</td>
            <td><input type="password" name="bankpassword"></td>
        </tr>
    </table>
    <input type="submit" value="充值">
    <div id="message1">100积分兑换1元</div>
    <div id="message">${message}</div>
</form>
</body>
</html>
