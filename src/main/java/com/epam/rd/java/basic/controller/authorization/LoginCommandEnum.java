package com.epam.rd.java.basic.controller.authorization;

import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.authorization.command.LogoutCommand;
import com.epam.rd.java.basic.controller.authorization.command.SignInCommand;
import com.epam.rd.java.basic.controller.authorization.command.SignUpCommand;

public enum LoginCommandEnum {

    SIGN_IN{
        {
            this.command = new SignInCommand();
        }
    },
    SIGN_UP{
        {
            this.command = new SignUpCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    };

    Command command;
    public Command getCommand(){return command;}
}
