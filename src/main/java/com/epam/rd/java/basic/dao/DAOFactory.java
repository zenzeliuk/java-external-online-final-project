package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.dao.connection.DBConnection;

public interface DAOFactory {

    UserDAO getUserDAO(DBConnection connection);

}

