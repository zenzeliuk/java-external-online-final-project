package com.epam.rd.java.basic.controller.command.item;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Brand;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Color;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.dto.ItemDTO;
import com.epam.rd.java.basic.service.CategoryService;
import com.epam.rd.java.basic.service.ColorService;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ItemsCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer categoryId = Integer.valueOf(request.getParameter("category_id"));
        Integer colorId = Integer.valueOf(request.getParameter("color_id"));
        Integer brandId = Integer.valueOf(request.getParameter("brand_id"));
        Integer sortingId = Integer.valueOf(request.getParameter("sorting_id"));

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();
        CategoryService categoryService = factory.getCategoryService();
        ColorService colorService = factory.getColorService();
        BrandServise brandServise = factory.getBrandServise();

        try {
            List<Item> itemList = itemService.findWithPaginationFilterAndSorting(page, categoryId, colorId, brandId, sortingId);
            List<Integer> pages = itemService.getCountRows(page, categoryId, colorId, brandId, sortingId);

            List<Category> categoryList = categoryService.findAll();
            List<Color> colorList = colorService.findAll();
            List<Brand> brandList = brandServise.findAll();


            request.getSession().setAttribute("category_list", categoryList);
            request.getSession().setAttribute("color_list", colorList);
            request.getSession().setAttribute("brand_list", brandList);

            request.getSession().setAttribute("pages", pages);
            request.getSession().setAttribute("items", itemList);
            return new Page(PathPageManager.getProperty("page.items")).setDispatchType(Page.DispatchType.FORWARD);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
        }
    }
}
