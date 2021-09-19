package com.epam.rd.java.basic.controller.command.admin;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.ItemDetails;
import com.epam.rd.java.basic.service.CategoryService;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class CreateItemCommandPOST implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        String itemName = request.getParameter("name_item");
        String image = request.getParameter("image");
        String price = request.getParameter("price");
        String categoryId = request.getParameter("category_id");
        String brandId = request.getParameter("brand_id");
        String colorId = request.getParameter("color_id");

        Item item = Item.builder()
                .name(itemName)
                .image(image)
                .price(new BigDecimal(price))
                .build();
        ItemDetails itemDetails = ItemDetails.builder()
                .categoryId(Integer.parseInt(categoryId))
                .brandId(Integer.parseInt(brandId))
                .colorId(Integer.parseInt(colorId))
                .build();

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();

        try {
            itemService.createItemWithDetails(item, itemDetails);
            return new Page(Page.WebPath.ADMIN_HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } catch (ServiceException e) {
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        }
    }
}
