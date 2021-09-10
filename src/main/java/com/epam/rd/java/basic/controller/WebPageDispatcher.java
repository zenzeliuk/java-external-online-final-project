package com.epam.rd.java.basic.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebPageDispatcher {

    HttpServletRequest request;
    HttpServletResponse response;
    WebPage webPage;

    public WebPageDispatcher(HttpServletRequest request, HttpServletResponse response, WebPage webPage) {
        this.request = request;
        this.response = response;
        this.webPage = webPage;
    }

    public void dispatch() throws IOException, ServletException {

        if (webPage == null || webPage.getPath() == null) {
            request.getRequestDispatcher(
                    getUrl(new WebPage(WebPage.WebPageBase.ERROR_ACTION)))
                    .forward(request, response);
            return;
        }

        if (webPage.getDispatchType() == WebPage.DispatchType.REDIRECT) {
            response.sendRedirect(getUrl());
        }
        if (webPage.getDispatchType() == WebPage.DispatchType.FORWARD) {
            request.getRequestDispatcher(getUrl()).forward(request, response);
        }
    }


    private String getUrl(WebPage page) {
        return request.getContextPath() + page.getPath();
    }

    private String getUrl() {
        return request.getContextPath() + webPage.getPath();
    }
}
