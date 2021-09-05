package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.RoleDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Role;
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

class RoleDAOImplTest {

    private static final DBConnection dbConnection = new ConnectionImpl();
    private static RoleDAO roleDAO;
    private static String sqlClearDB;
    private static String sqlCreateDB;
    private static String sqlDropDB;
    private static String pathSqlCreateDB = "sql/create_db.sql";
    private static String pathSqlClearDB = "sql/clear_db.sql";
    private static String pathSqlDropDB = "sql/drop_db.sql";


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
    void setUp() throws SQLException {
        dbConnection.getConnection().prepareStatement(sqlClearDB).executeUpdate();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        dbConnection.getConnection().prepareStatement(sqlDropDB).executeUpdate();
        dbConnection.close();
    }


    @Test
    void findAll() throws DaoException {
        List<Role> roleList = roleDAO.findAll();
        assertTrue(roleList.isEmpty());

        roleDAO.create(Role.ADMIN);
        roleList = roleDAO.findAll();
        assertEquals(1, roleList.size());

        roleDAO.create(Role.USER);
        roleList = roleDAO.findAll();
        assertEquals(2, roleList.size());
    }

    @Test
    void create() throws DaoException {
        int idAdmin = roleDAO.create(Role.ADMIN);
        assertTrue(idAdmin > 0);
        int idUser = roleDAO.create(Role.USER);
        assertEquals(idAdmin + 1, idUser);
    }

    @Test
    void get() throws DaoException {
        int idAdmin = roleDAO.create(Role.ADMIN);
        Role role = roleDAO.get(idAdmin);
        assertEquals(Role.ADMIN, role);
    }

    @Test
    void update() throws DaoException {
        int idAdmin = roleDAO.create(Role.ADMIN);
        Role role = Role.createRole("ssss", idAdmin);
        boolean correctUpdate = roleDAO.update(role);
        role.setId(0);
        boolean notCorrectUpdate = roleDAO.update(role);
        assertTrue(correctUpdate);
        assertFalse(notCorrectUpdate);

    }

    @Test
    void delete() throws DaoException {
        int idAdmin = roleDAO.create(Role.ADMIN);
        boolean correctDelete = roleDAO.delete(idAdmin);
        boolean notCorrectDelete = roleDAO.delete(idAdmin);
        boolean deleteZeroUser = roleDAO.delete(0);
    }
}