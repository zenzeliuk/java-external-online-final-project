<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:general>
    <jsp:attribute name="css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" >
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
              id="bootstrap-css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css"/>
    </jsp:attribute>
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