<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<t:page>
    <jsp:body>
        <div>
            Магазин має каталог Товарів, для якого необхідно реалізувати можливість:<br/>
            - сортування за назвою товару (az, za);<br/>
            - сортування товарів за ціною (від дешевих до дорогих, від дорогих до дешевих);<br/>
            - сортування товарів за новизною;<br/>
            - вибірки товарів за параметрами (категорія, проміжок ціни, колір, розмір, тощо).<br/>
            Користувач переглядає каталог і може додавати товари до свого кошика.<br/>
            Після додавання товарів у кошик, зареєстрований користувач може зробити Замовлення.<br/>
            Для незареєстрованого користувача ця опція недоступна. Після розміщення замовлення,<br/>
            йому (замовленню) присвоюється статус 'зареєстрований'.<br/>
            Користувач має особистий кабінет, в якому може переглянути свої замовлення.<br/>
            Адміністратор системи володіє правами:<br/>
            - додавання/видалення товарів, зміни інформації про товар;<br/>
            - блокування/розблокування користувача;<br/>
            - переведення замовлення зі статусу 'зареєстрований' до 'оплачений' або 'скасований'.<br/>
        </div>
    </jsp:body>
</t:page>


