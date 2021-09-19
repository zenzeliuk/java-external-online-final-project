package com.epam.rd.java.basic.dao.impl;

import com.epam.rd.java.basic.dao.CartDAO;
import com.epam.rd.java.basic.dao.util.CloseResources;
import com.epam.rd.java.basic.dao.util.impl.CloseResourcesImpl;
import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.dto.CartDTO;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartDAOImpl implements CartDAO {

    private final Connection connection;
    private final CloseResources close;

    public CartDAOImpl(Connection connection) {
        this.connection = connection;
        close = new CloseResourcesImpl();
    }

    @Override
    public List<Cart> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Cart> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.CART.FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart result = getFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = "Cannot find all cart. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private Cart getFromResultSet(ResultSet resultSet) throws SQLException {
        return Cart.builder()
                .id(resultSet.getInt("id"))
                .userId(resultSet.getInt("user_id"))
                .statusId(resultSet.getInt("status_id"))
                .createTime(resultSet.getTimestamp("create_time"))
                .updateTime(resultSet.getTimestamp("update_time"))
                .build();
    }

    @Override
    public int create(Cart cart) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    (QueryConstants.CART.CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, cart.getStatusId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Cannot get generated cartId id. ");
            }
        } catch (SQLException e) {
            String exception = "Cannot create cart. " + cart.toString() + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    private void setPreparedStatementWithoutId(Cart cart, PreparedStatement preparedStatement) throws SQLException {
        if (cart.getUserId() == null) {
            preparedStatement.setNull(1, java.sql.Types.NULL);
        } else {
            preparedStatement.setInt(1, cart.getUserId());
        }
        preparedStatement.setInt(2, cart.getStatusId());
    }

    @Override
    public Cart get(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.GET_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get cart by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean update(Cart cart) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.UPDATE);
            setPreparedStatementWithoutId(cart, preparedStatement);
            preparedStatement.setInt(3, cart.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = "Cannot update cart. " + cart.toString() + e.getMessage();
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
            preparedStatement = connection.prepareStatement(QueryConstants.CART.DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            String exception = String.format("Cannot delete cart by id = '%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public Cart getCartByUserIdAndStatusName(int idUser, String nameStatus) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(QueryConstants.CART.GET_CART_BY_USER_ID_AND_STATUS_NAME);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, nameStatus);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFromResultSet(resultSet);
        } catch (SQLException e) {
            String exception = String.format("Cannot get cart by user_id='%s' and status='%s'. %s", idUser, nameStatus, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    /*
      List<Cart> resultList = new ArrayList<>();
                preparedStatement = connection.prepareStatement(QueryConstants.CART.FIND_ALL);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Cart result = getFromResultSet(resultSet);
                    resultList.add(result);
                }
                return resultList;
     */
    @Override
    public List<CartDTO> findAllByUserId(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<CartDTO> resultList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(QueryConstants.CART.FIND_ALL_BY_USER_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartDTO result = getCartDTOFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            String exception = String.format("Cannot get list cart by user_id='%s'. %s", id, e.getMessage());
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public Integer getCountRows(String user, String dateFrom, String dateTo, String status) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (dateFrom == null || dateFrom.equals("")) {
                dateFrom = "2000-00-00";
            }
            if (dateTo == null || dateTo.equals("")) {
                dateTo = "2100-00-00";
            }
            StringBuilder query = new StringBuilder();
            query.append(QueryConstants.CART.GET_COUNT_ROWS);

            if (user.equals("2")) {
                query.append("AND shop.cart.user_id IS NOT NULL ");
            }
            if (user.equals("3")) {
                query.append("AND shop.cart.user_id IS NULL ");
            }

            if (!status.equals("0")) {
                query.append("AND status_id = ? ");
            }

            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            if (!status.equals("0")) {
                preparedStatement.setString(3, status);
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
    public List<CartDTO> findWithPaginationFilterAndSorting(String user, String dateFrom, String dateTo, String status, String page, String sorting) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Integer start;
            if (Integer.parseInt(page) == 1) {
                start = 0;
            } else {
                start = (Integer.parseInt(page) - 1) * 10;
            }

            if (dateFrom == null || dateFrom.equals("")) {
                dateFrom = "2000-00-00";
            }
            if (dateTo == null || dateTo.equals("")) {
                dateTo = "2100-00-00";
            }

            List<CartDTO> resultList = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append(QueryConstants.CART.FIND_ALL_WITH_FILTER);

            if (user.equals("2")) {
                query.append("AND c.user_id IS NOT NULL ");
            }
            if (user.equals("3")) {
                query.append("AND c.user_id IS NULL ");
            }

            if (!status.equals("0")) {
                query.append("AND c.status_id = ? ");
            }

            switch (sorting) {
                case ("1"): {
                    query.append("ORDER BY c.create_time ");
                    break;
                }
                case ("2"): {
                    query.append("ORDER BY c.create_time DESC ");
                    break;
                }
                case ("3"): {
                    query.append("ORDER BY c.update_time ");
                    break;
                }
                case ("4"): {
                    query.append("ORDER BY c.update_time DESC ");
                    break;
                }
                default: {
                    query.append("ORDER BY c.create_time DESC ");
                }
            }
            query.append("LIMIT ?, ?");


            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            if (!status.equals("0")) {
                preparedStatement.setString(3, status);
                preparedStatement.setInt(4, start);
                preparedStatement.setInt(5, 10);
            } else {
                preparedStatement.setInt(3, start);
                preparedStatement.setInt(4, 10);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartDTO result = getCartDTOFromResultSet(resultSet);
                resultList.add(result);
            }
            return resultList;

        } catch (SQLException e) {
            String exception = "Cannot find cart with filter. " + e.getMessage();
            log.error(exception);
            throw new DaoException(exception);
        } finally {
            close.closeResultSet(resultSet);
            close.closePrepareStatement(preparedStatement);
        }

    }

    private CartDTO getCartDTOFromResultSet(ResultSet resultSet) throws SQLException {
        return CartDTO.builder()
                .id(String.valueOf(resultSet.getInt("id")))
                .statusName(resultSet.getString("status"))
                .userName(resultSet.getString("user_name"))
                .createTime(resultSet.getTimestamp("create_time"))
                .updateTime(resultSet.getTimestamp("update_time"))
                .build();
    }
}
