<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HostelWorld</title>
    <link href="../css/login.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="../css/w3.css">
</head>
<body>
<div class="login" commandName="user" role="form">
    <form:form>
        <li>
            <h2>登录失败</h2>
            <hr class="hr15">
            <h2 id="message">${message}</h2>
            <hr class="hr15">
            <a href="/login" style="font: 13px/1.5 '微软雅黑', Verdana, Helvetica, Arial, sans-serif;">返回登录</a>

        </li>
    </form:form>


</div>
</body>
</html>
