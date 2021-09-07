package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.RoleDAO;
import com.epam.rd.java.basic.dao.connection.DBConnection;
import com.epam.rd.java.basic.dao.connection.impl.ConnectionImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Cart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDAOImplTest {

    private static final DBConnection dbConnection = new ConnectionImpl();
    private static CartDAO cartDAO;
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

        cartDAO = new CartDAOImpl(dbConnection.getConnection());

        //Script create new database
        dbConnection.getConnection().prepareStatement(sqlCreateDB).executeUpdate();
    }
    private static void readFromBuffer(BufferedReader reader, StringBuilder builder) throws IOException {
        for (String line; (line = reader.readLine()) != null; ) {
            builder.append(line);
        }
    }

    @Test
    void findAll() throws DaoException {

    }

    @Test
    void create() {

    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getUserCartWithEmptyStatus() {
    }
}