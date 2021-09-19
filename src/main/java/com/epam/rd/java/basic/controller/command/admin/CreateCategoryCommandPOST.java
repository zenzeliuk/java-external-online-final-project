package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.service.CategoryService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCategoryCommandPOST implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        String categoryName = request.getParameter("name_category");
        Category category = new Category();
        category.setName(categoryName);

        ServiceFactory factory = new ServiceFactoryImpl();
        CategoryService categoryService = factory.getCategoryService();

        try {
            categoryService.create(category);
            return new Page(Page.WebPath.CREATE_ITEM.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }
    }
}
