package com.epam.rd.java.basic.controller;

import com.epam.rd.java.basic.controller.command.Command;
import com.epam.rd.java.basic.controller.command.CommandList;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandKey = req.getParameter("command");
        Command command = CommandList.parse(commandKey);
        String view = command.execute(req, resp);

        if (view != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(view);
            dispatcher.forward(req, resp);
        }

    }
}
