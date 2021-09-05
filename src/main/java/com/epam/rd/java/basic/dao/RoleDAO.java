package com.epam.rd.java.basic.dao;

import com.epam.rd.java.basic.exception.DaoException;
import com.epam.rd.java.basic.model.Role;

public interface RoleDAO extends AbstractDAO<Role>{

    Role findByName(String name) throws DaoException;
}
