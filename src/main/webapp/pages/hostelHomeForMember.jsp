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
        </ul>
    </nav>
</div>
<div>
    <table border="1">
        <tr>
            <td>客栈编号</td>
            <td>${hostelInfo.userID}</td>
        </tr>
        <tr>
            <td>客栈状态</td>
            <td>${hostelstate}</td>
        </tr>
        <tr>
            <td>客栈名称</td>
            <td>${hostelInfo.name}</td>
        </tr>
        <tr>
            <td>客栈地址</td>
            <td>${hostelInfo.address}</td>
        </tr>
        <tr>
            <td>客栈电话</td>
            <td>${hostelInfo.phone}</td>
        </tr>
        <tr>
            <td>房间数量</td>
            <td>${roomList.size()}</td>
        </tr>
    </table>
    <h2>房间列表</h2>
    <table border="2">
        <tr>
            <td>房间编号</td>
            <td>房间类型</td>
            <td>房间价格</td>
            <td>预定</td>

        </tr>
        <c:forEach items="${roomList}" var="item">
            <tr>
                <td>${item.roomId}</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td><a href="<%=request.getContextPath()%>/member/room/${item.roomId}">预定</a></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
