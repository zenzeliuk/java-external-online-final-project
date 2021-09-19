<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>

            <%--            <h3>--%>
            <%--                <a><fmt:message key="msg.filter"/></a>--%>
            <%--                <fmt:message key="msg.admin-users"/>--%>
            <%--                <c:if test="${requestScope.user == 0 || requestScope.user == null}">--%>
            <%--                    <fmt:message key="msg.all"/>--%>
            <%--                </c:if>--%>
            <%--                <c:if test="${requestScope.user == 1}">--%>
            <%--                    <fmt:message key="msg.registered"/>--%>
            <%--                </c:if>--%>
            <%--                <c:if test="${requestScope.user == 2}">--%>
            <%--                    <fmt:message key="msg.unregistered"/>--%>
            <%--                </c:if>--%>
            <%--            </h3>--%>


            <form action="/admin/carts" method="GET">
                Pagination
                <c:forEach items="${sessionScope.pages_carts}" var="page">
                    <a href="${pageContext.request.contextPath}?user=${requestScope.user}&date_from=${requestScope.date_from}&date_to=${requestScope.date_to}&status=${requestScope.status}&sorting=${requestScope.sorting}&page=${page}">${page}</a>
                </c:forEach>
                <br/>

                FILTER<br/>
                <label for="user"><fmt:message key="msg.admin-users"/></label>
                <select name="user" id="user">
                    <option value="1"><fmt:message key="msg.all"/></option>
                    <option value="2"><fmt:message key="msg.registered"/></option>
                    <option value="3"><fmt:message key="msg.unregistered"/></option>
                </select>
                DATE CREATE FROM
                <input type="date" name="date_from" value="${requestScope.date_from}">
                DATE CREATE TO
                <input type="date" name="date_to" value="${requestScope.date_to}">

                <label for="status"><fmt:message key="msg.status"/></label>
                <select name="status" id="status">
                    <option value="0"><fmt:message key="msg.all"/></option>
                    <c:forEach items="${sessionScope.status_list}" var="status">
                        <option value="${status.id}"><c:out value="${status.name}"/></option>
                    </c:forEach>
                </select>

                <label for="sorting"><fmt:message key="msg.sorting"/></label>
                <select name="sorting" id="sorting">
                    <option value="1"><fmt:message key="msg.sorting-create-time-increase"/></option>
                    <option value="2"><fmt:message key="msg.sorting-create-time-decrease"/></option>
                    <option value="3"><fmt:message key="msg.sorting-update-time-increase"/></option>
                    <option value="4"><fmt:message key="msg.sorting-update-time-decrease"/></option>
                </select>

                <button type="submit"><fmt:message key="msg.submit"/></button>
            </form>

            <table>
                <tr>
                    <th id="login"><fmt:message key="msg.login"/></th>
                    <th id="create"><fmt:message key="msg.create"/></th>
                    <th id="update"><fmt:message key="msg.update"/></th>
                    <th id="status_name"><fmt:message key="msg.status"/></th>
                    <th id="change_status"><fmt:message key="msg.change-status"/></th>
                </tr>
                <c:forEach items="${sessionScope.cart_list}" var="cart">
                    <tr>
                        <td headers="login"><c:out value="${cart.userName}"/></td>
                        <td headers="create"><c:out value="${cart.createTime}"/></td>
                        <td headers="update"><c:out value="${cart.updateTime}"/></td>
                        <td headers="status_name"><c:out value="${cart.statusName}"/></td>
                        <td headers="change_status">
                            <form action="/admin/change_status" method="POST">
                                <input type="text" name="cart_id" value="${cart.id}" hidden>
                                <select name="status_id">
                                    <c:forEach items="${sessionScope.status_list}" var="status">
                                        <option value="${status.id}"><c:out value="${status.name}"/></option>
                                    </c:forEach>
                                </select>
                                <button type="submit"><fmt:message key="msg.submit"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>


        </jsp:body>
    </t:page>
</fmt:bundle>

