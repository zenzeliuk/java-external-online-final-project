package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminHomeCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("role");

        if (role == null || user == null || Role.ADMIN.getName().equals(role.getName())) {
            return new Page(Page.WebPath.LOGIN.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }


        return new Page(PathPageManager.getProperty("page.admin")).setDispatchType(Page.DispatchType.FORWARD);
    }

}
