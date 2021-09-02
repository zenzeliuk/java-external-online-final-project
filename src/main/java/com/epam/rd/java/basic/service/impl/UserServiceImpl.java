package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class UserServiceImpl implements UserService {

    private DAOFactory daoFactory;
    private UserDAO userDAO;

    public UserServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            userDAO = daoFactory.getUserDAO(dbConnection.getConnection());
            return userDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all users. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public User create(User user) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            userDAO = daoFactory.getUserDAO(dbConnection.getConnection());
            int id = userDAO.create(user);
            if (id > 1) {
                user.setId(id);
                dbConnection.commit();
                return user;
            } else {
                dbConnection.rollback();
                throw new ServiceException(String.format("Invalid id='%s' created user", id));
            }
        } catch (DaoException e) {
            String exception = "Cannot create user. " + user.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }


    @Override
    public User get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            userDAO = daoFactory.getUserDAO(dbConnection.getConnection());
            return userDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get user by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(User user) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            userDAO = daoFactory.getUserDAO(dbConnection.getConnection());
            if (userDAO.update(user)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update user. " + user.toString() + e.getMessage();
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
            userDAO = daoFactory.getUserDAO(dbConnection.getConnection());
            if (userDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete user by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }
}
