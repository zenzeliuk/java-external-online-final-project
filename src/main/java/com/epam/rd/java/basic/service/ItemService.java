package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.ItemDetails;

import java.util.List;

public interface ItemService extends AbstractService<Item> {
    List<Integer> getPages(String categoryId, String colorId, String brandId, String priceFrom, String priceTo) throws ServiceException;

    List<Item> findWithPaginationFilterAndSorting
            (String categoryId, String colorId, String brandId, String priceFrom, String priceTo, String page, String sorting)
            throws ServiceException;

    void createItemWithDetails(Item item, ItemDetails itemDetails) throws ServiceException;
}
