package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.BrandDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Brand;

import java.sql.Connection;
import java.util.List;

public class BrandDAOImpl implements BrandDAO {

    private final Connection connection;
    private final CloseResources close;

    public BrandDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    //TODO
    @Override
    public List<Brand> findAll() throws DaoException {
        return null;
    }

    @Override
    public int create(Brand brand) throws DaoException {
        return 0;
    }

    @Override
    public Brand get(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean update(Brand brand) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }
}
