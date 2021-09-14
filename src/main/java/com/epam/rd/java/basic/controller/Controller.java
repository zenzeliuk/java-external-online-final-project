package com.epam.rd.java.basic.controller;

import com.epam.rd.java.basic.controller.util.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "main", urlPatterns = {"/login/*", "/admin/*", "/app/*", "/user/*"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Page resultPage = null;
        CommandFactory commandFactory = new CommandFactory(request);
        Command command = commandFactory.getCommand();
        resultPage = command.execute(request, response);
        if (resultPage == null) {
            resultPage = new Page("/index.jsp").setDispatchType(Page.DispatchType.FORWARD);
            dispatch(request, response, resultPage.getName());
        } else if (resultPage.getDispatchType().equals(Page.DispatchType.REDIRECT)) {
            response.sendRedirect(request.getContextPath() + resultPage.getName());
        } else {
            dispatch(request, response, resultPage.getName());
        }
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
