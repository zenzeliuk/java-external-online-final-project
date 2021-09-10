package com.epam.rd.java.basic.controller;

import com.epam.rd.java.basic.exception.UnableToParseUriException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class UriMarshaller {

    String requestUri;
    String action = null;
    private static final int INDEX_OF_ACTION_IN_URL_PARTS_ARRAY = 1;

    public UriMarshaller(HttpServletRequest request) {
        requestUri = request.getRequestURI().substring(request.getContextPath().length());
    }

    public String getAction() {
        if (action != null) {
            return action;
        } else {
            action = parseURI();
        }
        return action;
    }

    private String parseURI() {
        String[] uriParts = requestUri.split("/");
        uriParts = Arrays.stream(uriParts).filter(x -> !x.equals("")).toArray(String[]::new);
        if (uriParts.length >= 2) {
            return uriParts[INDEX_OF_ACTION_IN_URL_PARTS_ARRAY];
        } else {
            throw new UnableToParseUriException("No action part found in uri");
        }
    }

}
