<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<style>
    <%@include file="/css/styles.css" %>
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
            <li style="float: right"><a><c:out value="${user.login}"/></a></li>
        </c:if>
        <%--        LOGIN AND REGISTRATION --%>
        <c:if test="${user == null}">
            <li style="float: right"><a href="/jsp/registration.jsp">Registration</a></li>
            <li style="float: right"><a href="/jsp/login.jsp">Login</a></li>
        </c:if>

    </ul>
</div>
<br>

<div id="info">
    Інтернет магазин

    Магазин має каталог Товарів, для якого необхідно реалізувати можливість:
    - сортування за назвою товару (az, za);
    - сортування товарів за ціною (від дешевих до дорогих, від дорогих до дешевих);
    - сортування товарів за новизною;
    - вибірки товарів за параметрами (категорія, проміжок ціни, колір, розмір, тощо).
    Користувач переглядає каталог і може додавати товари до свого кошика.
    Після додавання товарів у кошик, зареєстрований користувач може зробити Замовлення.
    Для незареєстрованого користувача ця опція недоступна. Після розміщення замовлення,
    йому (замовленню) присвоюється статус 'зареєстрований'.
    Користувач має особистий кабінет, в якому може переглянути свої замовлення.
    Адміністратор системи володіє правами:
    - додавання/видалення товарів, зміни інформації про товар;
    - блокування/розблокування користувача;
    - переведення замовлення зі статусу 'зареєстрований' до 'оплачений' або 'скасований'.
</div>


</body>
</html>
