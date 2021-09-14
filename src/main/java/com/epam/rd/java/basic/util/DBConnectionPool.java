package com.epam.rd.java.basic.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class DBConnectionPool {

    private static final HikariDataSource dataSource;

    private DBConnectionPool() {
    }

    static {
        HikariConfig config = new HikariConfig("/datasource.properties");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
