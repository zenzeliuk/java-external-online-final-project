<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <c:forEach items="${sessionScope.items}" var="item">
                <form action="add-to-cart" method="POST">
                    <tr>
                        <input type="text" name="item_id" value="${item.id}" hidden>
                        <td><fmt:message key="msg.item-name"/> <c:out value="${item.name}"/></td>
                        <td><fmt:message key="msg.item-price"/> <c:out value="${item.price}"/></td>
                        <td><fmt:message key="msg.item-image"/> <c:out value="${item.image}"/></td>
                        <c:if test='${!fn:contains(sessionScope.cartitem, item.id)}'>
                            <button type="submit"><fmt:message key="msg.add-to-cart"/></button>
                        </c:if>
                    </tr>
                </form>
            </c:forEach>
        </jsp:body>
    </t:page>
</fmt:bundle>

