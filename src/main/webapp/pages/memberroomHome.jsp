<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/16
  Time: 12:40
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
            <li><a href="#">看看客栈</a></li>
            <li><a href="<%=request.getContextPath()%>/member/unbook">退订房间</a></li>
            <li><a href="<%=request.getContextPath()%>/member/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/member/edit">修改资料</a></li>
            <li><a href="<%=request.getContextPath()%>/member/charge">会员卡充值</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
        </ul>
    </nav>
</div>
<div>
    <form action="<%=request.getContextPath()%>/member/book">
        <table border="2">
            <tr>
                <td>会员编号</td>
                <td><input type="text" readonly="readonly" value="${member.userid}"></td>
            </tr>
            <tr>
                <td>房间编号</td>
                <td><input type="text" name="roomid" value="${room.id}"></td>
            </tr>
            <tr>
                <td>入住日期</td>
                <td><input type="date" name="indate"></td>
            </tr>
            <tr>
                <td>退房日期</td>
                <td><input type="date" name="outdate"></td>
            </tr>
        </table>
        <input type="submit" value="花费会员卡中20元进行预订">
    </form>
</div>
</body>
</html>
