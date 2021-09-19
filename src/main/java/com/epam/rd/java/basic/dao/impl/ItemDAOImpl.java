package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.ItemDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Item;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Log4j2
public class ItemDAOImpl implements ItemDAO {

    private final Connection connection;
    private final CloseResources close;

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<Item> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Item> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = "Cannot find all user. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private Item getFromResultSet(ResultSet resultSet) throws SQLException {
        return Item.builder()
                .id(resultSet.getInt("id"))
                .count(resultSet.getInt("count"))
                .name(resultSet.getString("name"))
                .image(resultSet.getString("image"))
                .price(resultSet.getBigDecimal("price"))
                .createTime(resultSet.getTimestamp("create_time"))
                .updateTime(resultSet.getTimestamp("update_time"))
                .build();
    }

    @Override
    public int create(Item item) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.ITEM.CREATE, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementWithoutId(item, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated item id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create item. " + item.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(Item item, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, item.getCount());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getImage());
        preparedStatement.setBigDecimal(4, item.getPrice());
    }

    @Override
    public Item get(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.GET_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get item by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean update(Item item) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.UPDATE);
            setPreparedStatementWithoutId(item, preparedStatement);
            preparedStatement.setInt(5, item.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update item. " + item.toString() + e.getMessage();
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
            preparedStatement = connection.prepareStatement(QueryConstants.ITEM.DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete item by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public Integer getCountRows(Integer categoryId, Integer colorId, Integer brandId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append(QueryConstants.ITEM_DETAILS.GET_COUNT_ROWS);
            int count = 0;

            if (nonNull(categoryId) || nonNull(colorId) || nonNull(brandId)) {
                query.append("WHERE ");
            }

            if (nonNull(categoryId)) {
                query.append("i.category_id = ? ");
                count++;
            }
            if (nonNull(colorId)) {
                if (count != 0) {
                    query.append("AND ");
                }
                query.append("i.color_id = ? ");
                count++;
            }
            if (nonNull(brandId)) {
                if (count != 0) {
                    query.append("AND ");
                }
                query.append("i.brand_id = ? ");
                count++;
            }

            preparedStatement = connection.prepareStatement(query.toString());

            if (nonNull(categoryId)) {
                preparedStatement.setInt(1, categoryId);
            }
            if (nonNull(colorId)) {
                if (isNull(categoryId)) {
                    preparedStatement.setInt(1, colorId);
                }
                if (nonNull(categoryId)) {
                    preparedStatement.setInt(2, colorId);
                }
            }
            if (nonNull(brandId)) {
                preparedStatement.setInt(count, brandId);
            }

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            String exception = "Cannot get count rows. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public List<Item> findWithPaginationFilterAndSorting(Integer page, Integer categoryId, Integer colorId, Integer brandId, String sortingId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Integer start;
            if (page == 1) {
                start = 0;
            } else {
                start = (page - 1) * 10;
            }
            List<Item> resultList = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append(QueryConstants.ITEM.FIND_ALL_WITH_FILTER);
            int count = 0;

            if (nonNull(categoryId) || nonNull(colorId) || nonNull(brandId)) {
                query.append("WHERE ");
            }

            if (nonNull(categoryId)) {
                query.append("i.category_id = ? ");
                count++;
            }
            if (nonNull(colorId)) {
                if (count != 0) {
                    query.append("AND ");
                }
                query.append("i.color_id = ? ");
                count++;
            }
            if (nonNull(brandId)) {
                if (count != 0) {
                    query.append("AND ");
                }
                query.append("i.brand_id = ? ");
                count++;
            }
            query.append("LIMIT ?, ?");
            preparedStatement = connection.prepareStatement(query.toString());

            if (nonNull(categoryId)) {
                preparedStatement.setInt(1, categoryId);
            }
            if (nonNull(colorId)) {
                if (isNull(categoryId)) {
                    preparedStatement.setInt(1, colorId);
                }
                if (nonNull(categoryId)) {
                    preparedStatement.setInt(2, colorId);
                }
            }
            if (nonNull(brandId)) {
                preparedStatement.setInt(count, brandId);
            }

            if (count == 0){
                preparedStatement.setInt(1, start);
                preparedStatement.setInt(2, 10);
            }
            if (count == 1){
                preparedStatement.setInt(2, start);
                preparedStatement.setInt(3, 10);
            }
            if (count == 2){
                preparedStatement.setInt(3, start);
                preparedStatement.setInt(4, 10);
            }
            if (count == 3){
                preparedStatement.setInt(4, start);
                preparedStatement.setInt(5, 10);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;

        } catch (SQLException e) {
            String exception = "Cannot find item with filter. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }


    }
}
