package com.epam.rd.java.basic.dao.impl;

public class QueryConstants {

    private QueryConstants() {
    }

    public static class USER {
        public static final String SQL_FIND_ALL_USERS = "" +
                "SELECT " +
                "       user.id       AS id, " +
                "       user.login    AS login, " +
                "       user.password AS password, " +
                "       role.id       AS role_id, " +
                "       role.name     AS name " +
                "FROM shop.user AS user " +
                "         JOIN role ON user.role_id = role.id " +
                "ORDER BY id ASC;";
        public static final String SQL_CREATE_USER = "" +
                "INSERT INTO user (login, password, role_id) " +
                "VALUES (?, ?, ?)";
        public static final String SQL_GET_USER_BY_ID = "" +
                "SELECT " +
                "       user.id       AS id, " +
                "       user.login    AS login, " +
                "       user.password AS password, " +
                "       role.id       AS role_id, " +
                "       role.name     AS name " +
                "FROM shop.user AS user " +
                "         JOIN role ON user.role_id = role.id " +
                "WHERE user.id = ?";
        public static final String SQL_UPDATE_USER = "" +
                "UPDATE user " +
                "SET user.login    = ?, " +
                "    user.password = ?, " +
                "    user.role_id  = ? " +
                "WHERE id = ?";
        public static final String SQL_DELETE_USER_BY_ID = "" +
                "DELETE " +
                "FROM user " +
                "WHERE id = ?";
    }

    public static class ITEM {
        public static final String SQL_FIND_ALL_ITEMS = "" +
                "SELECT i.id          AS item_id, " +
                "       i.name        AS item_name, " +
                "       i.code        AS item_code, " +
                "       i.price       AS item_price, " +
                "       i.description AS item_description, " +
                "       c.id          AS id, " +
                "       c.name        AS name, " +
                "       c.description AS description " +
                "FROM shop.item AS i " +
                "         JOIN category c ON i.category_id = c.id " +
                "ORDER BY item_id";
        public static final String SQL_CREATE_ITEM = "" +
                "INSERT INTO item (name, code, price, description, category_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        public static final String SQL_GET_ITEM_BY_ID = "" +
                "SELECT i.id          AS item_id, " +
                "       i.name        AS item_name, " +
                "       i.code        AS item_code, " +
                "       i.price       AS item_price, " +
                "       i.description AS item_description, " +
                "       c.id          AS id, " +
                "       c.name        AS name, " +
                "       c.description AS description " +
                "FROM shop.item AS i " +
                "         JOIN category c ON i.category_id = c.id " +
                "WHERE i.id = ?";
        public static final String SQL_UPDATE_ITEM = "" +
                "UPDATE item " +
                "SET item.name = ?, " +
                "    item.code = ?, " +
                "    item.price = ?, " +
                "    item.description = ?, " +
                "    item.category_id = ? " +
                "WHERE item.id = ?";
        public static final String SQL_DELETE_ITEM_BY_ID = "" +
                "DELETE " +
                "FROM item " +
                "WHERE item.id = ?";
    }

    public static class CART {
        public static final String SQL_FIND_ALL_CARTS = "" +
                "SELECT cart.id            AS cart_id, " +
                "       cart.create_time   AS cart_create_time, " +
                "       cart.update_time   AS cart_update_time, " +
                "       status.id          AS status_id, " +
                "       status.name        AS status_name, " +
                "       customer.id        AS customer_id, " +
                "       customer.login     AS customer_login, " +
                "       customer.password  AS customer_password, " +
                "       customer_role.id   AS customer_role_id, " +
                "       customer_role.name AS customer_role_name, " +
                "       approved.id        AS user_approved_id, " +
                "       approved.login     AS user_approved_login, " +
                "       approved.password  AS user_approved_password, " +
                "       approved_role.id   AS user_approved_role_id, " +
                "       approved_role.name AS user_approved_role_name " +
                "FROM shop.cart AS cart, " +
                "     shop.status AS status, " +
                "     shop.user AS customer, " +
                "     shop.user AS approved, " +
                "     shop.role AS customer_role, " +
                "     shop.role AS approved_role " +
                "WHERE cart.user_id = customer.id " +
                "  AND cart.status_id = status.id " +
                "  AND cart.approved_cart_by = approved.id " +
                "  AND customer.role_id = customer_role.id " +
                "  AND approved.role_id = approved_role.id";
        public static final String SQL_CREATE_CART = "" +
                "INSERT INTO cart (user_id, status_id, approved_cart_by, create_time, update_time) " +
                "VALUES (?, ?, ?, ?, ?)";
        public static final String SQL_GET_CART_BY_ID = SQL_FIND_ALL_CARTS +
                "  AND cart.id = ?";
        public static final String SQL_UPDATE_CART = "" +
                "UPDATE cart " +
                "SET cart.status_id = ?, " +
                "    cart.user_id = ?, " +
                "    cart.approved_cart_by = ?, " +
                "    cart.create_time = ?, " +
                "    cart.update_time = ? " +
                "WHERE cart.id = ?";
        public static final String SQL_DELETE_CART_BY_ID = "" +
                "DELETE " +
                "FROM cart " +
                "WHERE cart.id = ?";
    }

    public static class STATUS {
        public static final String SQL_FIND_ALL_STATUSES = "" +
                "SELECT status.id AS status_id, " +
                "       status.name AS status_name " +
                "FROM shop.status AS status";
        public static final String SQL_CREATE_STATUS = "" +
                "INSERT INTO shop.status (name) " +
                "VALUES (?)";
        public static final String SQL_GET_STATUS_BY_ID = SQL_FIND_ALL_STATUSES + " WHERE status.id = ?";
        public static final String SQL_UPDATE_STATUS = "" +
                "UPDATE shop.status AS status " +
                "SET status.name = ? " +
                "WHERE status.id = ?";
        public static final String SQL_DELETE_STATUS_BY_ID = "" +
                "DELETE " +
                "FROM shop.status AS status " +
                "WHERE status.id = ?";
    }


    //    public static class ITEM {
//        public static final String SQL_FIND_ALL_ITEMS = "";
//        public static final String SQL_CREATE_ITEM = "";
//        public static final String SQL_GET_ITEM_BY_ID = "";
//        public static final String SQL_UPDATE_ITEM = "";
//        public static final String SQL_DELETE_ITEM_BY_ID = "";
//    }
}
