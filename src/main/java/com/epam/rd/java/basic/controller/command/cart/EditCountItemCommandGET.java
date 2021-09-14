package com.epam.rd.java.basic.controller.command.cart;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.service.CartItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCountItemCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        Integer countItem = Integer.parseInt(request.getParameter("count-item"));
        Integer cartItemId = Integer.parseInt(request.getParameter("cart-item-id"));

        ServiceFactory factory = new ServiceFactoryImpl();
        CartItemService cartItemService = factory.getCartItemService();

        try {
            CartItem cartItem = cartItemService.get(cartItemId);
            cartItem.setCountItem(countItem);
            cartItemService.update(cartItem);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new Page(Page.WebPath.CART.getPath()).setDispatchType(Page.DispatchType.FORWARD);
    }
}
