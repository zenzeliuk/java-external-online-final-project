package com.epam.rd.java.basic.controller.command.item;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.service.CategoryService;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ItemsCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();
        CategoryService categoryService = factory.getCategoryService();


        try {
            List<Item> itemList = itemService.findAll();
            List<Category> categoryList = categoryService.findAll();
            request.getSession().setAttribute("category_list", categoryList);
            request.getSession().setAttribute("items", itemList);
            return new Page(PathPageManager.getProperty("page.items")).setDispatchType(Page.DispatchType.FORWARD);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
        }
    }
}
