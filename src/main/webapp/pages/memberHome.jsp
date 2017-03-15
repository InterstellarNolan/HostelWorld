<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 19:39
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
            <li><a href="#">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/hostels">看看客栈</a></li>
            <li><a href="<%=request.getContextPath()%>/member/unbook">退订房间</a></li>
            <li><a href="<%=request.getContextPath()%>/member/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/edit">修改资料</a></li>
            <li><a href="<%=request.getContextPath()%>/member/charge">会员卡充值</a></li>
        </ul>
    </nav>
</div>
<div>
    <table border="1">
        <tr>
            <td>会员卡号</td>
            <td>${member.idCard}</td>
        </tr>
        <tr>
            <td>姓名</td>
            <td>${member.realName}</td>
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
            <td>会员卡余额</td>
            <td>${member.moneyLeft}</td>
        </tr>
        <tr>
            <td>会员卡累计消费</td>
            <td>${member.moneyPaid}</td>
        </tr>
        <tr>
            <td>当前可享折扣</td>
            <td>${discount}</td>
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
            <td>会员卡状态</td>
            <td>${member.state}</td>
        </tr>
    </table>
</div>
</body>
</html>
