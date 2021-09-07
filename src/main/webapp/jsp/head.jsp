<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/css/styles.css" %>
    <%@include file="/css/item.css" %>
</style>
<html>

<body>
<div id="main">
    <ul class="topnav">
        <%--        HOME --%>
        <li><a class="active" href="/index.jsp">Home</a></li>
        <li><a href="controller?command=items">Items</a></li>
        <%--        USER/ADMIN WAS LOGIN --%>
        <c:if test="${user != null}">
            <li style="float: right"><a href="controller?command=logout">Logout</a></li>
            <li style="float: right"><a href="controller?command=user_details"><c:out value="${user.login}"/></a></li>
        </c:if>
        <%--        LOGIN AND REGISTRATION --%>
        <c:if test="${user == null}">
            <li style="float: right"><a href="/jsp/registration.jsp">Registration</a></li>
            <li style="float: right"><a href="/jsp/login.jsp">Login</a></li>
        </c:if>

    </ul>
</div>
</body>
</html>
