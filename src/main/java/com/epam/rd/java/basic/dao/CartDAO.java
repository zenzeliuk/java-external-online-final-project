package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.dto.CartDTO;

import java.util.List;

public interface CartDAO extends AbstractDAO<Cart> {

    Cart getCartByUserIdAndStatusName(int id, String nameStatus) throws DaoException;

    List<CartDTO> findAllByUserId(int id) throws DaoException;

    Integer getCountRows(String user, String dateFrom, String dateTo, String status) throws DaoException;

    List<CartDTO> findWithPaginationFilterAndSorting(String user, String dateFrom, String dateTo, String status, String page, String sorting) throws DaoException;
}
