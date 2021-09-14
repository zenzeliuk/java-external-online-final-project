package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.CartItemDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.service.CartItemService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class CartItemServiceImpl implements CartItemService {

    private final DAOFactory daoFactory;
    private CartItemDAO cartItemDAO;

    public CartItemServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<CartItem> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartItemDAO = daoFactory.getCartItemDAO(dbConnection.getConnection());
            return cartItemDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all cart_item. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public CartItem create(CartItem cartItem) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            cartItemDAO = daoFactory.getCartItemDAO(dbConnection.getConnection());
            int id = cartItemDAO.create(cartItem);
            cartItem.setId(id);
            dbConnection.commit();
            return cartItem;
        } catch (DaoException e) {
            String exception = "Cannot create cart_item. " + cartItem.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public CartItem get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartItemDAO = daoFactory.getCartItemDAO(dbConnection.getConnection());
            return cartItemDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get cart_item by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(CartItem cartItem) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            cartItemDAO = daoFactory.getCartItemDAO(dbConnection.getConnection());
            if (cartItemDAO.update(cartItem)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update cart_item. " + cartItem.toString() + e.getMessage();
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
            cartItemDAO = daoFactory.getCartItemDAO(dbConnection.getConnection());
            if (cartItemDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete cart_item by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public List<CartItem> findAllByCartId(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartItemDAO = daoFactory.getCartItemDAO(dbConnection.getConnection());
            return cartItemDAO.findAllByCartId(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot find list cart_item by cart id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }
}
