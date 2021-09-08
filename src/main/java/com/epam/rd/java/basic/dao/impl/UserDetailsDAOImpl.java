package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.UserDetailsDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.UserDetails;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserDetailsDAOImpl implements UserDetailsDAO {

    private final Connection connection;
    private final CloseResources close;

    public UserDetailsDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<UserDetails> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<UserDetails> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.USER_DETAILS.FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDetails result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = "Cannot find all user_details. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private UserDetails getFromResultSet(ResultSet resultSet) throws SQLException {
        return UserDetails.builder()
                .userId(resultSet.getInt("id"))
                .firstName(resultSet.getString("firs_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .phone(resultSet.getLong("phone"))
                .age(resultSet.getInt("age"))
                .build();
    }

    @Override
    public int create(UserDetails userDetails) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.USER_DETAILS.CREATE, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementWithoutId(userDetails, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated user_details id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create user_details. " + userDetails.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(UserDetails userDetails, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, userDetails.getId());
        preparedStatement.setString(2, userDetails.getFirstName());
        preparedStatement.setString(3, userDetails.getLastName());
        preparedStatement.setString(4, userDetails.getEmail());
        preparedStatement.setLong(5, userDetails.getPhone());
        preparedStatement.setInt(6, userDetails.getAge());
    }

    @Override
    public UserDetails get(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.USER_DETAILS.GET_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get user_details by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean update(UserDetails userDetails) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.USER_DETAILS.UPDATE);
            setPreparedStatementWithoutId(userDetails, preparedStatement);
            preparedStatement.setInt(7, userDetails.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update user_details. " + userDetails.toString() + e.getMessage();
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
            preparedStatement = connection.prepareStatement(QueryConstants.USER_DETAILS.DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete user_details by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }
}
