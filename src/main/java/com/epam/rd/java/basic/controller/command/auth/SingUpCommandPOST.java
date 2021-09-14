package com.epam.rd.java.basic.controller.command.auth;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.model.UserDetails;
import com.epam.rd.java.basic.service.RoleService;
import com.epam.rd.java.basic.service.UserDetailsService;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingUpCommandPOST implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        User user = User.builder()
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .build();
        UserDetails userDetails = UserDetails.builder()
                .firstName(request.getParameter("firstname"))
                .lastName(request.getParameter("lastname"))
                .email(request.getParameter("email"))
                .phone(Long.valueOf(request.getParameter("phone")))
                .age(Integer.parseInt(request.getParameter("age")))
                .build();

        ServiceFactory factory = new ServiceFactoryImpl();
        RoleService roleService = factory.getRoleService();
        UserService userService = factory.getUserService();
        UserDetailsService userDetailsService = factory.getUserDetailsService();
        try {
            Role role = roleService.findByName(Role.USER.getName());

            user.setRoleId(role.getId());
            User result = userService.create(user);

            userDetails.setId(user.getId());
            userDetailsService.create(userDetails);

            request.getSession().setAttribute("user", result);
            request.getSession().setAttribute("role", role);
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute("error", "msg.error");
            request.setAttribute("user", user);
            request.setAttribute("details", userDetails);
            return new Page(PathPageManager.getProperty("page.sign-up")).setDispatchType(Page.DispatchType.FORWARD);
        }

    }
}
