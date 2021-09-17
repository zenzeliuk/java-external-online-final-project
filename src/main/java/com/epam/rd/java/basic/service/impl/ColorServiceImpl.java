package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.CategoryDAO;
import com.epam.rd.java.basic.dao.ColorDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Color;
import com.epam.rd.java.basic.service.ColorService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ColorServiceImpl implements ColorService {

    private final DAOFactory daoFactory;
    private ColorDAO colorDAO;

    public ColorServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<Color> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            colorDAO = daoFactory.getColorDAO(dbConnection.getConnection());
            return colorDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all color. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Color create(Color color) throws ServiceException {
        return null;
    }

    @Override
    public Color get(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Color color) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }
}
