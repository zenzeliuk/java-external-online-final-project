package com.epam.rd.java.basic.controller.command;

import com.epam.rd.java.basic.controller.AttributeConstant;
import com.epam.rd.java.basic.controller.Path;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.dto.ItemDTO;
import com.epam.rd.java.basic.model.mapper.ItemMapper;
import com.epam.rd.java.basic.service.ItemService;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.factory.impl.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
public class ItemsCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String error;

        ServiceFactory factory = new ServiceFactoryImpl();
        ItemService itemService = factory.getItemService();

        try {
            List<Item> itemList = itemService.findAll();
            List<ItemDTO> itemDTOList = ItemMapper.toItemDTOList(itemList);
            request.setAttribute("items", itemDTOList);
            return Path.ITEM_PAGE;
        } catch (ServiceException e) {
            log.error("Can not find all items" + e.getMessage());
            error = "Something went wrong, please repeat later.";
            request.setAttribute(AttributeConstant.ERROR, error);
            return Path.ERROR_PAGE;
        }
    }

}
