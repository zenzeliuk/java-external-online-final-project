package com.epam.rd.java.basic.controller.authorization.command;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
