<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <c:forEach items="${sessionScope.user_cart_list}" var="c">

                <form action="show-cart" method="GET">
                    <table>
                        <input type="text" name="item_id" value="${c.id}" hidden>
                        <td><fmt:message key="msg.status"/> <c:out value="${c.statusName}"/></td>
                        <td><fmt:message key="msg.create"/> <c:out value="${c.createTime}"/></td>
                        <td><fmt:message key="msg.update"/> <c:out value="${c.updateTime}"/></td>
                        <td><button type="submit"><fmt:message key="msg.show"/></button></td>
                    </table>
                </form>

            </c:forEach>
        </jsp:body>
    </t:page>
</fmt:bundle>

