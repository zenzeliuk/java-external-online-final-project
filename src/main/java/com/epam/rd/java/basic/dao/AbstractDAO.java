package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractDAO<K, T extends BaseEntity> {

    List<T> findAll();

    Optional<T> create(T t);

    Optional<T> get(K id);

    Optional<T> update(K id);

    boolean delete(T entity);

}
