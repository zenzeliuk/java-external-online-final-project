package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.DAOFactory;
import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.dao.UserDAO;

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
}
