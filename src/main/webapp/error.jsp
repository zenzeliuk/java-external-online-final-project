<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">
<t:page>
    <jsp:body>
        <c:if test="${not empty user_blocked}">
            <h3><fmt:message key="msg.user-blocked"/></h3>
        </c:if>
    </jsp:body>
</t:page>
</fmt:bundle>
