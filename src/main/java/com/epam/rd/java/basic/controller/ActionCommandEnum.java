package com.epam.rd.java.basic.controller;

import com.epam.rd.java.basic.controller.annotations.Action;
import com.epam.rd.java.basic.controller.command.admin.AdminHomeCommandGET;
import com.epam.rd.java.basic.controller.command.auth.*;
import com.epam.rd.java.basic.controller.command.cart.CartCommandGET;
import com.epam.rd.java.basic.controller.command.cart.ConfirmCartCommandPOST;
import com.epam.rd.java.basic.controller.command.cart.EditCountItemCommandGET;
import com.epam.rd.java.basic.controller.command.item.AddItemToCartCommandPOST;
import com.epam.rd.java.basic.controller.command.item.ItemsCommandGET;
import com.epam.rd.java.basic.controller.command.user.UserHomeCommandGET;

public enum ActionCommandEnum {

    @Action(url = "/login/sign-in", method = "POST")
    SIGN_IN_POST {
        {
            this.command = new SingInCommandPOST();
        }
    },

    @Action(url = "/login/sign-in", method = "GET")
    SIGN_IN_GET {
        {
            this.command = new SingInCommandGET();
        }
    },

    @Action(url = "/login/sign-up", method = "POST")
    SIGN_UP_POST {
        {
            this.command = new SingUpCommandPOST();
        }
    },

    @Action(url = "/login/sign-up", method = "GET")
    SIGN_UP_GET {
        {
            this.command = new SingUpCommandGET();
        }
    },

    @Action(url = "/login/logout", method = "GET")
    LOG_OUT_GET {
        {
            this.command = new LogOutCommandGET();
        }
    },

    @Action(url = "/app/item", method = "GET")
    ITEMS {
        {
            this.command = new ItemsCommandGET();
        }
    },

    @Action(url = "/app/add-to-cart", method = "POST")
    ADD_ITEM_TO_CART {
        {
            this.command = new AddItemToCartCommandPOST();
        }
    },
    @Action(url = "/app/cart", method = "GET")
    CART {
        {
            this.command = new CartCommandGET();
        }
    },

    @Action(url = "/app/confirm-cart", method = "POST")
    CONFIRM_CART {
        {
            this.command = new ConfirmCartCommandPOST();
        }
    },

    @Action(url = "/app/edit-count", method = "GET")
    EDIT_COUNT_ITEM_CART {
        {
            this.command = new EditCountItemCommandGET();
        }
    },

    @Action(url = "/admin/home", method = "GET")
    ADMIN_HOME {
        {
            this.command = new AdminHomeCommandGET();
        }
    },

    @Action(url = "/user/home", method = "GET")
   USER_HOME {
        {
            this.command = new UserHomeCommandGET();
        }
    },


    ;

    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
