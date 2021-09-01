package com.epam.rd.java.basic.dao.impl.mysql;

public class QueryConstants {

    private QueryConstants() {
        //private constructor. Class is util
    }

    public static class USER {
        public static final String SQL_FIND_ALL_USERS = "" +
                "SELECT " +
                "       user.id       AS id, " +
                "       user.login    AS login, " +
                "       user.password AS pass, " +
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
                "       user.password AS pass, " +
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

}
