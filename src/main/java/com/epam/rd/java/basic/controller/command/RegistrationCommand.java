package com.epam.rd.java.basic.controller.command;

import com.epam.rd.java.basic.controller.AttributeConstant;
import com.epam.rd.java.basic.controller.Path;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.RoleService;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
public class RegistrationCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String error;
        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            error = "One or more of the input boxes were blank. Try again.";
            session.setAttribute(AttributeConstant.ERROR, error);
            return Path.ERROR_PAGE;
        }

        ServiceFactory factory = new ServiceFactoryImpl();
        UserService userService = factory.getUserService();
        RoleService roleService = factory.getRoleService();

        User userFromDB;
        Role role;

        try {
            userFromDB = userService.findByLogin(login);
            if (userFromDB != null) {
                error = "This user login is already taken.";
                session.setAttribute(AttributeConstant.ERROR, error);
                return Path.ERROR_PAGE;
            }
        } catch (ServiceException e) {
            log.info("User can be continue registration. Login was not find in DB.");
        }

        try {
            role = roleService.findByName(Role.USER.getName());
        } catch (ServiceException e) {
            log.error("Role user was not find from DB." + e.getMessage());
            error = "Something went wrong, please repeat later.";
            session.setAttribute(AttributeConstant.ERROR, error);
            return Path.ERROR_PAGE;
        }

        User user = User.builder()
                .login(login)
                .password(password)
                .roleId(role.getId())
                .build();
        try {
            userService.create(user);
            session.setAttribute("user", user);
            return Path.HOME_PAGE;
        } catch (ServiceException e) {
            error = "Something went wrong, please repeat later.";
            session.setAttribute(AttributeConstant.ERROR, error);
            return Path.ERROR_PAGE;
        }
    }
}
