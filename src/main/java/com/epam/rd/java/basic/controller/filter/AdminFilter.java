package com.epam.rd.java.basic.controller.filter;

import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("role");

        if (role == null || user == null || !Role.ADMIN.getName().equals(role.getName())) {
            RequestDispatcher dispatcher = ((HttpServletRequest) request).getSession()
                    .getServletContext().getRequestDispatcher(Page.WebPath.HOME.getPath());
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }
}
