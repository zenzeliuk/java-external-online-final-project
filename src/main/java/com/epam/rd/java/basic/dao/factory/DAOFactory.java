package com.epam.rd.java.basic.dao.factory;

import com.epam.rd.java.basic.dao.*;
import com.epam.rd.java.basic.dao.connection.DBConnection;

import java.sql.Connection;

public interface DAOFactory {

    BrandDAO getBrandDAO(Connection connection);
    CartDAO getCartDAO(Connection connection);
    CartItemDAO getCartItemDAO(Connection connection);
    CategoryDAO getCategoryDAO(Connection connection);
    ColorDAO getColorDAO(Connection connection);
    ItemDAO getItemDAO(Connection connection);
    ItemDetailsDAO getItemDetailsDAO(Connection connection);
    RoleDAO getRoleDAO(Connection connection);
    StatusDAO getStatusDAO(Connection connection);
    UserDAO getUserDAO(Connection connection);
    UserDetailsDAO getUserDetailsDAO(Connection connection);
}

