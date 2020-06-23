package com.conan.shop.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class PurchaseRecord implements Serializable {

    private static final long serialVersionUID = 4165765192015410199L;

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Double price;
    private Integer quantity;
    private Double sum;
    private Timestamp purchaseTime;
    private String note;
}
