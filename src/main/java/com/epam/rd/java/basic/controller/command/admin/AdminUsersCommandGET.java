package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.model.dto.UserDTO;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminUsersCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory factory = new ServiceFactoryImpl();
        UserService userService = factory.getUserService();

        List<UserDTO> userListDTO = null;
        try {
            userListDTO = userService.findAllDTO();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("user_list", userListDTO);
        return new Page(PathPageManager.getProperty("page.admin-users")).setDispatchType(Page.DispatchType.FORWARD);
    }

}
