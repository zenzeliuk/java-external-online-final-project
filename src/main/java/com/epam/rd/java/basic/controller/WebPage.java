package com.epam.rd.java.basic.controller;

public class WebPage {

    private DispatchType dispatchType;
    private WebPageBase base;
    private String query;

    public static final WebPage STAY = new WebPage(WebPageBase.STAY).setDispatchType(DispatchType.STAY);

    public WebPage(WebPageBase baseUrl) {
        base = baseUrl;
        query = "";
        dispatchType = DispatchType.FORWARD;
    }

    public String getQueryString() {
        return query;
    }

    public WebPage setQueryString(String queryString) {
        this.query = queryString;
        return this;
    }

    public String getPath() {
        return base.getPath() + query;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public WebPage setDispatchType(DispatchType dispatchType) {
        this.dispatchType = dispatchType;
        return this;
    }

    public enum DispatchType {FORWARD, REDIRECT, STAY}

    public enum WebPageBase {

        LOGIN_PAGE("/login.jsp"),
        HOME_PAGE("/index.jsp"),
        ITEM_PAGE("/jsp/item.jsp"),
        ERROR_PAGE("/error.jsp"),
        ERROR_ACTION("Error"),
        STAY("");

        final String path;

        WebPageBase(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }
}
