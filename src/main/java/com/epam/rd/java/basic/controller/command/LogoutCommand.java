package com.epam.rd.java.basic.controller.command;

import com.epam.rd.java.basic.controller.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (session != null) {
            session.invalidate();
        }
        return Path.HOME_PAGE;
    }
}
