package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.dao.ItemDetailsDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.*;
import com.epam.rd.java.basic.model.mapper.UserMapper;
import com.epam.rd.java.basic.service.ItemService;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ItemServiceImpl implements ItemService {

    private final DAOFactory daoFactory;
    private ItemDAO itemDAO;
    private ItemDetailsDAO itemDetailsDAO;

    public ItemServiceImpl() {
        daoFactory = new DAOFactoryImpl();
    }

    @Override
    public List<Item> findAll() throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            return itemDAO.findAll();
        } catch (DaoException e) {
            String exception = "Cannot find all items. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Item create(Item item) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            int id = itemDAO.create(item);
            item.setId(id);
            dbConnection.commit();
            return item;
        } catch (DaoException e) {
            String exception = "Cannot create item. " + item.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public Item get(int id) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            return itemDAO.get(id);
        } catch (DaoException e) {
            String exception = String.format("Cannot get item by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean update(Item item) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            if (itemDAO.update(item)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = "Cannot update item. " + item.toString() + e.getMessage();
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
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            if (itemDAO.delete(id)) {
                dbConnection.commit();
                return true;
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (DaoException e) {
            String exception = String.format("Cannot delete item by id='%s'. %s", id, e.getMessage());
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }


    @Override
    public List<Integer> getPages(String categoryId, String colorId, String brandId, String priceFrom, String priceTo) throws ServiceException {
        try (DBConnection dbConnection = new ConnectionImpl()) {
            List<Integer> pages = new ArrayList<>();
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            Integer countRows = itemDAO.getCountRows(categoryId, colorId, brandId, priceFrom, priceTo);
            if (countRows != null) {
                int p = (countRows / 10) + 1;
                for (int i = 1; i <= p; i++) {
                    pages.add(i);
                }
            }
            return pages;
        } catch (DaoException e) {
            String exception = "Cannot get count rows. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Item> findWithPaginationFilterAndSorting
            (String categoryId, String colorId, String brandId, String priceFrom, String priceTo, String page, String sorting)
            throws ServiceException {

        try (DBConnection dbConnection = new ConnectionImpl()) {
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            return itemDAO.findWithPaginationFilterAndSorting(
                    categoryId, colorId, brandId, priceFrom, priceTo, page, sorting
            );
        } catch (DaoException e) {
            String exception = "Cannot find item with filter. " + e.getMessage();
            log.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public void createItemWithDetails(Item item, ItemDetails itemDetails) throws ServiceException {
        DBConnection dbConnection = new ConnectionImpl();
        try {
            dbConnection.autoCommit(false);
            itemDAO = daoFactory.getItemDAO(dbConnection.getConnection());
            itemDetailsDAO = daoFactory.getItemDetailsDAO(dbConnection.getConnection());
            int itemId = itemDAO.create(item);
            itemDetails.setId(itemId);
            itemDetailsDAO.create(itemDetails);
            dbConnection.commit();
        } catch (DaoException e) {
            String exception = "Cannot create item with details. " + item.toString() + e.getMessage();
            log.error(exception);
            dbConnection.rollback();
            throw new ServiceException(exception);
        } finally {
            dbConnection.close();
        }
    }
}
