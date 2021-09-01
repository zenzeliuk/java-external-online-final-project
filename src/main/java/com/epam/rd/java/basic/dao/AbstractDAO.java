package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.CannotExecuteQueryToDBException;
import com.epam.rd.java.basic.model.BaseEntity;

import java.util.List;

public interface AbstractDAO<T extends BaseEntity> {

    List<T> findAll() throws CannotExecuteQueryToDBException;
    int create(T t) throws CannotExecuteQueryToDBException;
    T get(int id) throws CannotExecuteQueryToDBException;
    boolean update(T entity) throws CannotExecuteQueryToDBException;
    boolean delete(int id) throws CannotExecuteQueryToDBException;

}
