package com.epam.rd.java.basic.controller.command;

import com.epam.rd.java.basic.controller.AttributeConstant;
import com.epam.rd.java.basic.controller.Path;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
public class LoginCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = request.getParameter(AttributeConstant.LOGIN);
        String password = request.getParameter(AttributeConstant.PASSWORD);

        String forward = Path.ERROR_PAGE;
        String error;

        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            error = "One or more of the input boxes were blank. Try again.";
            session.setAttribute(AttributeConstant.ERROR, error);
            return forward;
        }

        ServiceFactory factory = new ServiceFactoryImpl();
        UserService userService = factory.getUserService();

        try {
            User user = userService.findByLogin(login);
            if (!user.getPassword().equals(password)) {
                error = "Wrong password";
                session.setAttribute(AttributeConstant.ERROR, error);
            } else {
                session.setAttribute(AttributeConstant.USER, user);
                forward = Path.HOME_PAGE;
            }
            return forward;
        } catch (ServiceException e) {
            error = "No such user found";
            session.setAttribute(AttributeConstant.ERROR, error);
            return forward;
        }
    }
}
