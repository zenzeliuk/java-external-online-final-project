package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Status;

public interface StatusService extends AbstractService<Status>{
    Status getByName(String name) throws ServiceException;
}
