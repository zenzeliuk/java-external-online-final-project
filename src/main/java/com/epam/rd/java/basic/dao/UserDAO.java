package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.User;

public interface UserDAO extends AbstractDAO<User> {

    User findByLogin(String login) throws DaoException;
}
