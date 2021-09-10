package com.epam.rd.java.basic.controller.authorization.command;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.WebPage;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements Command {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";


    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {

        WebPage webPage = new WebPage(WebPage.WebPageBase.LOGIN_PAGE);

        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        UserService userService = serviceFactory.getUserService();






        return webPage;
    }
}


//
//        if (login == null || password == null || login.isBlank() || password.isBlank()) {
//            error = "One or more of the input boxes were blank. Try again.";
//            session.setAttribute(AttributeConstant.ERROR, error);
//            return forward;
//        }
//
//        ServiceFactory factory = new ServiceFactoryImpl();
//        UserService userService = factory.getUserService();
//
//        try {
//            User user = userService.findByLogin(login);
//            if (!user.getPassword().equals(password)) {
//                error = "Wrong password";
//                session.setAttribute(AttributeConstant.ERROR, error);
//            } else {
//                session.setAttribute(AttributeConstant.USER, user);
//                forward = Path.HOME_PAGE;
//            }
//            return forward;
//        } catch (ServiceException e) {
//            error = "No such user found";
//            session.setAttribute(AttributeConstant.ERROR, error);
//            return forward;
//        }
//    }
//}