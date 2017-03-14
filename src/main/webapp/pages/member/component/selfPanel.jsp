<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="vip-self-container">
    <jsp:include page="info.jsp" flush="true"/>
    <button><a href="/member/charge">充值</a></button>
    <button><a href="/member/convert">积分换钱</a></button>
    <button id="selfPanel_stopCardBtn">停卡</button>
    <div id="selfPanel_stopCard_msg"></div>

    <%--<a class="close" data-dismiss="alert" href="#">&times;</a>--%>
</div>