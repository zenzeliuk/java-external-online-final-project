package com.epam.rd.java.basic.dao.impl.mysql;

public class QueryConstants {

    private QueryConstants() {
        //private constructor. Class is util
    }

    public static class USER {
        public static final String SQL_FIND_ALL_USERS = "SELECT " +
                "user.id AS id, user.login AS login, user.password AS pass, role.id AS role_id, role.name AS name " +
                "FROM shop.user AS user " +
                "JOIN role ON user.role_id = role.id " +
                "ORDER BY id ASC";
        public static final String SQL_CREATE_USER = "";
    }

}
