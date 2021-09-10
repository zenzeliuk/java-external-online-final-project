package com.epam.rd.java.basic.controller.authorization;

import com.epam.rd.java.basic.controller.WebPage;
import com.epam.rd.java.basic.controller.WebPageDispatcher;
import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.CommandFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "Login", urlPatterns = {"/Login/*"})
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebPage result;
        try {

            CommandFactory factory = new AuthorizationCommandFactory(req, resp);
            Command command = factory.defineCommand();
            result = command.execute(req, resp);
        } catch (Exception e) {
            result = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        }

        new WebPageDispatcher(req, resp, result).dispatch();
    }
}
