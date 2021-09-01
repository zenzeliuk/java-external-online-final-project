package com.epam.rd.java.basic.dao;

import java.sql.Connection;

public interface DAOFactory {

    UserDAO getUserDAO(Connection connection);
    ItemDAO getItemDAO(Connection connection);
}

