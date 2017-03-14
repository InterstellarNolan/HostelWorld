<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="hostelworld"/>
    <meta name="author" content="axy"/>
    <title>HostelWorld</title>
    <meta name="keywords" content=""/>
    <link rel="stylesheet" href="../../css/w3.css">
    <link rel="stylesheet" href="../../css/skel-noscript.css"/>
    <link rel="stylesheet" href="../../css/style.css"/>
    <link rel="stylesheet" href="../../css/style-desktop.css"/>
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery.dropotron.js"></script>
    <script src="../../js/config.js"></script>
    <script src="../../js/skel.min.js"></script>
    <script src="../../js/skel-panels.min.js"></script>
</head>


<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>
<jsp:include page="component/selfPanel.jsp" flush="true"/>
<h1 id="hostelName"></h1>
<div>
    <img id="image" class="image-little">
    <div id="roomName"></div>
    <div id="roomPrice"></div>
</div>
<form:form commandName="bookBill" method="post" action="/vip/book" >
    <label >入住日期</label>
    <form:input path="liveInDate"/>
    <input type="submit" value="确认预订"/>
</form:form>

<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/member/bookPage.js"></script>
</body>
</html>