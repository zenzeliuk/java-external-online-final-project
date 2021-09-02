package com.epam.rd.java.basic.service.factory.impl;

import com.epam.rd.java.basic.service.*;
import com.epam.rd.java.basic.service.factory.ServiceFactory;
import com.epam.rd.java.basic.service.impl.*;

public class ServiceFactoryImpl implements ServiceFactory {


    @Override
    public CartItemService getCartItemService() {
        return new CartItemServiceImpl();
    }

    @Override
    public CartService getCartService() {
        return new CartServiceImpl();
    }

    @Override
    public CategoryService getCategoryService() {
        return new CategoryServiceImpl();
    }

    @Override
    public ItemService getItemService() {
        return new ItemServiceImpl();
    }

    @Override
    public RoleService getRoleService() {
        return new RoleServiceImpl();
    }

    @Override
    public StatusService getStatusService() {
        return new StatusServiceImpl();
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
