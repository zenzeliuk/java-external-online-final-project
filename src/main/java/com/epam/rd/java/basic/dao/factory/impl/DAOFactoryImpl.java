package com.epam.rd.java.basic.dao.factory.impl;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.impl.CartDAOImpl;
import com.epam.rd.java.basic.dao.impl.ItemDAOImpl;
import com.epam.rd.java.basic.dao.impl.UserDAOImpl;

import java.sql.Connection;

public class DAOFactoryImpl implements DAOFactory {

    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new UserDAOImpl(connection);
    }

    @Override
    public ItemDAO getItemDAO(Connection connection) {
        return new ItemDAOImpl(connection);
    }

    @Override
    public CartDAO getCartDAO(Connection connection) {
        return new CartDAOImpl(connection);
    }

}
