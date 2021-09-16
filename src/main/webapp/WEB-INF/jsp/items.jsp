<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            https://freefrontend.com/bootstrap-code-examples/
            https://freefrontend.com/bootstrap-product-cards/
            https://bbbootstrap.com/snippets/bootstrap-ecommerce-product-grid-view-card-19577966
            https://bootsnipp.com/snippets/N6kk4
            https://bootsnipp.com/snippets/N6pQ6
            https://bootsnipp.com/snippets/lWmk2
            <div>
                <b><fmt:message key="msg.category"/></b><br/>
                <c:forEach items="${sessionScope.category_list}" var="category">
                    <form action="filter-item" method="GET">
                        <input type="text" name="category_id" value="${category.id}" hidden>
                        <button type="submit">${category.name}</button>
                    </form>
                </c:forEach>
            </div>

            <div class="container d-flex justify-content-center mt-50 mb-50">
                <div class="row">
                    <c:forEach items="${sessionScope.items}" var="item">
                        <div class="col-md-4 mt-2">
                            <div class="card">
                                <div class="card-body">
                                    <div class="card-img-actions">
                                        <img src="${item.image}"class="card-img img-fluid" width="96" height="350" alt="">
                                    </div>
                                </div>
                                <div class="card-body bg-light text-center">
                                    <div class="mb-2">
                                        <h6 class="font-weight-semibold mb-2">
                                            <a class="text-default mb-2" data-abc="true">${item.name}</a>
                                        </h6>
                                        <a class="text-muted" data-abc="true">Laptops &
                                            Notebooks</a>
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


<%--            <table>--%>
<%--                <tr>--%>
<%--                    <th id="name"><fmt:message key="msg.item-name"/></th>--%>
<%--                    <th id="price"><fmt:message key="msg.item-price"/></th>--%>
<%--                    <th id="image"><fmt:message key="msg.item-image"/></th>--%>
<%--                    <th id="addtocart"><fmt:message key="msg.add-to-cart"/></th>--%>
<%--                </tr>--%>
<%--                <c:forEach items="${sessionScope.items}" var="item">--%>
<%--                    <form action="add-to-cart" method="POST">--%>
<%--                        <tr>--%>
<%--                            <input type="text" name="item_id" value="${item.id}" hidden>--%>
<%--                            <td headers="name"><c:out value="${item.name}"/></td>--%>
<%--                            <td headers="price"><c:out value="${item.price}"/></td>--%>
<%--                            <td headers="image"><c:out value="${item.image}"/></td>--%>
<%--                            <td headers="addtocart">--%>
<%--                                <c:if test='${!fn:contains(sessionScope.cartitem, item.id)}'>--%>
<%--                                    <button type="submit"><fmt:message key="msg.add"/></button>--%>
<%--                                </c:if>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </form>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
        </jsp:body>

    </t:page>
</fmt:bundle>

