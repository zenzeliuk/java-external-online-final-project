package com.epam.rd.java.basic.controller.command.cart;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.Status;
import com.epam.rd.java.basic.service.CartService;
import com.epam.rd.java.basic.service.StatusService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmCartCommandPOST implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        Cart cart = (Cart) request.getSession().getAttribute("cart");

        ServiceFactory factory = new ServiceFactoryImpl();
        CartService cartService = factory.getCartService();
        StatusService statusService = factory.getStatusService();

        try {
            Status status = statusService.getByName(Status.REGISTERED.getName());
            cart.setStatusId(status.getId());
            cartService.update(cart);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("cart", null);

        return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
    }

}
