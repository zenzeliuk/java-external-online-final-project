package com.epam.rd.java.basic.dao.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface CloseResources {

    void closeResultSet(ResultSet resultSet);
    void closePrepareStatement(PreparedStatement preparedStatement);

}
