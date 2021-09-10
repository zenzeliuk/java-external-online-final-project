//package com.epam.rd.java.basic.controller.old;
//
//
//import com.epam.rd.java.basic.controller.Command;
//
//public class CommandList {
//
//    private static final Map<String, Command> commandMap = new TreeMap<>();
//
//    static {
//        commandMap.put("login", new LoginCommand());
//        commandMap.put("logout", new LogoutCommand());
//        commandMap.put("registration", new RegistrationCommand());
//        commandMap.put("items", new ItemsCommand());
//        commandMap.put("add_to_cart", new AddToCartCommand());
//    }
//
//    public static Command parse(String commandFromParameter) {
//
//        if (commandFromParameter == null || !commandMap.containsKey(commandFromParameter)) {
//            return commandMap.get("wrong");
//        }
//        return commandMap.get(commandFromParameter);
//    }
//}
