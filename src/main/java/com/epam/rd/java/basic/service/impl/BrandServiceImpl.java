package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.BrandDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Brand;
import com.epam.rd.java.basic.service.BrandService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class BrandServiceImpl implements BrandService {

    private final DAOFactory daoFactory;
    private BrandDAO brandDAO;

    public BrandServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<Brand> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            brandDAO = daoFactory.getBrandDAO(dbConnection.getConnection());
            return brandDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all brand. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Brand create(Brand brand) throws ServiceException {
        return null;
    }

    @Override
    public Brand get(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Brand brand) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }
}
