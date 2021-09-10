//package com.epam.rd.java.basic.controller.old;
//
//
//import com.epam.rd.java.basic.controller.Command;
//
//@Log4j2
//public class LoginCommand implements Command {
//
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//
//        HttpSession session = request.getSession();
//
//        String login = request.getParameter(AttributeConstant.LOGIN);
//        String password = request.getParameter(AttributeConstant.PASSWORD);
//
//        String forward = Path.ERROR_PAGE;
//        String error;
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
