package com.epam.rd.java.basic.controller.command.item;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import com.epam.rd.java.basic.controller.util.Sorting;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Brand;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Color;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.dto.ItemDTO;
import com.epam.rd.java.basic.service.BrandService;
import com.epam.rd.java.basic.service.CategoryService;
import com.epam.rd.java.basic.service.ColorService;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.util.Objects.nonNull;

public class ItemsCommandGET implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        String sortingId = request.getParameter("sorting_id");
        String p = request.getParameter("page");
        String caId = request.getParameter("category_id");
        String coId = request.getParameter("color_id");
        String bId = request.getParameter("brand_id");

        Integer page = 1;
        Integer categoryId = null;
        Integer colorId = null;
        Integer brandId = null;

        if (nonNull(p)) {
            page = Integer.valueOf(p);
        }
        if (nonNull(caId)) {
            categoryId = Integer.valueOf(caId);
        }
        if (nonNull(coId)) {
            colorId = Integer.valueOf(coId);
        }
        if (nonNull(bId)) {
            brandId = Integer.valueOf(bId);
        }

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();
        CategoryService categoryService = factory.getCategoryService();
        ColorService colorService = factory.getColorService();
        BrandService brandService = factory.getBrandService();

        try {
            List<Integer> pages = itemService.getPages(categoryId, colorId, brandId);
            List<Item> itemList = itemService.findWithPaginationFilterAndSorting
                    (page, categoryId, colorId, brandId, sortingId);
            List<Category> categoryList = categoryService.findAll();
            List<Color> colorList = colorService.findAll();
            List<Brand> brandList = brandService.findAll();

            request.setAttribute("page", page);
            request.setAttribute("category_id", categoryId);
            request.setAttribute("color_id", colorId);
            request.setAttribute("brand_id", brandId);
            request.getSession().setAttribute("pages_item", pages);
            request.getSession().setAttribute("items", itemList);
            request.getSession().setAttribute("category_list", categoryList);
            request.getSession().setAttribute("color_list", colorList);
            request.getSession().setAttribute("brand_list", brandList);
            return new Page(PathPageManager.getProperty("page.items")).setDispatchType(Page.DispatchType.FORWARD);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.FORWARD);
        }
    }
}
