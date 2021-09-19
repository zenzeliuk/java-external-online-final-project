package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Item;

import java.util.List;

public interface ItemDAO extends AbstractDAO<Item> {

    Integer getCountRows(Integer categoryId, Integer colorId, Integer brandId) throws DaoException;

    List<Item> findWithPaginationFilterAndSorting(Integer page, Integer categoryId, Integer colorId, Integer brandId, String sortingId) throws DaoException;
}
