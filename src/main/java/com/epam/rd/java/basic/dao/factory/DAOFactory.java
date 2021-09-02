package com.epam.rd.java.basic.dao.factory;

import com.epam.rd.java.basic.dao.*;

import java.sql.Connection;

public interface DAOFactory {

    CartDAO getCartDAO(Connection connection);
    CartItemDAO getCartItemDAO(Connection connection);
    CategoryDAO getCategoryDAO(Connection connection);
    ItemDAO getItemDAO(Connection connection);
    RoleDAO getRoleDAO(Connection connection);
    StatusDAO getStatusDAO(Connection connection);
    UserDAO getUserDAO(Connection connection);
    UserDetailsDAO getUserDetailsDAO(Connection connection);

}

