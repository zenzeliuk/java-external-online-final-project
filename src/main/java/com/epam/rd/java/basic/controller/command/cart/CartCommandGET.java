package com.epam.rd.java.basic.controller.command.cart;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.dto.CartItemDTO;
import com.epam.rd.java.basic.model.mapper.CartItemMapper;
import com.epam.rd.java.basic.service.CartItemService;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartCommandGET implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();
        CartItemService cartItemService = factory.getCartItemService();

        List<CartItem> cartItemList;
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        if (cart != null) {
            try {
                cartItemList = cartItemService.findAllByCartId(cart.getId());
                for (CartItem ci : cartItemList) {
                    Item item = itemService.get(ci.getItemId());
                    CartItemDTO cartItemDTO = CartItemMapper.toCartItemDTO(ci, item);
                    cartItemDTOList.add(cartItemDTO);
                }
            } catch (ServiceException e) {
                return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
            }
        }

        request.getSession().setAttribute("cartitemDTO", cartItemDTOList);

        return new Page(PathPageManager.getProperty("page.cart")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
