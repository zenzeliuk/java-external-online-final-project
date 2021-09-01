package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.dao.DBConnection;

public interface ServiceFactory {

    UserService getUserService(DBConnection connection);

}
