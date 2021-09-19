<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>

            <h3>
                <a><fmt:message key="msg.filter"/></a>
                <fmt:message key="msg.admin-users"/>
                <c:if test="${requestScope.user == 0 || requestScope.user == null}">
                    <fmt:message key="msg.all"/>
                </c:if>
                <c:if test="${requestScope.user == 1}">
                    <fmt:message key="msg.registered"/>
                </c:if>
                <c:if test="${requestScope.user == 2}">
                    <fmt:message key="msg.unregistered"/>
                </c:if>
            </h3>
            <form action="/admin/carts" method="GET">
                <div class="select" style="width:200px;">
                    <label for="user"><fmt:message key="msg.admin-users"/></label>
                    <select name="user" id="user">
                        <option value="0"><fmt:message key="msg.all"/></option>
                        <option value="1"><fmt:message key="msg.registered"/></option>
                        <option value="2"><fmt:message key="msg.unregistered"/></option>
                    </select>
                </div>
                <input type="date" name="date_from" value="${requestScope.date_from}">
                <input type="date" name="date_to" value="${requestScope.date_to}">
                <button type="submit"><fmt:message key="msg.submit"/></button>
            </form>


        </jsp:body>
    </t:page>
</fmt:bundle>

