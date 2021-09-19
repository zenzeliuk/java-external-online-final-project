package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.service.CartService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeStatusCartPOST implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        Integer cartId = Integer.valueOf(request.getParameter("cart_id"));
        Integer statusId = Integer.valueOf(request.getParameter("status_id"));

        ServiceFactory factory = new ServiceFactoryImpl();
        CartService cartService = factory.getCartService();

        try {
            Cart cart = cartService.get(cartId);
            System.out.println(cart);
            cart.setStatusId(statusId);
            cartService.update(cart);
            return new Page(Page.WebPath.CARTS.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }


    }
}
