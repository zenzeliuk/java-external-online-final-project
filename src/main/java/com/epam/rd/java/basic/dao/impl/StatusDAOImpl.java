package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.StatusDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Status;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class StatusDAOImpl implements StatusDAO {

    private final Connection connection;
    private final CloseResources close;

    public StatusDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<Status> findAll() throws DaoException {
        List<Status> statusList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.STATUS.SQL_FIND_ALL_STATUSES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Status status = getStatusFromResultSet(resultSet);
                statusList.add(status);
            }
        } catch (SQLException e) {
            String exception = "Cannot find all status. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
        return statusList;
    }

    private Status getStatusFromResultSet(ResultSet resultSet) throws SQLException {
        return Status.createStatus(
                resultSet.getString("status_name"),
                resultSet.getInt("status_id"));
    }

    @Override
    public int create(Status status) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.STATUS.SQL_CREATE_STATUS, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementWithoutId(status, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated status id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create status. " + status.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(Status status, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, status.getName());
    }

    @Override
    public Status get(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.STATUS.SQL_GET_STATUS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getStatusFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get user by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean update(Status status) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.STATUS.SQL_UPDATE_STATUS);
            setPreparedStatementWithoutId(status, preparedStatement);
            preparedStatement.setInt(2, status.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update status. " + status.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.STATUS.SQL_DELETE_STATUS_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete status by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }
}
