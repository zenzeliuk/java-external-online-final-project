<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <table>
                <tr>
                    <th id="name"><fmt:message key="msg.item-name"/></th>
                    <th id="price"><fmt:message key="msg.item-price"/></th>
                    <th id="count"><fmt:message key="msg.count"/></th>
                </tr>
                <c:forEach items="${requestScope.cartItemDTOList}" var="ci">
                    <tr>
                        <td headers="name"><c:out value="${ci.itemName}"/></td>
                        <td headers="price"><c:out value="${ci.price}"/></td>
                        <td headers="count"><c:out value="${ci.count}"/></td>
                    </tr>
                </c:forEach>
            </table>


        </jsp:body>
    </t:page>
</fmt:bundle>

