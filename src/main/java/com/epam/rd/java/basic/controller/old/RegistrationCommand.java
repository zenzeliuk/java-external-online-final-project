//package com.epam.rd.java.basic.controller.old;
//
//
//import com.epam.rd.java.basic.controller.Command;
//
//@Log4j2
//public class RegistrationCommand implements Command {
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//
//        HttpSession session = request.getSession();
//
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//
//        String error;
//        if (login == null || password == null || login.isBlank() || password.isBlank()) {
//            error = "One or more of the input boxes were blank. Try again.";
//            session.setAttribute(AttributeConstant.ERROR, error);
//            return Path.ERROR_PAGE;
//        }
//
//        ServiceFactory factory = new ServiceFactoryImpl();
//        UserService userService = factory.getUserService();
//        RoleService roleService = factory.getRoleService();
//
//        User userFromDB;
//        Role role;
//
//        try {
//            userFromDB = userService.findByLogin(login);
//            if (userFromDB != null) {
//                error = "This user login is already taken.";
//                session.setAttribute(AttributeConstant.ERROR, error);
//                return Path.ERROR_PAGE;
//            }
//        } catch (ServiceException e) {
//            log.info("User can be continue registration. Login was not find in DB.");
//        }
//
//        try {
//            role = roleService.findByName(Role.USER.getName());
//        } catch (ServiceException e) {
//            log.error("Role user was not find from DB." + e.getMessage());
//            error = "Something went wrong, please repeat later.";
//            session.setAttribute(AttributeConstant.ERROR, error);
//            return Path.ERROR_PAGE;
//        }
//
//        User user = User.builder()
//                .login(login)
//                .password(password)
//                .roleId(role.getId())
//                .build();
//        try {
//            userService.create(user);
//            session.setAttribute("user", user);
//            return Path.HOME_PAGE;
//        } catch (ServiceException e) {
//            error = "Something went wrong, please repeat later.";
//            session.setAttribute(AttributeConstant.ERROR, error);
//            return Path.ERROR_PAGE;
//        }
//    }
//}
