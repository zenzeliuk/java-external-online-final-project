package com.epam.rd.java.basic.controller.command.auth;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommandGET implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
    }
}
