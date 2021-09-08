package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.RoleDAO;
import com.epam.rd.java.basic.dao.UserDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {

    private static final DBConnection dbConnection = new ConnectionImpl();
    private static UserDAO userDAO;
    private static RoleDAO roleDAO;
    private static String sqlClearDB;
    private static String sqlCreateDB;
    private static String sqlDropDB;
    private static String pathSqlCreateDB = "sql/create_db.sql";
    private static String pathSqlClearDB = "sql/clear_db.sql";
    private static String pathSqlDropDB = "sql/drop_db.sql";

    private Role userRole;
    private Role adminRole;
    private User userMock;

    @BeforeAll
    static void init() throws IOException, SQLException {
        ClassLoader classLoader = UserDAOImplTest.class.getClassLoader();

        File fileCreateDB = new File(classLoader.getResource(pathSqlCreateDB).getFile());
        BufferedReader readerCreateDB = new BufferedReader(new FileReader(fileCreateDB));
        StringBuilder builderCreateDB = new StringBuilder();
        readFromBuffer(readerCreateDB, builderCreateDB);
        sqlCreateDB = builderCreateDB.toString();

        File fileClearDB = new File(classLoader.getResource(pathSqlClearDB).getFile());
        BufferedReader readerClearDB = new BufferedReader(new FileReader(fileClearDB));
        StringBuilder builderClearDB = new StringBuilder();
        readFromBuffer(readerClearDB, builderClearDB);
        sqlClearDB = builderClearDB.toString();

        File fileDropDB = new File(classLoader.getResource(pathSqlDropDB).getFile());
        BufferedReader readerDropDB = new BufferedReader(new FileReader(fileDropDB));
        StringBuilder builderDropDB = new StringBuilder();
        readFromBuffer(readerDropDB, builderDropDB);
        sqlDropDB = builderDropDB.toString();

        userDAO = new UserDAOImpl(dbConnection.getConnection());
        roleDAO = new RoleDAOImpl(dbConnection.getConnection());

        //Script create new database
        dbConnection.getConnection().prepareStatement(sqlCreateDB).executeUpdate();
    }

    private static void readFromBuffer(BufferedReader reader, StringBuilder builder) throws IOException {
        for (String line; (line = reader.readLine()) != null; ) {
            builder.append(line);
        }
    }

    @BeforeEach
    void setUp() throws SQLException, DaoException {
        dbConnection.getConnection().prepareStatement(sqlClearDB).executeUpdate();
        userRole = Role.USER;
        adminRole = Role.ADMIN;
        int idUser = roleDAO.create(userRole);
        int idAdmin = roleDAO.create(adminRole);
        userRole.setId(idUser);
        adminRole.setId(idAdmin);
        userMock = User.builder()
                .login("login_test")
                .password("password_test")
//                .role(userRole)
                .build();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        dbConnection.getConnection().prepareStatement(sqlDropDB).executeUpdate();
        dbConnection.close();
    }

    @Test
    void findAll() throws DaoException {
        List<User> userList = userDAO.findAll();
        assertTrue(userList.isEmpty());

        userDAO.create(userMock);
        userList = userDAO.findAll();
        assertEquals(1, userList.size());

        userMock.setLogin("unique_login");

        userDAO.create(userMock);
        userList = userDAO.findAll();
        assertEquals(2, userList.size());
    }

    @Test
    void create() throws DaoException {
        int idUser = userDAO.create(userMock);
        userMock.setId(idUser);
        assertTrue(idUser > 0);
        userMock.setLogin("unique_login");
        int idOtherUser = userDAO.create(userMock);
        assertEquals(idUser + 1, idOtherUser);
    }

    @Test
    void get() throws DaoException {
        int idMockUser = userDAO.create(userMock);
        userMock.setId(idMockUser);
        User findUser = userDAO.get(idMockUser);
        assertEquals(userMock, findUser);
    }

    @Test
    void update() throws DaoException {
        int idUser = userDAO.create(userMock);
        User user = User.builder()
                .id(idUser)
                .login("update_login")
                .password("update_login")
//                .role(adminRole)
                .build();
        User notCorrectUser = User.builder()
                .id(0)
                .login("0")
                .password("0")
//                .role(adminRole)
                .build();

        User beforeUpdateUser = userDAO.get(idUser);
        boolean correctUpdate = userDAO.update(user);
        boolean notCorrectUpdate = userDAO.update(notCorrectUser);
        User afterUpdateUser = userDAO.get(idUser);

        assertTrue(correctUpdate);
        assertFalse(notCorrectUpdate);
        assertNotEquals(beforeUpdateUser, afterUpdateUser);
    }

    @Test
    void delete() throws DaoException {
        int idUser = userDAO.create(userMock);
        boolean correctDelete = userDAO.delete(idUser);
        boolean notCorrectDelete = userDAO.delete(idUser);
        boolean deleteZeroUser = userDAO.delete(0);
        assertTrue(correctDelete);
        assertFalse(notCorrectDelete);
        assertFalse(deleteZeroUser);
    }
}