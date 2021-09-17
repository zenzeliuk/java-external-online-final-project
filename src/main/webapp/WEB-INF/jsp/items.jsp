<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>

            <div>
                <form action="filter-item" method="GET">
                    <label for="category"><fmt:message key="msg.category"/></label>
                    <select class="form-control" id="category" required="true" name="category_id">
                        <option value="0"><fmt:message key="msg.all-category"/></option>
                        <c:forEach items="${sessionScope.category_list}" var="category">
                            <option value="${category.id}">
                                    ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                    <label for="color"><fmt:message key="msg.color"/></label>
                    <select class="form-control" id="color" required="true" name="color_id">
                        <option value="0"><fmt:message key="msg.all-color"/></option>
                        <c:forEach items="${sessionScope.color_list}" var="color">
                            <option value="${color.id}">
                                    ${color.name}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Submit"/>
                </form>
            </div>


            <div class="container d-flex justify-content-center mt-50 mb-50">
                <div class="row">
                    <c:forEach items="${sessionScope.items}" var="item">
                        <div class="col-md-4 mt-2">
                            <div class="card">
                                <div class="card-body">
                                    <div class="card-img-actions">
                                        <img src="${item.image}" class="card-img img-fluid" width="96" height="350"
                                             alt="">
                                    </div>
                                </div>
                                <div class="card-body bg-light text-center">
                                    <div class="mb-2">
                                        <h6 class="font-weight-semibold mb-2">
                                            <a class="text-default mb-2" data-abc="true">${item.name}</a>
                                        </h6>
                                        <a class="text-muted" data-abc="true">Description</a>
                                    </div>
                                    <h3 class="mb-0 font-weight-semibold">${item.price} UAH</h3>

                                    <c:if test='${!fn:contains(sessionScope.cartitem, item.id)}'>
                                        <form action="add-to-cart" method="POST">
                                            <input type="text" name="item_id" value="${item.id}" hidden>
                                            <button type="submit" class="btn bg-cart">
                                                <fmt:message key="msg.add-to-cart"/>
                                            </button>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>


        </jsp:body>

    </t:page>
</fmt:bundle>

