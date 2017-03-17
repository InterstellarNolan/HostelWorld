<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <link href="../css/login.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="../css/w3.css">
    <link>
</head>
<body>
<div class="login">
    <div class="message">HostelWorld</div>
    <div id="bannerwrap"></div>
    <form action="/register/manager" method="POST">

        <input name="action" value="login" type="hidden">
        <input type="text" placeholder="用户名" required='true' id='userName' name='userName' value="${userName}"/>
        <hr class="hr15">
        <input type="password" placeholder="密码" required='true' id='password' name="password" value="${password}"/>
        <hr class="hr15">
        <input id="btnLogin" type="submit" value="注册"/>
        <hr class="hr15">
    </form>
    <div id="nav">
        <ul>
            <li><a href="/login">去登录</a></li>
        </ul>
    </div>
    <div id="message">${message}</div>
</div>


</body>
</html>
