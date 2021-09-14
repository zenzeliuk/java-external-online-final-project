package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.CartItem;

import java.util.List;

public interface CartItemDAO extends AbstractDAO<CartItem> {

    List<CartItem> findAllByCartId(int id) throws DaoException;
}
