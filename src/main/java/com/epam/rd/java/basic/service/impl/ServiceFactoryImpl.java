package com.epam.rd.java.basic.service.impl;

import com.epam.rd.java.basic.service.ServiceFactory;
import com.epam.rd.java.basic.service.UserService;

public class ServiceFactoryImpl implements ServiceFactory {

    @Override
    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
