<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/css/item.css" %>
</style>
<html>
<head>
    <title>Items</title>
</head>

<body>

<div class="grid-container">

    <c:forEach items="${items}" var="item">
        <form>
            <div>
                <p><c:out value="${item.name}"/></p>
                <p><c:out value="${item.code}"/></p>
                <p><c:out value="${item.price}"/></p>
                <p><c:out value="${item.description}"/></p>
            </div>
        </form>
    </c:forEach>

</div>


</body>
</html>
