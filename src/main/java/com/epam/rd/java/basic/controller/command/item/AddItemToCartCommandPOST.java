package com.epam.rd.java.basic.controller.command.item;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.service.CartItemService;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddItemToCartCommandPOST implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        int itemId = Integer.parseInt(request.getParameter("item_id"));
        Cart cart = (Cart) session.getAttribute("cart");

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();
        CartItemService cartItemService = factory.getCartItemService();
        try {
            Item item = itemService.get(itemId);
            CartItem cartItem = CartItem.builder()
                    .cartId(cart.getId())
                    .itemId(item.getId())
                    .price(item.getPrice())
                    .countItem(1)
                    .build();
            cartItemService.create(cartItem);

            return new Page(Page.WebPath.ITEMS.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }

    }

}
