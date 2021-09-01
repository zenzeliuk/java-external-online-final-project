package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Category;
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

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("category"))
                .description(resultSet.getString("description"))
                .build();
        return Item
                .builder()
                .id(resultSet.getInt("item_id"))
                .name(resultSet.getString("item_name"))
                .code(resultSet.getString("item_code"))
                .price(resultSet.getBigDecimal("item_price"))
                .description(resultSet.getString("item_description"))
                .category(category)
                .build();
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
