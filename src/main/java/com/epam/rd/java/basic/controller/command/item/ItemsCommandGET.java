package com.epam.rd.java.basic.controller.command.item;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Brand;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Color;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.service.BrandService;
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

        String page = request.getParameter("page");
        String categoryId = request.getParameter("category_id");
        String colorId = request.getParameter("color_id");
        String brandId = request.getParameter("brand_id");
        String priceFrom = request.getParameter("price_from");
        String priceTo = request.getParameter("price_to");
        String sorting = request.getParameter("sorting");

        if (page == null || page.equals("")) {
            page = "1";
        }
        if (categoryId == null || categoryId.equals("")) {
            categoryId = "0";
        }
        if (colorId == null || colorId.equals("")) {
            colorId = "0";
        }
        if (brandId == null || brandId.equals("")) {
            brandId = "0";
        }
        if (priceFrom == null || priceFrom.equals("")) {
            priceFrom = "0";
        }
        if (priceTo == null || priceTo.equals("")) {
            priceTo = "9999999";
        }
        if (sorting == null || sorting.equals("")) {
            sorting = "0";
        }

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();
        CategoryService categoryService = factory.getCategoryService();
        ColorService colorService = factory.getColorService();
        BrandService brandService = factory.getBrandService();

        try {
            List<Integer> pages = itemService.getPages(categoryId, colorId, brandId, priceFrom, priceTo);
            List<Item> itemList = itemService.findWithPaginationFilterAndSorting
                    (categoryId, colorId, brandId, priceFrom, priceTo, page, sorting);
            List<Category> categoryList = categoryService.findAll();
            List<Color> colorList = colorService.findAll();
            List<Brand> brandList = brandService.findAll();

            request.setAttribute("page", page);
            request.setAttribute("category_id", categoryId);
            request.setAttribute("color_id", colorId);
            request.setAttribute("brand_id", brandId);
            request.setAttribute("price_from", priceFrom);
            request.setAttribute("price_to", priceTo);
            request.setAttribute("sorting", sorting);

            request.getSession().setAttribute("category_list", categoryList);
            request.getSession().setAttribute("color_list", colorList);
            request.getSession().setAttribute("brand_list", brandList);
            request.getSession().setAttribute("items", itemList);
            request.getSession().setAttribute("pages_item", pages);
            return new Page(PathPageManager.getProperty("page.items")).setDispatchType(Page.DispatchType.FORWARD);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
        }
    }
}
