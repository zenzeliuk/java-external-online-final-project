package com.epam.rd.java.basic.dao.factory;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.dao.StatusDAO;
import com.epam.rd.java.basic.dao.UserDAO;

import java.sql.Connection;

public interface DAOFactory {

    UserDAO getUserDAO(Connection connection);
    ItemDAO getItemDAO(Connection connection);
    CartDAO getCartDAO(Connection connection);
    StatusDAO getStatusDAO(Connection connection);

}

