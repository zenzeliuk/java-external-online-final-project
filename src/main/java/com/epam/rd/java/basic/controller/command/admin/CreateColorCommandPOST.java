package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Color;
import com.epam.rd.java.basic.service.ColorService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateColorCommandPOST implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String colorName = request.getParameter("name_color");
        Color color = new Color();
        color.setName(colorName);

        ServiceFactory factory = new ServiceFactoryImpl();
        ColorService colorService = factory.getColorService();

        try {
            colorService.create(color);
            return new Page(Page.WebPath.CREATE_ITEM.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }
    }
}
