package com.epam.rd.java.basic.dao.impl.mysql;

import com.epam.rd.java.basic.dao.DAOFactory;
import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.dao.DBConnection;


public class DAOFactoryImpl implements DAOFactory {

    @Override
    public UserDAO getUserDAO(DBConnection connection) {
        return new UserDAOImpl(connection.getConnection());
    }
}
