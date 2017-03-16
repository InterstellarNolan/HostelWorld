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
    <title>HostelWorld</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/member/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/hostels">看看客栈</a></li>
            <li><a href="<%=request.getContextPath()%>/member/unbook">退订房间</a></li>
            <li><a href="<%=request.getContextPath()%>/member/analysis">统计信息</a></li>
            <li><a href="#">修改资料</a></li>
            <li><a href="<%=request.getContextPath()%>/member/charge">会员卡充值</a></li>
        </ul>
    </nav>
</div>
<div>
    <a href="/member/stop">停止会员卡资格</a>
    <form action="<%=request.getContextPath()%>/member/edit" method="post">
        <table border="2">
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name" value="${member.realName}"></td>
            </tr>
            <tr>
                <td>银行卡号</td>
                <td><input type="text" name="bankcard" value="${user.bankId}"></td>
            </tr>
            <tr>
                <td>银行卡余额</td>
                <td><input type="text" readonly="readonly" value="${user.bankMoney}"></td>
            </tr>
            <tr>
                <td>会员卡余额</td>
                <td><input type="text" readonly="readonly" value="${member.moneyLeft}"></td>
            </tr>
            <tr>
                <td>等级</td>
                <td><input type="text" readonly="readonly" value="${member.level}"></td>
            </tr>
            <tr>
                <td>积分</td>
                <td><input type="text" readonly="readonly" value="${member.score}"></td>
            </tr>
            <tr>
                <td>会员卡状态</td>
                <td><input type="text" readonly="readonly" value="${member.state}"></td>
            </tr>
        </table>
        <input type="submit" value="更改">
    </form>

    <div id="message1">${message1}</div>
    <div id="message2">${message2}</div>
</div>
</body>
</html>
