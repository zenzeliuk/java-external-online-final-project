package com.epam.rd.java.basic.dao.util.impl;

import com.epam.rd.java.basic.dao.util.CloseResources;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class CloseResourcesImpl implements CloseResources {

    @Override
    public void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void closePrepareStatement(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
