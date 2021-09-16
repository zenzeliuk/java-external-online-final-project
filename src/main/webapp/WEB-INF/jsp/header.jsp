<%@ page import="com.epam.rd.java.basic.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="header_messages">

    <c:set var="ADMIN" value="<%=Role.ADMIN%>"/>
    <c:set var="USER" value="<%=Role.USER%>"/>


    https://bootsnipp.com/snippets/N6kk4
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        div class="container">
        <a class="navbar-brand" href="index.html">Simple Ecommerce</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.html">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="category.html">Categories <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="product.html">Product</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cart.html">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="contact.html">Contact</a>
                </li>
            </ul>

            <form class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="button" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="cart.html">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light">3</span>
                </a>
            </form>
        </div>
        </div>
    </nav>


    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/"><fmt:message key="h.home"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/item"><fmt:message key="h.items"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/cart"><fmt:message key="h.cart"/></a>
        </li>
        <c:if test="${sessionScope.role.name() == ADMIN}">
            <li>
                <a href="${pageContext.request.contextPath}/admin/home"><fmt:message key="h.admin"/></a>
            </li>
        </c:if>
        <c:if test="${sessionScope.role.name() == USER || sessionScope.role.name() == ADMIN}">
            <li>
                <a href="${pageContext.request.contextPath}/user/home"><fmt:message key="h.user"/></a>
            </li>
        </c:if>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li>
                    <a href="${pageContext.request.contextPath}/login/logout"><fmt:message key="h.logout"/></a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="${pageContext.request.contextPath}/login/sign-in"><fmt:message key="h.sign-in"/></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/login/sign-up"><fmt:message key="h.sign-up"/></a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</fmt:bundle>