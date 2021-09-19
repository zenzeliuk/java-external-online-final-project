package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Item;

import java.util.List;

public interface ItemService extends AbstractService<Item> {
    List<Integer> getPages(Integer categoryId, Integer colorId, Integer brandId) throws ServiceException;

    List<Item> findWithPaginationFilterAndSorting
            (Integer page, Integer categoryId, Integer colorId, Integer brandId, String sortingId)
            throws ServiceException;
}
