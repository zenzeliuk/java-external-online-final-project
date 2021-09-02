package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Item;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ItemDAOImpl implements ItemDAO {

    private final Connection connection;
    private final CloseResources close;

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
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
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
        return itemList;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.ITEM.SQL_CREATE_ITEM, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementWithoutId(item, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated item id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create item. " + item.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(Item item, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, item.getName());
        preparedStatement.setString(2, item.getCode());
        preparedStatement.setBigDecimal(3, item.getPrice());
        preparedStatement.setString(4, item.getDescription());
        preparedStatement.setInt(5, item.getCategory().getId());
    }

    @Override
    public Item get(int id) throws DaoException {
        Item item;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.SQL_GET_ITEM_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item = getItemFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get item by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
        return item;
    }

    @Override
    public boolean update(Item item) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.SQL_UPDATE_ITEM);
            setPreparedStatementWithoutId(item, preparedStatement);
            preparedStatement.setInt(6, item.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update item. " + item.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.SQL_DELETE_ITEM_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete item by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }
}
