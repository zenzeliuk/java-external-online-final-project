package com.epam.rd.java.basic.dao.impl;

public class QueryConstants {

    private QueryConstants() {
    }

    public static class CART {
        public static final String FIND_ALL = "SELECT * FROM shop.cart";
        public static final String CREATE = "INSERT INTO shop.cart (status_id) VALUES (?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.cart.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.cart " +
                "SET shop.cart.user_id = ?, " +
                "    shop.cart.status_id   = ? " +
                "WHERE shop.cart.id = ?";
        public static final String DELETE_BY_ID = "DELETE FROM shop.cart WHERE shop.cart.id = ?";
        public static final String GET_CART_BY_USER_ID_AND_STATUS_NAME = "" +
                "SELECT c.id          AS id, " +
                "       c.user_id     AS user_id, " +
                "       c.status_id   AS status_id, " +
                "       c.create_time AS create_time, " +
                "       c.update_time AS update_time " +
                "FROM shop.cart AS c " +
                "         JOIN shop.status AS s ON c.status_id = s.id " +
                "WHERE c.user_id = ? " +
                "  AND s.name = ?";
    }

    public static class CART_ITEM {
        public static final String FIND_ALL = "SELECT * FROM shop.cart_item";
        public static final String CREATE = "" +
                "INSERT INTO shop.cart_item (card_id, item_id, price, count) " +
                "VALUES (?, ?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.cart_item.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.cart_item " +
                "SET shop.cart_item.card_id = ?, " +
                "    shop.cart_item.item_id = ?, " +
                "    shop.cart_item.price   = ?, " +
                "    shop.cart_item.count   = ? " +
                "WHERE shop.cart_item.id = ? ";
        public static final String DELETE_BY_ID = "DELETE FROM shop.cart_item WHERE shop.cart_item.id = ?";
        public static final String FIND_ALL_BY_CART_ID = FIND_ALL + " WHERE shop.cart_item.card_id = ?";
    }

    public static class CATEGORY {
        public static final String FIND_ALL = "SELECT * FROM shop.category";
        public static final String CREATE = "INSERT INTO shop.category (name) VALUES (?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.category.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.category SET shop.category.name = ? " +
                "WHERE shop.category.id = ?";
        public static final String DELETE_BY_ID = "DELETE FROM shop.category WHERE shop.category.id = ?";
    }

    public static class ITEM {
        public static final String FIND_ALL = "SELECT * FROM shop.item";
        public static final String CREATE = "" +
                "INSERT INTO shop.item (category_id, brand_id, color_id, count, name, image, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.item.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.item " +
                "SET shop.item.category_id = ?, " +
                "    shop.item.brand_id    = ?, " +
                "    shop.item.color_id    = ?, " +
                "    shop.item.count       = ?, " +
                "    shop.item.name        = ?, " +
                "    shop.item.image       = ?, " +
                "    shop.item.price       = ? " +
                "WHERE shop.item.id = ?";
        public static final String DELETE_BY_ID = "DELETE FROM shop.item WHERE shop.item.id = ?";
    }

    public static class ROLE {
        public static final String FIND_ALL = "SELECT * FROM shop.role";
        public static final String CREATE = "INSERT INTO shop.role (name) VALUES (?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.role.id = ?";
        public static final String UPDATE = "UPDATE shop.role SET shop.role.name = ? WHERE shop.role.id = ?";
        public static final String DELETE_BY_ID = "DELETE FROM shop.role WHERE shop.role.id = ?";
        public static final String FIND_BY_NAME = FIND_ALL + " WHERE shop.role.name = ?";
        public static final String FIND_BY_ID = FIND_ALL + " WHERE shop.role.id = ?";
    }

    public static class STATUS {
        public static final String FIND_ALL = "SELECT * FROM shop.status";
        public static final String CREATE = "INSERT INTO shop.status (name) VALUES (?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.status.id = ?";
        public static final String UPDATE = "UPDATE shop.status SET shop.status.name = ? WHERE shop.status.id = ?";
        public static final String DELETE_BY_ID = "DELETE FROM shop.status WHERE shop.status.id = ?";
        public static final String GET_BY_NAME = FIND_ALL + " WHERE shop.status.name = ?";
    }

    public static class USER {
        public static final String FIND_ALL = "SELECT * FROM shop.user";
        public static final String CREATE = "INSERT INTO shop.user (login, password, role_id) VALUES (?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.user.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.user " +
                "SET shop.user.login    = ?, " +
                "    shop.user.password = ?, " +
                "    shop.user.role_id  = ? " +
                "WHERE id = ?";
        public static final String DELETE_BY_ID = "DELETE FROM shop.user WHERE shop.user.id = ?";
        public static final String FIND_BY_LOGIN = FIND_ALL + " WHERE shop.user.login = ?";
        public static final String FIND_BY_LOGIN_AND_PASSWORD = FIND_ALL + "" +
                " WHERE shop.user.login = ?" +
                " AND shop.user.password = ?";
    }


    public static class USER_DETAILS {
        public static final String FIND_ALL = "SELECT * FROM shop.user_details";
        public static final String CREATE = "" +
                "INSERT INTO shop.user_details (id, firs_name, last_name, email, phone, age) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE shop.user_details.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.user_details AS ud " +
                "SET ud.id        = ?, " +
                "    ud.firs_name = ?, " +
                "    ud.last_name = ?, " +
                "    ud.email     = ?, " +
                "    ud.phone     = ?, " +
                "    ud.age       = ? " +
                "WHERE ud.id = ?";
        public static final String DELETE_BY_ID = "DELETE FROM shop.user_details WHERE shop.user_details.id = ?";
    }
}
