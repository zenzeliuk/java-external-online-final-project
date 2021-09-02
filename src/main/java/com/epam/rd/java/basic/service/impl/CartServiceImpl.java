package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.service.CartService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class CartServiceImpl implements CartService {


    private final DAOFactory daoFactory;
    private CartDAO cartDAO;

    public CartServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<Cart> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            return cartDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all carts. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Cart create(Cart cart) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            int id = cartDAO.create(cart);
            cart.setId(id);
            dbConnection.commit();
            return cart;
        } catch (DaoException e) {
            String exception = "Cannot create cart. " + cart.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public Cart get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            return cartDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get cart by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(Cart cart) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            if (cartDAO.update(cart)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update cart. " + cart.toString() + e.getMessage();
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
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            if (cartDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete cart by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }
}
