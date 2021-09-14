package com.epam.rd.java.basic.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class LocalizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("lang") != null) {
            req.getSession().setAttribute("lang", req.getParameter("lang"));
            Locale.setDefault(new Locale(req.getParameter("lang")));
        }
        chain.doFilter(request, response);
    }
}
