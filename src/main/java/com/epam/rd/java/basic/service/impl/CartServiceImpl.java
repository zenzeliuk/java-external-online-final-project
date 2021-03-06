package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.StatusDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.dto.CartDTO;
import com.epam.rd.java.basic.service.CartService;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartServiceImpl implements CartService {

    private final DAOFactory daoFactory;
    private CartDAO cartDAO;
    private StatusDAO statusDAO;

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
            String exception = "Cannot create cartId. " + cart.toString() + e.getMessage();
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
            String exception = String.format("Cannot get cartId by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(Cart cart) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            if (cart.getUserId() == 0){
                cart.setUserId(null);
            }
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
            String exception = "Cannot update cartId. " + cart.toString() + e.getMessage();
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
            String exception = String.format("Cannot delete cartId by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public Cart getCartByUserIdAndStatusName(int idUser, String nameStatus) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            statusDAO = daoFactory.getStatusDAO(dbConnection.getConnection());
            return cartDAO.getCartByUserIdAndStatusName(idUser, nameStatus);
        } catch (DaoException e) {
            String exception = String.format("Cannot get cart by user_id='%s' and status='%s'. %s", idUser, nameStatus, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<CartDTO> findAllByUserId(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            return cartDAO.findAllByUserId(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get list cart by user_id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Integer> getPages(String user, String dateFrom, String dateTo, String status) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            List<Integer> pages = new ArrayList<>();
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            Integer countRows = cartDAO.getCountRows(user, dateFrom, dateTo, status);
            if (countRows != null) {
                int p = (countRows / 10) + 1;
                for (int i = 1; i <= p; i++) {
                    pages.add(i);
                }
            }
            return pages;
        } catch (DaoException e) {
            String exception = "Cannot get pages. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<CartDTO> findWithPaginationFilterAndSorting(String user, String dateFrom, String dateTo, String status, String page, String sorting) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            cartDAO = daoFactory.getCartDAO(dbConnection.getConnection());
            return cartDAO.findWithPaginationFilterAndSorting(
                    user, dateFrom, dateTo, status, page, sorting
            );
        } catch (DaoException e) {
            String exception = "Cannot find cart with filter. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }


    }
}
