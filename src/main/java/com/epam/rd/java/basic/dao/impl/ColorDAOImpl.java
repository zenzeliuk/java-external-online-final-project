package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ColorDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Category;
import com.epam.rd.java.basic.model.Color;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ColorDAOImpl implements ColorDAO {

    private final Connection connection;
    private final CloseResources close;

    public ColorDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<Color> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Color> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.COLOR.FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = "Cannot find all color. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private Color getFromResultSet(ResultSet resultSet) throws SQLException {
        Color color = new Color();
        color.setId(resultSet.getInt("id"));
        color.setName(resultSet.getString("name"));
        return color;
    }

    @Override
    public int create(Color color) throws DaoException {
        return 0;
    }

    @Override
    public Color get(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean update(Color color) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }
}
