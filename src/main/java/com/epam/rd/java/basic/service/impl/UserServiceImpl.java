package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
