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
        public static final String SQL_FIND_ALL_CARTS = "";
        public static final String SQL_CREATE_CART = "";
        public static final String SQL_GET_CART_BY_ID = "";
        public static final String SQL_UPDATE_CART = "";
        public static final String SQL_DELETE_CART_BY_ID = "";
    }

    //    public static class ITEM {
//        public static final String SQL_FIND_ALL_ITEMS = "";
//        public static final String SQL_CREATE_ITEM = "";
//        public static final String SQL_GET_ITEM_BY_ID = "";
//        public static final String SQL_UPDATE_ITEM = "";
//        public static final String SQL_DELETE_ITEM_BY_ID = "";
//    }
}
