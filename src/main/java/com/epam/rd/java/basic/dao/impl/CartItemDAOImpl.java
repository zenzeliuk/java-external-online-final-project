package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.CartItemDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.CartItem;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartItemDAOImpl implements CartItemDAO {

    private final Connection connection;
    private final CloseResources close;

    public CartItemDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<CartItem> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<CartItem> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.CART_ITEM.FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartItem result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = "Cannot find all cart_item. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private CartItem getFromResultSet(ResultSet resultSet) throws SQLException {
        return CartItem.builder()
                .id(resultSet.getInt("cart_item_id"))
                .cartId(resultSet.getInt("cart_id"))
                .itemId(resultSet.getInt("item_id"))
                .price(resultSet.getBigDecimal("price"))
                .countItem(resultSet.getInt("count_item"))
                .createTime(resultSet.getTimestamp("create_time"))
                .updateTime(resultSet.getTimestamp("update_time"))
                .build();
    }

    @Override
    public int create(CartItem cartItem) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.CART_ITEM.CREATE, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementWithoutId(cartItem, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated cart_item id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create cart_item. " + cartItem.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(CartItem cartItem, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, cartItem.getCartId());
        preparedStatement.setInt(2, cartItem.getItemId());
        preparedStatement.setBigDecimal(3, cartItem.getPrice());
        preparedStatement.setInt(4, cartItem.getCountItem());
    }

    @Override
    public CartItem get(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART_ITEM.GET_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get cart_item by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean update(CartItem cartItem) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART_ITEM.UPDATE);
            setPreparedStatementWithoutId(cartItem, preparedStatement);
            preparedStatement.setInt(5, cartItem.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update cart_item. " + cartItem.toString() + e.getMessage();
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
            preparedStatement = connection.prepareStatement(QueryConstants.CART_ITEM.DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete cart_item by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }
}
