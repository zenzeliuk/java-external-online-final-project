package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;

public interface CartService extends AbstractService<Cart>{

    Cart getCartByUserIdAndStatusName(int id, String name) throws ServiceException;
}
