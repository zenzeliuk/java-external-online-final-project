package com.epam.rd.java.basic.dao.factory.impl;

import com.epam.rd.java.basic.dao.*;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.impl.*;

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

    @Override
    public StatusDAO getStatusDAO(Connection connection) {
        return new StatusDAOImpl(connection);
    }

    @Override
    public CategoryDAO getCategoryDAO(Connection connection) {
        return new CategoryDAOImpl(connection);
    }

    @Override
    public RoleDAO getRoleDAO(Connection connection) {
        return new RoleDAOImpl(connection);
    }

    @Override
    public CartItemDAO getCartItemDAO(Connection connection) {
        return new CartItemDAOImpl(connection);
    }

    @Override
    public UserDetailsDAO getUserDetailsDAO(Connection connection) {
        return new UserDetailsDAOImpl(connection);
    }

}
