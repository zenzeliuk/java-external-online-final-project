package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Item> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = "Cannot find all user. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private Item getFromResultSet(ResultSet resultSet) throws SQLException {
        return Item.builder()
                .id(resultSet.getInt("id"))
                .categoryId(resultSet.getInt("category_id"))
                .brandId(resultSet.getInt("brand_id"))
                .colorId(resultSet.getInt("color_id"))
                .count(resultSet.getInt("count"))
                .name(resultSet.getString("name"))
                .image(resultSet.getString("image"))
                .price(resultSet.getBigDecimal("price"))
                .createTime(resultSet.getTimestamp("create_time"))
                .updateTime(resultSet.getTimestamp("update_time"))
                .build();
    }


    @Override
    public int create(Item item) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.ITEM.CREATE, Statement.RETURN_GENERATED_KEYS);
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
        preparedStatement.setInt(1, item.getCategoryId());
        preparedStatement.setInt(2, item.getBrandId());
        preparedStatement.setInt(3, item.getColorId());
        preparedStatement.setInt(4, item.getCount());
        preparedStatement.setString(5, item.getName());
        preparedStatement.setString(6, item.getImage());
        preparedStatement.setBigDecimal(7, item.getPrice());
    }

    @Override
    public Item get(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.GET_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get item by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean update(Item item) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.UPDATE);
            setPreparedStatementWithoutId(item, preparedStatement);
            preparedStatement.setInt(8, item.getId());
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
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.DELETE_BY_ID);
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
