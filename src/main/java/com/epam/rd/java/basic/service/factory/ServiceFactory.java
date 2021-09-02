package com.epam.rd.java.basic.service.factory;

import com.epam.rd.java.basic.service.*;

public interface ServiceFactory {

    CartItemService getCartItemService();
    CartService getCartService();
    CategoryService getCategoryService();
    ItemService getItemService();
    RoleService getRoleService();
    StatusService getStatusService();
    UserDetailsService getUserDetailsService();
    UserService getUserService();

}
