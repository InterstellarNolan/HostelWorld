<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="../../css/member_selfPanel.css">
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery.dropotron.js"></script>
    <script src="../../js/config.js"></script>
    <script src="../../js/skel.min.js"></script>
    <script src="../../js/skel-panels.min.js"></script>
</head>

<body>
<div id="header-wrapper">
    <div class="container">
        <div class="row">
            <div class="12u">

                <section id="header">

                    <h1>Hostel World</h1>

                    <!-- Nav -->
                    <nav id="nav">
                        <ul>

                            <li><a href="/member/hostels">所有客栈</a></li>
                            <li class="current_page_item"><a href="/member/bookList">我的预定</a></li>
                            <li><a href="/member/payList">我的消费</a></li>
                            <li><a href="/member/liveList">住房记录</a></li>
                        </ul>
                    </nav>

                </section>

            </div>
        </div>
    </div>
</div>
<div id="main-wrapper">
<div class="container">
    <div class="row">
        <div class="12u">
            <div class="w3-container w3-myfont">
                <!-- Portfolio -->
                <section>
                    <div>
                        <div class="row">
                            <div class="4u">
                                <section class="box">
                                    <img class="vip-avatar" src="../../img/ava1.jpg" alt="头像" id="avatar">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td>用户名：</td>
                                            <td>${member.realName}</td>
                                        </tr>
                                        <tr>
                                            <td>卡余额：</td>
                                            <td>${member.moneyLeft}元</td>
                                        </tr>
                                        <tr>
                                            <td>等级：</td>
                                            <td>${member.level}</td>
                                        </tr>
                                        <tr>
                                            <td>积分：</td>
                                            <td>${member.score}</td>
                                        </tr>
                                        <tr>
                                            <td>已消费：</td>
                                            <td>${member.moneyPaid}元</td>
                                        </tr>
                                        <tr>
                                            <td>状态：</td>
                                            <td>${member.state.toChineseString()}</td>
                                        </tr>
                                        <tr>
                                            <td><a href="/member/charge">充值</a></td>
                                            <td><a href="/member/convert">积分兑换</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </section>
                            </div>
                            <div class="8u skel-cell-mainContent">
                                <h3>预订记录</h3>
                                <div class="table-responsive">
                                    <table border="1">
                                        <tbody>

                                        <td>订单号</td>
                                        <td>客栈名称</td>
                                        <td>房间类型</td>
                                        <td>价格</td>
                                        <td>入住时间</td>
                                        <td>订单时间</td>

                                        <c:forEach items="${bookbilllist}" var="item">
                                            <tr>
                                                <td>${item.id}</td>
                                                <td>
                                                    <a href="/hostel/rooms?hostelId=${item.hostelId}">${item.hostelName}</a>
                                                </td>
                                                <td>${item.roomName}</td>
                                                <td>${item.roomPrice}</td>
                                                <td>${item.liveInDate}</td>
                                                <td>${item.createDate}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="../common/tail.jsp" %>
</body>
</html>