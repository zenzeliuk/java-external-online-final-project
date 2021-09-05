package com.epam.rd.java.basic.controller.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandList {

    private static final Map<String, Command> commandMap = new TreeMap<>();

    static {
        commandMap.put("login", new LoginCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("items", new ItemsCommand());
    }

    public static Command parse(String commandFromParameter) {

        if (commandFromParameter == null || !commandMap.containsKey(commandFromParameter)) {
            return commandMap.get("wrong");
        }
        return commandMap.get(commandFromParameter);
    }
}
