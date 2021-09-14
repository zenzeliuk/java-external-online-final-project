package com.epam.rd.java.basic.controller.command.user;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.model.dto.CartDTO;
import com.epam.rd.java.basic.service.CartService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserHomeCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        ServiceFactory factory = new ServiceFactoryImpl();
        CartService cartService = factory.getCartService();

        if (user == null) {
            return new Page(Page.WebPath.LOGIN.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }

        List<CartDTO> cartList;
        try {
            cartList = cartService.findAllByUserId(user.getId());
        } catch (ServiceException e) {
            return new Page(Page.WebPath.LOGIN.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }

        session.setAttribute("user_cart_list", cartList);
        return new Page(PathPageManager.getProperty("page.user")).setDispatchType(Page.DispatchType.FORWARD);
    }

}
