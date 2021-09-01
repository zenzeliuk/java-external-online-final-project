package com.epam.rd.java.basic.dao.impl.mysql;

import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.exception.CannotExecuteQueryToDBException;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserDAOImpl implements UserDAO {

    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws CannotExecuteQueryToDBException {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.USER.SQL_FIND_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            String exception = "Cannot find all user. ";
            log.error(exception + e.getMessage());
            throw new CannotExecuteQueryToDBException(exception);
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
        }
        return userList;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Role role = Role.createRole(
                resultSet.getString("role_id"),
                resultSet.getInt("role_id"));
        return User.builder()
                .id(resultSet.getInt("id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .role(role)
                .build();
    }

    @Override
    public int create(User user) throws CannotExecuteQueryToDBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.USER.SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new CannotExecuteQueryToDBException("Cannot get generated id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create user. " + user.toString();
            log.error(exception + e.getMessage());
            throw new CannotExecuteQueryToDBException(exception);
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public User get(int id) throws CannotExecuteQueryToDBException {
        User user;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.USER.SQL_GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get user by id = '%s'. ", id);
            log.error(exception + e.getMessage());
            throw new CannotExecuteQueryToDBException(exception);
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public boolean update(User user) throws CannotExecuteQueryToDBException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.USER.SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole().getId());
            preparedStatement.setInt(4, user.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update user. " + user.toString();
            log.error(exception + e.getMessage());
            throw new CannotExecuteQueryToDBException(exception);
        } finally {
            closePrepareStatement(preparedStatement);
        }
    }


    @Override
    public boolean delete(int id) throws CannotExecuteQueryToDBException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.USER.SQL_DELETE_USER_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete user by id = '%s'. ", id);
            log.error(exception + e.getMessage());
            throw new CannotExecuteQueryToDBException(exception);
        } finally {
            closePrepareStatement(preparedStatement);
        }
    }

    private void closePrepareStatement(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

}
