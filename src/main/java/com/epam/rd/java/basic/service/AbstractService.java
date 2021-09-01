package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.model.BaseEntity;

import java.util.List;

public interface AbstractService <T extends BaseEntity> {

    List<T> findAll();
    int create(T t);
    T get(int id);
    boolean update(T entity);
    boolean delete(int id);
}
