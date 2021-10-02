<%@ page import="com.epam.rd.java.basic.model.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <c:set var="OPEN" value="<%=Status.OPEN.getName()%>"/>
    <c:set var="CANCELED" value="<%=Status.CANCELED.getName()%>"/>
    <c:set var="PAID" value="<%=Status.PAID.getName()%>"/>
    <c:set var="REGISTERED" value="<%=Status.REGISTERED.getName()%>"/>

    <t:page>
        <jsp:body>
            <table>
                <caption><fmt:message key="msg.admin-carts"/></caption>
                <tr>
                    <th id="status"><fmt:message key="msg.status"/></th>
                    <th id="create"><fmt:message key="msg.create"/></th>
                    <th id="update"><fmt:message key="msg.update"/></th>
                    <th id="itemincart"><fmt:message key="msg.item-in-cart"/></th>
                </tr>
                <c:forEach items="${sessionScope.user_cart_list}" var="c">
                    <form action="show-cart" method="GET">
                        <tr>
                            <input type="text" name="cartId" value="${c.id}" hidden>
                            <td headers="status">
                                <c:choose>
                                    <c:when test="${c.statusName == OPEN}">
                                        <fmt:message key="msg.status-open"/>
                                    </c:when>
                                    <c:when test="${c.statusName == CANCELED}">
                                        <fmt:message key="msg.status-canceled"/>
                                    </c:when>
                                    <c:when test="${c.statusName == REGISTERED}">
                                        <fmt:message key="msg.status-registered"/>
                                    </c:when>
                                    <c:when test="${c.statusName == PAID}">
                                        <fmt:message key="msg.status-paid"/>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td headers="create"><c:out value="${c.createTime}"/></td>
                            <td headers="update"><c:out value="${c.updateTime}"/></td>
                            <td headers="itemincart">
                                <button type="submit"><fmt:message key="msg.show"/></button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
        </jsp:body>
    </t:page>
</fmt:bundle>

