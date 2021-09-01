package com.epam.rd.java.basic.dao.connection;

import java.sql.Connection;

public interface DBConnection extends AutoCloseable {

    void autoCommit(boolean autoCommit);
    void commit();
    void rollback();
    void close();
    Connection getConnection();

}
