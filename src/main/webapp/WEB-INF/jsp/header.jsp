<%@ page import="com.epam.rd.java.basic.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="header_messages">

    <c:set var="ADMIN" value="<%=Role.ADMIN%>"/>
    <c:set var="USER" value="<%=Role.USER%>"/>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav m-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/"><fmt:message key="h.home"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/item"><fmt:message
                                key="h.items"/></a>
                    </li>
                    <c:if test="${sessionScope.role.name() == ADMIN}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/home"><fmt:message
                                    key="h.admin"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.role.name() == USER || sessionScope.role.name() == ADMIN}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/home"><fmt:message
                                    key="h.user"/></a>
                        </li>
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/login/logout"><fmt:message
                                        key="h.logout"/></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/login/sign-in"><fmt:message
                                        key="h.sign-in"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/login/sign-up"><fmt:message
                                        key="h.sign-up"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>

                <form class="form-inline my-2 my-lg-0">
                    <a class="btn btn-success btn-sm ml-3" href="${pageContext.request.contextPath}/app/cart">
                        <i class="fa fa-shopping-cart"></i><fmt:message key="h.cart"/>
                    </a>
                </form>
            </div>
        </div>
    </nav>


</fmt:bundle>