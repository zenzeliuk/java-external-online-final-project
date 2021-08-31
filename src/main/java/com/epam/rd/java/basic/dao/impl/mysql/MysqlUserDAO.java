package com.epam.rd.java.basic.dao.impl.mysql;

import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.exception.CannotConnectToDBException;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class MysqlUserDAO implements UserDAO {

    private Connection connection;

    public MysqlUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstants.USER.SQL_FIND_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = Role.createRole(
                        resultSet.getString("role_id"),
                        resultSet.getInt("role_id"));
                User user = User.builder()
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .role(role)
                        .build();
                userList.add(user);
            }
        } catch (SQLException e) {
            String exception = "Cannot find all user. ";
            log.error(exception + e.getMessage());
            throw new CannotConnectToDBException(exception);
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
        }

        return userList;
    }

    @Override
    public Optional<User> create(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> get(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(User entity) {
        return false;
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
