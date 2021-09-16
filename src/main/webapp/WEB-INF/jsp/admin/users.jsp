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
            <div class="container">
                <div class="paginate" style="width:600px">
                    <ul class="pagination">
                        <c:forEach items="${sessionScope.pages}" var="page">
                            <li>
                                <a href="${pageContext.request.contextPath}?page=${page}">
                                        ${page}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <table>
                <caption><fmt:message key="msg.admin-users"/></caption>
                <tr>
                    <th id="login"><fmt:message key="msg.user-login"/></th>
                    <th id="firstname"><fmt:message key="msg.user-first-name"/></th>
                    <th id="lastname"><fmt:message key="msg.user-last-name"/></th>
                    <th id="email"><fmt:message key="msg.user-email"/></th>
                    <th id="phone"><fmt:message key="msg.user-phone"/></th>
                    <th id="age"><fmt:message key="msg.user-age"/></th>
                    <th id="role"><fmt:message key="msg.user-role-name"/></th>
                    <th id="changerole"><fmt:message key="msg.change-role"/></th>
                </tr>
                <c:forEach items="${sessionScope.user_list}" var="user">
                    <tr>
                        <td headers="login"><c:out value="${user.login}"/></td>
                        <td headers="firstname"><c:out value="${user.firstName}"/></td>
                        <td headers="lastname"><c:out value="${user.lastName}"/></td>
                        <td headers="email"><c:out value="${user.email}"/></td>
                        <td headers="phone"><c:out value="${user.phone}"/></td>
                        <td headers="age"><c:out value="${user.age}"/></td>
                        <c:choose>
                            <c:when test="${user.roleName == ADMIN}">
                                <td headers="role">
                                    <fmt:message key="msg.admin"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <form action="change-role" method="POST">
                                    <input type="text" name="user_id" value="${user.idUser}" hidden>
                                    <c:if test="${user.roleName == USER}">
                                        <td headers="role">
                                            <fmt:message key="msg.unblock"/>
                                        </td>
                                        <td headers="changestatus">
                                            <button type="submit">
                                                <fmt:message key="msg.do-block"/>
                                            </button>
                                        </td>
                                    </c:if>

                                    <c:if test="${user.roleName == BLOCKED}">
                                        <td headers="role">
                                            <fmt:message key="msg.block"/>
                                        </td>
                                        <td headers="changestatus">
                                            <button type="submit">
                                                <fmt:message key="msg.do-unblock"/>
                                            </button>
                                        </td>
                                    </c:if>
                                </form>

                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>

        </jsp:body>
    </t:page>
</fmt:bundle>

