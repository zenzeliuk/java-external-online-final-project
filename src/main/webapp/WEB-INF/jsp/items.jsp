<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <table>
                <caption><fmt:message key="msg.admin-items"/></caption>
                <tr>
                    <th id="name"><fmt:message key="msg.item-name"/></th>
                    <th id="price"><fmt:message key="msg.item-price"/></th>
                    <th id="image"><fmt:message key="msg.item-image"/></th>
                    <th id="addtocart"><fmt:message key="msg.add-to-cart"/></th>
                </tr>
                <c:forEach items="${sessionScope.items}" var="item">
                    <form action="add-to-cart" method="POST">
                        <tr>
                            <input type="text" name="item_id" value="${item.id}" hidden>
                            <td headers="name"><c:out value="${item.name}"/></td>
                            <td headers="price"><c:out value="${item.price}"/></td>
                            <td headers="image"><c:out value="${item.image}"/></td>
                            <td headers="addtocart"><c:if test='${!fn:contains(sessionScope.cartitem, item.id)}'>
                                <button type="submit"><fmt:message key="msg.add"/></button>
                            </c:if>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
        </jsp:body>

    </t:page>
</fmt:bundle>

