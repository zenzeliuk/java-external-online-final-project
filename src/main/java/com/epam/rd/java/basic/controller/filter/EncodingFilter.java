package com.epam.rd.java.basic.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"},
        initParams = @WebInitParam(name = "requestEncoding", value = "UTF-8"))
public class EncodingFilter implements Filter {

    private String encoding;
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if (encoding == null)
            encoding = DEFAULT_ENCODING;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding(encoding);

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }
}
