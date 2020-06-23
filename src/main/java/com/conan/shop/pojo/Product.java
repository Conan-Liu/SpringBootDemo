package com.conan.shop.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable{

    private static final long serialVersionUID = -7776935652842571259L;

    private Integer id;
    private String productName;
    private Integer stock;
    private Double price;
    private Integer version;
    private String note;
}
