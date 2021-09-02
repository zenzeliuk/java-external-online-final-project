package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.RoleDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.service.RoleService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class RoleServiceImpl implements RoleService {
    
    private final DAOFactory daoFactory;
    private RoleDAO roleDAO;

    public RoleServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }
    
    @Override
    public List<Role> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            roleDAO = daoFactory.getRoleDAO(dbConnection.getConnection());
            return roleDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all role. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Role create(Role role) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            roleDAO = daoFactory.getRoleDAO(dbConnection.getConnection());
            int id = roleDAO.create(role);
            role.setId(id);
            dbConnection.commit();
            return role;
        } catch (DaoException e) {
            String exception = "Cannot create role. " + role.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public Role get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            roleDAO = daoFactory.getRoleDAO(dbConnection.getConnection());
            return roleDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get role by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(Role role) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            roleDAO = daoFactory.getRoleDAO(dbConnection.getConnection());
            if (roleDAO.update(role)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update role. " + role.toString() + e.getMessage();
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
            roleDAO = daoFactory.getRoleDAO(dbConnection.getConnection());
            if (roleDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete role by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }
}
