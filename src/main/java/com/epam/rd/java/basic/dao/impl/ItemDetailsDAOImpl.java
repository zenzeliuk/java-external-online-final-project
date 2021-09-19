package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ItemDetailsDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.ItemDetails;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.List;

@Log4j2
public class ItemDetailsDAOImpl implements ItemDetailsDAO {

    private final Connection connection;
    private final CloseResources close;

    public ItemDetailsDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<ItemDetails> findAll() throws DaoException {
        return null;
    }

    @Override
    public int create(ItemDetails itemDetails) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.ITEM_DETAILS.CREATE);
            setPreparedStatementWithoutId(itemDetails, preparedStatement);
            preparedStatement.executeUpdate();
            return itemDetails.getId();
        } catch (SQLException e) {
            String exception = "Cannot create item details. " + itemDetails.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(ItemDetails itemDetails, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, itemDetails.getId());
        preparedStatement.setInt(2, itemDetails.getCategoryId());
        preparedStatement.setInt(3, itemDetails.getBrandId());
        preparedStatement.setInt(4, itemDetails.getColorId());

    }

    @Override
    public ItemDetails get(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean update(ItemDetails itemDetails) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }
}
