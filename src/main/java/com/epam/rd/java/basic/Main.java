package com.epam.rd.java.basic;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.service.UserService;
import com.epam.rd.java.basic.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws ServiceException {
        UserService service = new UserServiceImpl();
        System.out.println(service.get(5));
    }
}
