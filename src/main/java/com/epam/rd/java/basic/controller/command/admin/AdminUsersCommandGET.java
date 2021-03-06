package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.dto.UserDTO;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AdminUsersCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        String pg = request.getParameter("page");

        if (pg == null){
            pg = "1";
        }
        Integer page = Integer.valueOf(pg);

        ServiceFactory factory = new ServiceFactoryImpl();
        UserService userService = factory.getUserService();

        List<UserDTO> userListDTO = null;
        List<Integer> pages = new ArrayList<>();


        try {
            Integer countRows = userService.getCountRows();
            if (countRows != null) {
                int p = (countRows / 10) + 1;
                for (int i = 1; i <= p; i++) {
                    pages.add(i);
                }
            }
            Integer start;
            if (page == 1) {
                start = 1;
            } else {
                start = (page - 1) * 10 + 1;
            }
            userListDTO = userService.findAllWithPaginationDTO(start, 10);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        request.getSession().setAttribute("pages", pages);
        request.getSession().setAttribute("user_list", userListDTO);
        return new Page(PathPageManager.getProperty("page.admin-users")).setDispatchType(Page.DispatchType.FORWARD);
    }

}
