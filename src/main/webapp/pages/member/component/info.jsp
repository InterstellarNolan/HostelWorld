<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="../../../css/member_selfPanel.css"/>
</head>
<body>
<div >
    <img class="vip-avatar" src="../../../img/head.jpg" alt="头像" id="avatar">
    <table>
        <tbody>
        <tr><td>用户名：</td><td>${member.realName}</td></tr>
        <tr><td>卡余额：</td><td>${member.moneyLeft}元</td></tr>
        <tr><td>等级：</td><td>${member.level}</td></tr>
        <tr><td>积分：</td><td>${member.score}</td></tr>
        <tr><td>已消费：</td><td>${member.moneyPaid}元</td></tr>
        <tr><td>状态：</td><td>${member.state.toChineseString()}</td></tr>
        </tbody>
    </table>

</div>
</body>
</html>
