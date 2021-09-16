<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>

            <a href="${pageContext.request.contextPath}/admin/users?page=1">
                <fmt:message key="msg.admin-users"/>
            </a>
            <a href="${pageContext.request.contextPath}/admin/carts">
                <fmt:message key="msg.admin-carts"/>
            </a>
            <a href="${pageContext.request.contextPath}/admin/add-create-item">
                <fmt:message key="msg.admin-create-item"/>
            </a>
            <a href="${pageContext.request.contextPath}/app/item">
                <fmt:message key="msg.admin-items"/>
            </a>


        </jsp:body>
    </t:page>
</fmt:bundle>

