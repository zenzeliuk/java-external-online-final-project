package com.epam.rd.java.basic.dao;

public interface DAOFactory {

    UserDAO getUserDAO(DBConnection connection);

}

