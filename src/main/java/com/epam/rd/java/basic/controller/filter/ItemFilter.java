package com.epam.rd.java.basic.controller.filter;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.model.Status;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.CartItemService;
import com.epam.rd.java.basic.service.CartService;
import com.epam.rd.java.basic.service.StatusService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebFilter(filterName = "ItemFilter", urlPatterns = {"/app/*"})
public class ItemFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        ServiceFactory factory = new ServiceFactoryImpl();
        CartService cartService = factory.getCartService();
        CartItemService cartItemService = factory.getCartItemService();
        StatusService statusService = factory.getStatusService();

        if (user == null) {
            if (cart == null) {
                try {
                    cart = getEmptyCart(cartService, statusService);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }

        } else {
            if (cart == null) {
                try {
                    cart = cartService.getCartByUserIdAndStatusName(user.getId(), Status.OPEN.getName());
                } catch (ServiceException e) {
                    try {
                        cart = getEmptyCart(cartService, statusService);
                        cart.setUserId(user.getId());
                        cartService.update(cart);
                    } catch (ServiceException ex) {
                        ex.printStackTrace();
                    }
                }

            } else if (cart.getUserId() == null) {
                try {
                    List<CartItem> cartItemList = cartItemService.findAllByCartId(cart.getId());

                    Cart cartFromDB = cartService.getCartByUserIdAndStatusName(user.getId(), Status.OPEN.getName());
                    List<CartItem> cartItemListFromDB = cartItemService.findAllByCartId(cartFromDB.getId());

                    for (CartItem ci : cartItemList) {
                        for (CartItem ciDB : cartItemListFromDB) {
                            if (Objects.equals(ciDB.getItemId(), ci.getItemId())) {
                                cartItemService.delete(ci.getId());
                            }
                        }
                        ci.setCartId(cartFromDB.getId());
                        cartItemService.update(ci);
                    }

                    cartService.delete(cart.getId());
                    cart = cartFromDB;

                } catch (ServiceException e) {
                    cart.setUserId(user.getId());
                    try {
                        cartService.update(cart);
                    } catch (ServiceException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }

        List<CartItem> cartItemList = null;
        Status status = null;
        try {
            cartItemList = cartItemService.findAllByCartId(cart.getId());
            status = statusService.get(cart.getStatusId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        List<Integer> idCartItem = cartItemList.stream().map(CartItem::getItemId).collect(Collectors.toList());

        session.setAttribute("status", status);
        session.setAttribute("cart", cart);
        session.setAttribute("cartitem", idCartItem);
        chain.doFilter(request, response);
    }

    private Cart getEmptyCart(CartService cartService, StatusService statusService) throws ServiceException {
        Cart cart;
        Status status = statusService.getByName(Status.OPEN.getName());
        Cart newCart = Cart.builder().statusId(status.getId()).build();
        cart = cartService.create(newCart);
        return cart;
    }
}