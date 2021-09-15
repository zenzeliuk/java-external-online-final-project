package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.model.dto.UserDTO;

import java.util.List;

public interface UserService extends AbstractService<User> {

    User findByLogin(String login) throws ServiceException;
    User findByLoginAndPassword(String login, String password) throws ServiceException;

    List<UserDTO> findAllDTO() throws ServiceException;
}
