package com.epam.rd.java.basic.controller.util;

public enum Sorting {
    NAME_INCREASE(1,"Назвою А-Я"),
    NAME_DECREASE(2,"Назвою Я-А"),
    PRICE_INCREASE(3,"Від дешевих до дорогих"),
    PRICE_DECREASE(4,"Від дорогих до дешевих"),
    NOVELTY(5,"Новизною");

    private final int id;
    private final String name;

    Sorting(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
