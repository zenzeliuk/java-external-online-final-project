package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ColorDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Color;

import java.sql.Connection;
import java.util.List;

public class ColorDAOImpl implements ColorDAO {

    private final Connection connection;
    private final CloseResources close;

    public ColorDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    //TODO
    @Override
    public List<Color> findAll() throws DaoException {
        return null;
    }

    @Override
    public int create(Color color) throws DaoException {
        return 0;
    }

    @Override
    public Color get(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean update(Color color) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }
}
