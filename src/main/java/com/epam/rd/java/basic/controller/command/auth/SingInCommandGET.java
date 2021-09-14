package com.epam.rd.java.basic.controller.command.auth;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingInCommandGET implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return new Page(PathPageManager.getProperty("page.sign-in")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
