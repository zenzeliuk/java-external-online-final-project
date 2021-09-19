package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Item;

import java.util.List;

public interface ItemDAO extends AbstractDAO<Item> {

    Integer getCountRows(String categoryId, String colorId, String brandId, String priceFrom, String priceTo) throws DaoException;

    List<Item> findWithPaginationFilterAndSorting(String categoryId, String colorId, String brandId, String priceFrom, String priceTo, String page, String sorting) throws DaoException;
}
