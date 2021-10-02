package com.epam.rd.java.basic.controller.command.user;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.dto.CartItemDTO;
import com.epam.rd.java.basic.model.mapper.CartItemMapper;
import com.epam.rd.java.basic.service.CartItemService;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ShowCartGET implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        Integer cartId = Integer.parseInt(request.getParameter("cartId"));
        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();
        CartItemService cartItemService = factory.getCartItemService();

        try {
            List<CartItem> cartItemList = cartItemService.findAllByCartId(cartId);
            List<CartItemDTO> cartItemDTOList = new ArrayList<>();
            for (CartItem ci : cartItemList) {
                Item item = itemService.get(ci.getItemId());
                CartItemDTO cartItemDTO = CartItemMapper.toCartItemDTO(ci, item);
                cartItemDTOList.add(cartItemDTO);
            }
            request.setAttribute("cartItemDTOList", cartItemDTOList);
            return new Page(PathPageManager.getProperty("page.cart-details")).setDispatchType(Page.DispatchType.FORWARD);
        } catch (ServiceException e) {
            log.warn(String.format("Can not find carts with id=%d. %s", cartId, e.getMessage()));
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }

    }
}
