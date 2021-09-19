<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="message">

    <t:page>
        <jsp:body>
            <form action="/admin/create-item" method="POST">

                <label for="name_item"><fmt:message key="msg.item-name"/><</label>
                <input name="name_item" type="text" id="name_item" value="${name_item}">

                <label for="image"><fmt:message key="msg.item-image"/><</label>
                <input name="image" type="text" id="image" value="${image}">

                <label for="price"><fmt:message key="msg.item-price"/><</label>
                <input name="price" type="number" id="price" value="${price}">

                <label for="category"><fmt:message key="msg.category"/></label>
                <select name="category_id" id="category">
                    <c:forEach items="${sessionScope.category_list}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>

                <label for="brand"><fmt:message key="msg.brand"/></label>
                <select name="brand_id" id="brand">
                    <c:forEach items="${sessionScope.brand_list}" var="brand">
                        <option value="${brand.id}">${brand.name}</option>
                    </c:forEach>
                </select>

                <label for="color"><fmt:message key="msg.color"/></label>
                <select name="color_id" id="color">
                    <c:forEach items="${sessionScope.color_list}" var="color">
                        <option value="${color.id}">${color.name}</option>
                    </c:forEach>
                </select>
                <button type="submit"><fmt:message key="msg.admin-create-item"/></button>
            </form>
            <br/>
            <form action="/admin/create-category" method="POST">
                <label for="name_category"><fmt:message key="msg.category-name"/><</label>
                <input name="name_category" type="text" id="name_category" value="${name_category}">
                <button type="submit"><fmt:message key="msg.admin-create-category"/></button>
            </form>
            <br/>
            <form action="/admin/create-brand" method="POST">
                <label for="name_brand"><fmt:message key="msg.brand-name"/><</label>
                <input name="name_brand" type="text" id="name_brand" value="${name_brand}">
                <button type="submit"><fmt:message key="msg.admin-create-brand"/></button>
            </form>
            <br/>
            <form action="/admin/create-color" method="POST">
                <label for="name_color"><fmt:message key="msg.color-name"/><</label>
                <input name="name_color" type="text" id="name_color" value="${name_color}">
                <button type="submit"><fmt:message key="msg.admin-create-color"/></button>
            </form>

        </jsp:body>
    </t:page>
</fmt:bundle>