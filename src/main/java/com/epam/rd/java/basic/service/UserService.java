package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;

public interface UserService extends AbstractService<User> {

    User findByLogin(String login) throws ServiceException;
}
