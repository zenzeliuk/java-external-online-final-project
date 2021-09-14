package com.epam.rd.java.basic.controller.util;

import com.epam.rd.java.basic.controller.ActionCommandEnum;
import com.epam.rd.java.basic.controller.Command;
import com.epam.rd.java.basic.controller.annotations.Action;
import com.epam.rd.java.basic.exception.NotFoundCommandException;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CommandFactory {

    private HttpServletRequest request;

    public CommandFactory(HttpServletRequest request) {
        this.request = request;
    }

    public Command getCommand() {
        return ActionCommandEnum.valueOf(getCommandName(ActionCommandEnum.class)).getCurrentCommand();
    }

    String getCommandName(Class enumCommand) {
        String url = request.getRequestURI().replaceFirst(request.getContextPath(), "");
        for (Field field : enumCommand.getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof Action) {
                    if (((Action) annotation).url().equals(url)
                            && ((Action) annotation).method().equals(request.getMethod())) {
                        return field.getName();
                    }
                }
            }
        }
        throw new NotFoundCommandException("Not found Command for " + url);
    }


}
