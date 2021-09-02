package com.epam.rd.java.basic.dao.factory;

import com.epam.rd.java.basic.dao.*;

import java.sql.Connection;

public interface DAOFactory {

    UserDAO getUserDAO(Connection connection);
    ItemDAO getItemDAO(Connection connection);
    CartDAO getCartDAO(Connection connection);
    StatusDAO getStatusDAO(Connection connection);
    CategoryDAO getCategoryDAO(Connection connection);
    RoleDAO getRoleDAO(Connection connection);
    CartItemDAO getCartItemDAO(Connection connection);
    UserDetailsDAO getUserDetailsDAO(Connection connection);
}

