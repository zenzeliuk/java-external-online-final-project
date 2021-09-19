package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.RoleService;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleCommandGET implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        Integer idUser = Integer.valueOf(request.getParameter("user_id"));

        ServiceFactory factory = new ServiceFactoryImpl();
        UserService userService = factory.getUserService();
        RoleService roleService = factory.getRoleService();

        try {
            User user = userService.get(idUser);
            Role roleUser = roleService.findByName(Role.USER.getName());
            Role roleBlocked = roleService.findByName(Role.BLOCKED.getName());
            if (user.getRoleId() == roleUser.getId()) {
                user.setRoleId(roleBlocked.getId());
            } else if (user.getRoleId() == roleBlocked.getId()) {
                user.setRoleId(roleUser.getId());
            }
            userService.update(user);
            return new Page(Page.WebPath.USERS.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }


    }
}
