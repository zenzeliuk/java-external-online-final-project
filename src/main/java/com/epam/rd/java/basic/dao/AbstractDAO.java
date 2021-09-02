package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Entity;

import java.util.List;

public interface AbstractDAO<T extends Entity> {

    List<T> findAll() throws DaoException;
    int create(T t) throws DaoException;
    T get(int id) throws DaoException;
    boolean update(T t) throws DaoException;
    boolean delete(int id) throws DaoException;

}
