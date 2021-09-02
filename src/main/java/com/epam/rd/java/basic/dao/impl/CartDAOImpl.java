package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartDAOImpl implements CartDAO {

    private final Connection connection;
    private final CloseResources close;

    public CartDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<Cart> findAll() throws DaoException {
        List<Cart> cartList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.SQL_FIND_ALL_CARTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart cart = getCartFromResultSet(resultSet);
                cartList.add(cart);
            }
        } catch (SQLException e) {
            String exception = "Cannot find all cart. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
        return cartList;
    }

    private Cart getCartFromResultSet(ResultSet resultSet) throws SQLException {
        User customer = User.builder()
                .id(resultSet.getInt("customer_id"))
                .login(resultSet.getString("customer_login"))
                .password(resultSet.getString("customer_password"))
                .role(Role.createRole(
                        resultSet.getString("customer_role_name"),
                        resultSet.getInt("customer_role_id")))
                .build();
        User userApproved = User.builder()
                .id(resultSet.getInt("user_approved_id"))
                .login(resultSet.getString("user_approved_login"))
                .password(resultSet.getString("user_approved_password"))
                .role(Role.createRole(
                        resultSet.getString("user_approved_role_name"),
                        resultSet.getInt("user_approved_role_id")))
                .build();
        return Cart.builder()
                .id(resultSet.getInt("cart_id"))
                .customer(customer)
                .userApproved(userApproved)
                .createTime(resultSet.getTimestamp("cart_create_time"))
                .updateTime(resultSet.getTimestamp("cart_update_time"))
                .build();
    }

    @Override
    public int create(Cart cart) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.CART.SQL_CREATE_CART, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementWithoutId(cart, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated cart id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create cart. " + cart.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(Cart cart, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, cart.getStatus().getId());
        preparedStatement.setInt(2, cart.getCustomer().getId());
        preparedStatement.setInt(3, cart.getUserApproved().getId());
        preparedStatement.setTimestamp(4, cart.getCreateTime());
        preparedStatement.setTimestamp(5, cart.getUpdateTime());
    }

    @Override
    public Cart get(int id) throws DaoException {
        Cart cart;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.SQL_GET_CART_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cart = getCartFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get cart by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
        return cart;
    }

    @Override
    public boolean update(Cart cart) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.SQL_UPDATE_CART);
            setPreparedStatementWithoutId(cart, preparedStatement);
            preparedStatement.setInt(6, cart.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update cart. " + cart.toString() + e.getMessage();
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
            preparedStatement = connection.prepareStatement(QueryConstants.CART.SQL_DELETE_CART_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete cart by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }
}
