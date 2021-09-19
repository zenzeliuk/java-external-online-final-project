package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.service.CartService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCartsCommandGET implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("page");
        String sorting = request.getParameter("sorting");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        String user = request.getParameter("user");

//        int page;
//        if (p == null) {
//            page = 1;
//        } else {
//            page = Integer.parseInt(p);
//        }

        ServiceFactory factory = new ServiceFactoryImpl();
        CartService cartService = factory.getCartService();

//        List<Cart> cartList = cartService.findWithPaginationFilterAndSorting(dateFrom, dateTo, page, sorting);

        request.setAttribute("user", user);
        request.setAttribute("date_from", dateFrom);
        request.setAttribute("date_to", dateTo);
        request.getSession().setAttribute("page_cart", page);
        return new Page(PathPageManager.getProperty("page.admin-carts")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
