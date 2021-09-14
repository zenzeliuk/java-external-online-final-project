<%@ page import="com.epam.rd.java.basic.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="header_messages">

    <c:set var="ADMIN" value="<%=Role.ADMIN%>"/>
    <c:set var="USER" value="<%=Role.USER%>"/>
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