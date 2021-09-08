package com.epam.rd.java.basic.controller.command;

import com.epam.rd.java.basic.controller.AttributeConstant;
import com.epam.rd.java.basic.controller.Path;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.CartItemService;
import com.epam.rd.java.basic.service.CartService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class AddToCartCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(AttributeConstant.USER);
        Cart cart = (Cart) session.getAttribute(AttributeConstant.CART);
        String idItem = request.getParameter(AttributeConstant.ID_ITEM);
        BigDecimal priceItem = BigDecimal.valueOf(Long.parseLong(request.getParameter(AttributeConstant.PRICE)));
        int count = Integer.parseInt(request.getParameter(AttributeConstant.COUNT));

        ServiceFactory factory = new ServiceFactoryImpl();
        CartService cartService = factory.getCartService();
        CartItemService cartItemService = factory.getCartItemService();
        String error;

        if (user == null) {
            error = "User was not authorization.";
            session.setAttribute(AttributeConstant.ERROR, error);
            return Path.ERROR_PAGE;
        }

        if (cart == null) {
            try {
                cart = cartService.getUserCartWithEmptyStatus(user);
            } catch (ServiceException e) {
                error = "Cannot get cartId.";
                session.setAttribute(AttributeConstant.ERROR, error);
                return Path.ERROR_PAGE;
            }
        }

        CartItem cartItem = CartItem.builder()
                .cartId(cart.getId())
                .itemId(Integer.valueOf(idItem))
                .price(priceItem)
                .countItem(count)
                .build();
        try {
            cartItemService.create(cartItem);
        } catch (ServiceException e) {
            error = "Cannot add item to cart.";
            session.setAttribute(AttributeConstant.ERROR, error);
            return Path.ERROR_PAGE;
        }

        session.setAttribute(AttributeConstant.CART, cart);
        return Path.ITEM_PAGE;
    }


}