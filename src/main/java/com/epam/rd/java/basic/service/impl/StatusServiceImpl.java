package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.StatusDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Status;
import com.epam.rd.java.basic.service.StatusService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class StatusServiceImpl implements StatusService {

    private final DAOFactory daoFactory;
    private StatusDAO statusDAO;

    public StatusServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<Status> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            statusDAO = daoFactory.getStatusDAO(dbConnection.getConnection());
            return statusDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all status. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Status create(Status status) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            statusDAO = daoFactory.getStatusDAO(dbConnection.getConnection());
            int id = statusDAO.create(status);
            status.setId(id);
            dbConnection.commit();
            return status;
        } catch (DaoException e) {
            String exception = "Cannot create status. " + status.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public Status get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            statusDAO = daoFactory.getStatusDAO(dbConnection.getConnection());
            return statusDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get status by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(Status status) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            statusDAO = daoFactory.getStatusDAO(dbConnection.getConnection());
            if (statusDAO.update(status)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update status. " + status.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            statusDAO = daoFactory.getStatusDAO(dbConnection.getConnection());
            if (statusDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete status by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public Status getByName(String name) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            statusDAO = daoFactory.getStatusDAO(dbConnection.getConnection());
            return statusDAO.getByName(name);
        } catch (DaoException e) {
            String exception = String.format("Cannot get status by name='%s'. %s", name, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }
}
