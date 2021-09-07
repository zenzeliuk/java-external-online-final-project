package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.User;

public interface CartService extends AbstractService<Cart>{
    Cart getUserCartWithEmptyStatus(User user) throws ServiceException;
}
