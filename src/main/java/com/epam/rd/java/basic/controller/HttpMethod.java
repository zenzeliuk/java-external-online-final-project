package com.epam.rd.java.basic.controller;

public enum HttpMethod {

    GET("GET"),
    POST("POST");

    String value;

    HttpMethod(String value) {
        this.value = value;
    }
    public static boolean isGet(String method){
        return method.equals(GET.value);
    }
    public static boolean isPost(String method){
        return method.equals(POST.value);
    }
}
