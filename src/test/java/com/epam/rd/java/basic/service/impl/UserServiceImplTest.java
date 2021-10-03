package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.dao.factory.DAOFactory;
import com.epam.rd.java.basic.dao.factory.impl.DAOFactoryImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    DAOFactory daoFactory;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByLoginAndPassword() throws DaoException, ServiceException {
//        User user = User.builder()
//                .login("123")
//                .password("123")
//                .build();
//        UserDAO userDAO = mock(UserDAO.class);
//        Connection connection = mock(Connection.class);
//
//        when(daoFactory.getConnection()).thenReturn(connection);
//        when(daoFactory.getUserDAO(connection)).thenReturn(userDAO);
//
//        when(userDAO.findByLoginAndPassword(user.getLogin(), user.getPassword())).thenReturn(user);
//        User res = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
//        assertEquals(user, res);
    }
}