package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Entity;

import java.util.List;

public interface AbstractService<T extends Entity> {

    List<T> findAll() throws ServiceException;
    T create(T t) throws ServiceException;
    T get(int id) throws ServiceException;
    boolean update(T t) throws ServiceException;
    boolean delete(int id) throws ServiceException;
}
