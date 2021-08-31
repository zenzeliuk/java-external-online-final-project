package com.epam.rd.java.basic.dao.connection;

import com.epam.rd.java.basic.util.DBConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class SQLConnection implements DBConnection {

    private Connection connection;

    public SQLConnection() {
        try {
            connection = DBConnectionPool.getConnection();
        } catch (SQLException e) {
            log.error("Cannot get connection from connection pool. " + e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void autoCommit(boolean autoCommit) {
        if (isNotNull()) {
            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                log.error(String.format("Cannot change autocommit '%s'. %s", autoCommit, e.getMessage()));
            }
        }
    }

    @Override
    public void commit() {
        if (isNotNull()) {
            try {
                connection.commit();
            } catch (SQLException e) {
                log.error("Cannot commit connection. " + e.getMessage());
            }
        }
    }

    @Override
    public void rollback() {
        if (isNotNull()) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.error("Cannot rollback connection. " + e.getMessage());
            }
        }
    }

    @Override
    public void close() {
        if (isNotNull()) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Cannot close connection. " + e.getMessage());
            }
        }
    }

    private boolean isNotNull() {
        return this.connection != null;
    }

}
