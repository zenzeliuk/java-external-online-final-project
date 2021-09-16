<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <aside class="col-sm-2">
                <div class="card">
                    <article class="card-group-item">
                        <header class="card-header"><h6 class="title">Similar category </h6></header>
                        <div class="filter-content">
                            <div class="list-group list-group-flush">
                                <a href="#" class="list-group-item">Cras justo odio </a>
                                <a href="#" class="list-group-item">Dapibus ac facilisis </a>
                                <a href="#" class="list-group-item">Morbi leo risus </a>
                                <a href="#" class="list-group-item">Another item </a>
                            </div>  <!-- list-group .// -->
                        </div>
                    </article> <!-- card-group-item.// -->

                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title">Brands </h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <form>
                                    <label class="form-check">
                                        <input class="form-check-input" type="checkbox" value="">
                                        <span class="form-check-label">Mersedes Benz</span>
                                    </label> <!-- form-check.// -->
                                    <label class="form-check">
                                        <input class="form-check-input" type="checkbox" value="">
                                        <span class="form-check-label">Nissan Altima</span>
                                    </label>  <!-- form-check.// -->
                                    <label class="form-check">
                                        <input class="form-check-input" type="checkbox" value="">
                                        <span class="form-check-label">Another Brand</span>
                                    </label>  <!-- form-check.// -->
                                </form>

                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->

                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title">Range input </h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label>Min</label>
                                        <input type="number" class="form-control" id="inputEmail4" placeholder="$0">
                                    </div>
                                    <div class="form-group col-md-6 text-right">
                                        <label>Max</label>
                                        <input type="number" class="form-control" placeholder="$1,0000">
                                    </div>
                                </div>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->

                </div> <!-- card.// -->
            </aside>
            <!-- col.// -->

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

