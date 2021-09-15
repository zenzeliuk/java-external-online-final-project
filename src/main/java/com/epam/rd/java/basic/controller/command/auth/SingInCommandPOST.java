package com.epam.rd.java.basic.controller.command.auth;

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

public class SingInCommandPOST implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ServiceFactory factory = new ServiceFactoryImpl();
        UserService userService = factory.getUserService();
        RoleService roleService = factory.getRoleService();

        try {
            User user = userService.findByLoginAndPassword(login, password);
            Role role = roleService.findById(user.getRoleId());
            if (role.getName().equals(Role.BLOCKED.getName())) {
                request.setAttribute("user_blocked", true);
                return new Page(PathPageManager.getProperty("page.error")).setDispatchType(Page.DispatchType.FORWARD);
            }
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", role);
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute("error", true);
            request.setAttribute("login", login);
            return new Page(PathPageManager.getProperty("page.sign-in")).setDispatchType(Page.DispatchType.FORWARD);
        }
    }

}
