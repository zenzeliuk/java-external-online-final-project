package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.User;

import java.util.List;

public interface UserDAO extends AbstractDAO<User> {

    User findByLogin(String login) throws DaoException;
    User findByLoginAndPassword(String login, String password) throws DaoException;
    List<User> findAllWithPagination (Integer start, Integer total) throws DaoException;
    Integer getCountRows() throws DaoException;

}
