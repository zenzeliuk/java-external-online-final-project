package com.epam.rd.java.basic.dao.impl.mysql;

import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class MysqlUserDAO implements UserDAO {

    Connection connection;

    public MysqlUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        return null;
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
}
