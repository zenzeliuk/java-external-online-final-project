package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Brand;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Color;
import com.epam.rd.java.basic.service.BrandService;
import com.epam.rd.java.basic.service.CategoryService;
import com.epam.rd.java.basic.service.ColorService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateItemCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory factory = new ServiceFactoryImpl();
        CategoryService categoryService = factory.getCategoryService();
        BrandService brandService = factory.getBrandService();
        ColorService colorService = factory.getColorService();

        try {
            List<Category> categoryList = categoryService.findAll();
            List<Brand> brandList = brandService.findAll();
            List<Color> colorList = colorService.findAll();

            request.getSession().setAttribute("category_list", categoryList);
            request.getSession().setAttribute("brand_list", brandList);
            request.getSession().setAttribute("color_list", colorList);
            return new Page(PathPageManager.getProperty("page.admin-create-item")).setDispatchType(Page.DispatchType.FORWARD);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
        }

    }
}
