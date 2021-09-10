package com.epam.rd.java.basic.controller.authorization;

import com.epam.rd.java.basic.controller.CommandFactory;
import com.epam.rd.java.basic.controller.UriMarshaller;
import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.exception.UnexistingUrlException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationCommandFactory extends CommandFactory {

    public AuthorizationCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request);
        String action = marshaller.getAction();
        if (action == null) {
            throw new UnexistingUrlException();
        }
        switch (action) {
            case "SignIn": {
                return LoginCommandEnum.SIGN_IN.getCommand();
            }
            case "SignUp": {
                return LoginCommandEnum.SIGN_UP.getCommand();
            }
            case "Logout": {
                return LoginCommandEnum.LOGOUT.getCommand();
            }
            default: {
                throw new UnexistingUrlException();
            }
        }
    }
}
