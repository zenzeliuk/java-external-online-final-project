<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <form action="sign-in" method="POST">
                <c:if test="${not empty error}">
                    <a><fmt:message key="msg.error-sign-in"/></a><br/>
                </c:if>

                <label for="input_login"><fmt:message key="msg.login"/><</label>
                <input name="login" type="text" id="input_login" value="${login}">

                <label for="input_password"><fmt:message key="msg.password"/><</label>
                <input name="password" type="password" id="input_password">
                <button type="submit"><fmt:message key="msg.sign-in"/></button>

            </form>
        </jsp:body>
    </t:page>
</fmt:bundle>