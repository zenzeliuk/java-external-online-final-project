package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Status;
import com.epam.rd.java.basic.model.dto.CartDTO;
import com.epam.rd.java.basic.service.CartService;
import com.epam.rd.java.basic.service.StatusService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCartsCommandGET implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("page");
        String user = request.getParameter("user");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        String status = request.getParameter("status");
        String sorting = request.getParameter("sorting");

        if (page == null) {
            page = "1";
        }
        if (user == null) {
            user = "1";
        }
        if (status == null) {
            status = "0";
        }
        if (sorting == null) {
            sorting = "2";
        }

        ServiceFactory factory = new ServiceFactoryImpl();
        CartService cartService = factory.getCartService();
        StatusService statusService = factory.getStatusService();

        try {
            List<Status> statusList = statusService.findAll();
            List<Integer> pages = cartService.getPages(user, dateFrom, dateTo, status);
            List<CartDTO> cartList = cartService.findWithPaginationFilterAndSorting(user, dateFrom, dateTo, status, page, sorting);

            request.setAttribute("page", page);
            request.setAttribute("user", user);
            request.setAttribute("date_from", dateFrom);
            request.setAttribute("date_to", dateTo);
            request.setAttribute("status", status);
            request.setAttribute("sorting", sorting);

            request.getSession().setAttribute("status_list", statusList);
            request.getSession().setAttribute("cart_list", cartList);
            request.getSession().setAttribute("pages_carts", pages);
            return new Page(PathPageManager.getProperty("page.admin-carts")).setDispatchType(Page.DispatchType.FORWARD);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
        }

    }
}
