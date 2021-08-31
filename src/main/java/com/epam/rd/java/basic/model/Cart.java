package com.epam.rd.java.basic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Cart extends BaseEntity {

    private Status status;
    private User customer;
    private User userApproved;
    private Timestamp createTime;
    private Timestamp updateTime;

}
