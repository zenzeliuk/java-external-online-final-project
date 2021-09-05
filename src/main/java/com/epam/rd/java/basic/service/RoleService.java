package com.epam.rd.java.basic.service;

import com.epam.rd.java.basic.exception.ServiceException;
import com.epam.rd.java.basic.model.Role;

public interface RoleService extends AbstractService<Role> {
    Role findByName(String name) throws ServiceException;
}
