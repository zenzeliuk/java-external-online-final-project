package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    DAOFactory daoFactory;

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private DBConnection dbConnection;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByLoginAndPassword() throws DaoException, ServiceException {
        User user = User.builder()
                .login("123")
                .password("123")
                .build();
        UserDAO userDAO = mock(UserDAO.class);

        when(daoFactory.getUserDAO(dbConnection.getConnection())).thenReturn(userDAO);
        when(userDAO.findByLoginAndPassword(user.getLogin(), user.getPassword())).thenReturn(user);

        User res = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
//        assertEquals(user, res);
    }
}