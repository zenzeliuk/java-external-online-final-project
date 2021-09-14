package com.epam.rd.java.basic.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CartDTO {

    private String id;
    private String statusName;
    private Timestamp createTime;
    private Timestamp updateTime;

}
