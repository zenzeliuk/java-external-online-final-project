<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>

            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><fmt:message key="msg.admin-users"/></h5>
                                <a href="${pageContext.request.contextPath}/admin/users"><fmt:message key="msg.go"/></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-3">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><fmt:message key="msg.admin-carts"/></h5>
                                <a href="${pageContext.request.contextPath}/admin/carts"><fmt:message key="msg.go"/></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><fmt:message key="msg.admin-create-item"/></h5>
                                <a href="${pageContext.request.contextPath}/admin/create-item"><fmt:message
                                        key="msg.go"/></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><fmt:message key="msg.admin-items"/></h5>
                                <a href="${pageContext.request.contextPath}/app/item"><fmt:message key="msg.go"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </jsp:body>
    </t:page>
</fmt:bundle>

