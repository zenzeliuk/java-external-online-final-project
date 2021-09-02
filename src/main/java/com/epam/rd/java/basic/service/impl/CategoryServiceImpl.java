package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.CategoryDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.service.CategoryService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final DAOFactory daoFactory;
    private CategoryDAO categoryDAO;

    public CategoryServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }
    
    @Override
    public List<Category> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            categoryDAO = daoFactory.getCategoryDAO(dbConnection.getConnection());
            return categoryDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all category. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Category create(Category category) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            categoryDAO = daoFactory.getCategoryDAO(dbConnection.getConnection());
            int id = categoryDAO.create(category);
            category.setId(id);
            dbConnection.commit();
            return category;
        } catch (DaoException e) {
            String exception = "Cannot create category. " + category.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public Category get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            categoryDAO = daoFactory.getCategoryDAO(dbConnection.getConnection());
            return categoryDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get category by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(Category category) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            categoryDAO = daoFactory.getCategoryDAO(dbConnection.getConnection());
            if (categoryDAO.update(category)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update category. " + category.toString() + e.getMessage();
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
            categoryDAO = daoFactory.getCategoryDAO(dbConnection.getConnection());
            if (categoryDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete category by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }
}
