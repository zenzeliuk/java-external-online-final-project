package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Item;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ItemDAOImpl implements ItemDAO {

    private final Connection connection;

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Item> findAll() throws DaoException {
        List<Item> itemList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.SQL_FIND_ALL_ITEMS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                itemList.add(item);
            }
        } catch (SQLException e) {
            String exception = "Cannot find all user. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
//            closeResultSet(resultSet);
//            closePrepareStatement(preparedStatement);
        }
        return itemList;
    }

    private Item getItemFromResultSet(ResultSet resultSet) {
        Item item = Item.builder()
                .build();
        return null;
    }

    @Override
    public int create(Item item) throws DaoException {
        return 0;
    }

    @Override
    public Item get(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean update(Item entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }
}
