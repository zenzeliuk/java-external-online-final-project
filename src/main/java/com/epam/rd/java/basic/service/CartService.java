package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.dto.CartDTO;

import java.util.List;

public interface CartService extends AbstractService<Cart>{

    Cart getCartByUserIdAndStatusName(int id, String name) throws ServiceException;

    List<CartDTO> findAllByUserId(int id) throws ServiceException;
}
