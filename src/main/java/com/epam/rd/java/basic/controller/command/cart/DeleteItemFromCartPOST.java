package com.epam.rd.java.basic.controller.command.cart;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.service.CartItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Log4j2
public class DeleteItemFromCartPOST implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        Integer cartItemId = Integer.parseInt(request.getParameter("cart_item_id"));
        ServiceFactory factory = new ServiceFactoryImpl();
        CartItemService cartItemService = factory.getCartItemService();
        try {
            cartItemService.deleteCartItem(cartItemId);
            return new Page(Page.WebPath.CART.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            log.warn("Can not delete item from cart. " + e.getMessage());
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }
    }
}
