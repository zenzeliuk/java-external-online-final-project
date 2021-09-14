<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:general>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <ul>
            <li><a href="?lang=ukr&${pageContext.request.queryString}" title="UA"> ua </a></li>
            <li><a href="?lang=en&${pageContext.request.queryString}" title="ENG"> eng </a></li>
        </ul>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:general>