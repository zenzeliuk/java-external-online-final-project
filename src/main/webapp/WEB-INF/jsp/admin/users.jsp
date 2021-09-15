<%@ page import="com.epam.rd.java.basic.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <c:set var="USER" value="<%=Role.USER.getName()%>"/>
    <c:set var="ADMIN" value="<%=Role.ADMIN.getName()%>"/>
    <c:set var="BLOCKED" value="<%=Role.BLOCKED.getName()%>"/>
    <t:page>
        <jsp:body>

            <table>
                <caption><fmt:message key="msg.admin-users"/></caption>
                <tr>
                    <th id="login"><fmt:message key="msg.user-login"/></th>
                    <th id="role"><fmt:message key="msg.user-role-name"/></th>
                    <th id="firstname"><fmt:message key="msg.user-first-name"/></th>
                    <th id="lastname"><fmt:message key="msg.user-last-name"/></th>
                    <th id="email"><fmt:message key="msg.user-email"/></th>
                    <th id="phone"><fmt:message key="msg.user-phone"/></th>
                    <th id="age"><fmt:message key="msg.user-age"/></th>
                    <th id="changestatus"><fmt:message key="msg.change-status"/></th>
                </tr>
                <c:forEach items="${sessionScope.user_list}" var="user">
                    <tr>
                        <td headers="login"><c:out value="${user.login}"/></td>
                        <td headers="role"><c:out value="${user.roleName}"/></td>
                        <td headers="firstname"><c:out value="${user.firstName}"/></td>
                        <td headers="lastname"><c:out value="${user.lastName}"/></td>
                        <td headers="email"><c:out value="${user.email}"/></td>
                        <td headers="phone"><c:out value="${user.phone}"/></td>
                        <td headers="age"><c:out value="${user.age}"/></td>
                        <c:choose>
                            <c:when test="${user.roleName == ADMIN}">
                                <td headers="changestatus">
                                    <button type="button">
                                        <fmt:message key="msg.admin"/>
                                    </button>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <form action="change-role" method="POST">
                                    <input type="text" name="user_id" value="${user.idUser}" hidden>
                                    <td headers="changestatus">
                                        <button type="submit">
                                            <c:if test="${user.roleName == USER}">
                                                <fmt:message key="msg.block"/>
                                            </c:if>
                                            <c:if test="${user.roleName == BLOCKED}">
                                                <fmt:message key="msg.unblock"/>
                                            </c:if>
                                        </button>
                                    </td>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </jsp:body>
    </t:page>
</fmt:bundle>

