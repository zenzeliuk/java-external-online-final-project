package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Status;

public interface StatusDAO extends AbstractDAO<Status> {

    Status getByName(String name) throws DaoException;
}
