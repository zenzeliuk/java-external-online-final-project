package com.epam.rd.java.basic.dao.impl.mysql;

import com.epam.rd.java.basic.dao.DAOFactory;
import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;


public class MysqlDAOFactory implements DAOFactory {

    @Override
    public UserDAO getUserDAO(DBConnection connection) {
        return new MysqlUserDAO(connection.getConnection());
    }
}
