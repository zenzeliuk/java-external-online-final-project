package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.UserDetailsDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.UserDetails;
import com.epam.rd.java.basic.service.UserDetailsService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DAOFactory daoFactory;
    private UserDetailsDAO userDetailsDAO;

    public UserDetailsServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<UserDetails> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            userDetailsDAO = daoFactory.getUserDetailsDAO(dbConnection.getConnection());
            return userDetailsDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all user_details. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public UserDetails create(UserDetails userDetails) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            userDetailsDAO = daoFactory.getUserDetailsDAO(dbConnection.getConnection());
            int id = userDetailsDAO.create(userDetails);
            userDetails.setId(id);
            dbConnection.commit();
            return userDetails;
        } catch (DaoException e) {
            String exception = "Cannot create user_details. " + userDetails.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public UserDetails get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            userDetailsDAO = daoFactory.getUserDetailsDAO(dbConnection.getConnection());
            return userDetailsDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get user_details by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(UserDetails userDetails) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            userDetailsDAO = daoFactory.getUserDetailsDAO(dbConnection.getConnection());
            if (userDetailsDAO.update(userDetails)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update user_details. " + userDetails.toString() + e.getMessage();
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
            userDetailsDAO = daoFactory.getUserDetailsDAO(dbConnection.getConnection());
            if (userDetailsDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete user_details by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }
}
