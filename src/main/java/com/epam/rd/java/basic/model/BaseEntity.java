package com.epam.rd.java.basic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
public abstract class BaseEntity implements Serializable {

    private int id;

}
