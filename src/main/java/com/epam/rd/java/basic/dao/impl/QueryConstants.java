package com.epam.rd.java.basic.dao.impl;

public class QueryConstants {

    private QueryConstants() {
    }

    /*
    SELECT user.id       AS id,
           user.login    AS login,
           user.password AS password,
           role.id       AS role_id,
           role.name     AS name
    FROM shop.user AS user,
         shop.role AS role
    WHERE user.role_id = role.id;
     */
    public static class USER {
        public static final String FIND_ALL = "" +
                "SELECT user.id       AS id, " +
                "       user.login    AS login, " +
                "       user.password AS password, " +
                "       role.id       AS role_id, " +
                "       role.name     AS name " +
                "FROM shop.user AS user, " +
                "     shop.role AS role " +
                "WHERE user.role_id = role.id";
        public static final String CREATE = "INSERT INTO shop.user (login, password, role_id) VALUES (?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " AND user.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.user AS user " +
                "SET user.login    = ?, " +
                "    user.password = ?, " +
                "    user.role_id  = ? " +
                "WHERE id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM shop.user " +
                "WHERE id = ?";
    }

    public static class ITEM {
        public static final String FIND_ALL = "" +
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
        public static final String CREATE = "" +
                "INSERT INTO item (name, code, price, description, category_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        public static final String GET_BY_ID = "" +
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
        public static final String UPDATE = "" +
                "UPDATE item " +
                "SET item.name        = ?, " +
                "    item.code        = ?, " +
                "    item.price       = ?, " +
                "    item.description = ?, " +
                "    item.category_id = ? " +
                "WHERE item.id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM item " +
                "WHERE item.id = ?";
    }

    public static class CART {
        public static final String FIND_ALL = "" +
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
        public static final String CREATE = "" +
                "INSERT INTO cart (user_id, status_id, approved_cart_by) " +
                "VALUES (?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL +
                "  AND cart.id = ?";
        public static final String UPDATE = "" +
                "UPDATE cart " +
                "SET cart.status_id        = ?, " +
                "    cart.user_id          = ?, " +
                "    cart.approved_cart_by = ? " +
                "WHERE cart.id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM cart " +
                "WHERE cart.id = ?";
    }

    public static class STATUS {
        public static final String FIND_ALL = "" +
                "SELECT status.id AS status_id, " +
                "       status.name AS status_name " +
                "FROM shop.status AS status";
        public static final String CREATE = "" +
                "INSERT INTO shop.status (name) " +
                "VALUES (?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE status.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.status AS status " +
                "SET status.name = ? " +
                "WHERE status.id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM shop.status AS status " +
                "WHERE status.id = ?";
    }

    public static class CATEGORY {
        public static final String FIND_ALL = "" +
                "SELECT category.id          AS category_id, " +
                "       category.description AS category_description, " +
                "       category.parent_id   AS parent_id " +
                "FROM shop.category AS category ";
        public static final String CREATE = "" +
                "INSERT INTO shop.category (name, parent_id, description) " +
                "VALUES (?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE category.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.category AS category " +
                "SET category.name        = ?, " +
                "    category.description = ?, " +
                "    category.parent_id   = ? " +
                "WHERE category.id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM shop.category AS category " +
                "WHERE category.id = ?";
    }

    public static class ROLE {
        public static final String FIND_ALL = "" +
                "SELECT role.id   AS role_id, " +
                "       role.name AS role_name " +
                "FROM shop.role AS role";
        public static final String CREATE = "" +
                "INSERT INTO shop.role (name) " +
                "VALUES (?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE role.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.role AS role " +
                "SET role.name = ? " +
                "WHERE role.id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM shop.role AS role " +
                "WHERE role.id = ?";
    }

    public static class CART_ITEM {
        public static final String FIND_ALL = "" +
                "SELECT c.id          AS cart_item_id, " +
                "       c.card_id     AS cart_id, " +
                "       c.item_id     AS item_id, " +
                "       c.price       AS price, " +
                "       c.count       AS count_item, " +
                "       c.create_time AS create_time, " +
                "       c.update_time AS update_time " +
                "FROM shop.cart_item AS c";
        public static final String CREATE = "" +
                "INSERT INTO shop.cart_item (card_id, item_id, price, count) " +
                "VALUES (?, ?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE c.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.cart_item AS ci " +
                "SET ci.card_id = ?, " +
                "    ci.item_id = ?, " +
                "    ci.price   = ?, " +
                "    ci.count   = ? " +
                "WHERE ci.id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM cart_item AS ci " +
                "WHERE ci.id = ?";
    }


    public static class USER_DETAILS {
        public static final String FIND_ALL = "" +
                "SELECT ud.id        AS user_id, " +
                "       ud.firs_name AS firs_name, " +
                "       ud.last_name AS last_name, " +
                "       ud.email     AS email, " +
                "       ud.phone     AS phone, " +
                "       ud.age       AS age " +
                "FROM shop.user_details AS ud";
        public static final String CREATE = "" +
                "INSERT INTO shop.user_details (id, firs_name, last_name, email, phone, age) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        public static final String GET_BY_ID = FIND_ALL + " WHERE ud.id = ?";
        public static final String UPDATE = "" +
                "UPDATE shop.user_details AS ud " +
                "SET ud.id        = ?, " +
                "    ud.firs_name = ?, " +
                "    ud.last_name = ?, " +
                "    ud.email     = ?, " +
                "    ud.phone     = ?, " +
                "    ud.age       = ? " +
                "WHERE ud.id = ?";
        public static final String DELETE_BY_ID = "" +
                "DELETE " +
                "FROM shop.user_details AS ud " +
                "WHERE ud.id = ?";
    }
}
