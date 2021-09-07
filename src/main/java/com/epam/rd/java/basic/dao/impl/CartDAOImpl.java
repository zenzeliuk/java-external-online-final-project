package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.Status;
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Cart> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.CART.FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = "Cannot find all cartId. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private Cart getFromResultSet(ResultSet resultSet) throws SQLException {
        Status status = Status.createStatus(
                resultSet.getString("status_name"),
                resultSet.getInt("status_id"));
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
                .status(status)
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
                    (QueryConstants.CART.CREATE, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementWithoutId(cart, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated cartId id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create cartId. " + cart.toString() + e.getMessage();
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
    }

    @Override
    public Cart get(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.GET_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get cartId by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean update(Cart cart) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.UPDATE);
            setPreparedStatementWithoutId(cart, preparedStatement);
            preparedStatement.setInt(4, cart.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update cartId. " + cart.toString() + e.getMessage();
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
            preparedStatement = connection.prepareStatement(QueryConstants.CART.DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete cartId by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public Cart getUserCartWithEmptyStatus(int idUser, int idEmptyStatus) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.GET_ID_CART_BY_USER_ID);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idEmptyStatus);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Cart cart = get(resultSet.getInt("id"));
            log.error(cart);
            return cart;
        } catch (SQLException e) {
            String exception = String.format("Cannot get empty cartId by user_id='%s'. %s", idUser, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }
}
