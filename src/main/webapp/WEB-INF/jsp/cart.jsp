<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <form action="confirm-cart" method="POST">
                <c:if test="${sessionScope.user != null && sessionScope.cartitemDTO.size() != 0}">
                    <a><fmt:message key="msg.status"/> <c:out value="${sessionScope.status.name}"/></a>
                    <button type="submit"><fmt:message key="msg.confirm"/></button>
                    <br/>
                </c:if>
            </form>
            <c:forEach items="${sessionScope.cartitemDTO}" var="ci">
                <table>
                    <tr>
                        <td><fmt:message key="msg.item-name"/> <c:out value="${ci.itemName}"/></td>
                        <td><fmt:message key="msg.item-price"/> <c:out value="${ci.price}"/></td>
                        <form action="edit-count" method="GET">
                            <td><fmt:message key="msg.count"/>
                                <input type="text" name="cart-item-id" value="${ci.id}" hidden>
                                <input type="number" name="count-item"  value="${ci.count}" required>
                                <button type="submit"><fmt:message key="msg.edit-count"/></button>
                            </td>
                        </form>

                        <form action="delete-cart-item" method="POST">
                            <td>
                                <input type="text" name="cart_item_id" value="${ci.id}" hidden>
                                <button type="submit"><fmt:message key="msg.delete"/></button>
                            </td>
                        </form>

                    </tr>
                </table>

            </c:forEach>
        </jsp:body>
    </t:page>
</fmt:bundle>

