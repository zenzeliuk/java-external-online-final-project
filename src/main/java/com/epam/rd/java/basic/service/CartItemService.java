package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.CartItem;

import java.util.List;

public interface CartItemService extends AbstractService<CartItem> {
    List<CartItem> findAllByCartId(int id) throws ServiceException;
}
