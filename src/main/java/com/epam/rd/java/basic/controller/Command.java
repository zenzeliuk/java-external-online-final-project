package com.epam.rd.java.basic.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    WebPage execute(HttpServletRequest request, HttpServletResponse response);

}
