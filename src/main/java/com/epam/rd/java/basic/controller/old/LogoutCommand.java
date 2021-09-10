//package com.epam.rd.java.basic.controller.old;
//
//
//import com.epam.rd.java.basic.controller.Command;
//
//public class LogoutCommand implements Command {
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//
//        HttpSession session = request.getSession();
//
//        if (session != null) {
//            session.invalidate();
//        }
//        return Path.HOME_PAGE;
//    }
//}
