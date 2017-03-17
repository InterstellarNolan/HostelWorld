<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/16
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hostel World</title>
    <link rel="stylesheet" href="../css/style-desktop.css"/>
</head>
<body>
<!-- Header Wrapper -->
<div id="header-wrapper">
    <div class="container">
        <div class="row">
            <div class="12u">
                <!-- Header -->
                <section id="header">
                    <!-- Logo -->
                    <nav id="nav">
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/hostel/home">基本信息</a></li>
                            <li><a href="<%=request.getContextPath()%>/hostel/request">开店申请</a></li>
                            <li><a href="<%=request.getContextPath()%>/hostel/edit">修改申请</a></li>
                            <li><a href="<%=request.getContextPath()%>/hostel/edit">所有房间</a></li>
                            <li><a href="<%=request.getContextPath()%>/hostel/addroom">新增房间</a></li>

                            <li><a href="<%=request.getContextPath()%>/hostel/analysis">统计信息</a></li>
                            <li><a href="<%=request.getContextPath()%>/hostel/checkIn">旅客入住</a></li>
                            <li><a href="#">旅客退房</a></li>
                            <li><a href="<%=request.getContextPath()%>/hostel/pay">旅客结账</a></li>
                            <li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
                        </ul>
                    </nav>
                </section>
            </div>
        </div>
    </div>
</div>

<div>
    <a href="<%=request.getContextPath()%>/hostel/checkOutMember">会员退房</a>
    <a href="<%=request.getContextPath()%>/hostel/checkOutVisitor">贵宾退房</a>
</div>
</body>
</html>
