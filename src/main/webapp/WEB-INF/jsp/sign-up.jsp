<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <form action="sign-up" method="POST">
                <c:if test="${not empty error}">
                    <a><fmt:message key="msg.error-sign-up"/></a><br/>
                </c:if>

                <label for="input_login"><fmt:message key="msg.login"/><</label>
                <input name="login" type="text" id="input_login" value="${requestScope.user.login}" required>

                <label for="input_password"><fmt:message key="msg.password"/><</label>
                <input name="password" type="password" id="input_password">

                <label for="input_firstname"><fmt:message key="msg.firstname"/><</label>
                <input name="firstname" type="text" id="input_firstname" value="${requestScope.details.firstName}" required>

                <label for="input_lastname"><fmt:message key="msg.lastname"/><</label>
                <input name="lastname" type="text" id="input_lastname" value="${requestScope.details.lastName}" required>

                <label for="input_email"><fmt:message key="msg.email"/><</label>
                <input name="email" type="text" id="input_email" value="${requestScope.details.email}" required>

                <label for="input_phone"><fmt:message key="msg.phone"/><</label>
                <input name="phone" type="number" id="input_phone" value="${requestScope.details.phone}" required>

                <label for="input_age"><fmt:message key="msg.age"/><</label>
                <input name="age" type="number" id="input_age" value="${requestScope.details.age}" required>

                <button type="submit"><fmt:message key="msg.sign-up"/></button>

            </form>
        </jsp:body>
    </t:page>
</fmt:bundle>