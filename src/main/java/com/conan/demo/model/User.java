package com.conan.demo.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/1/2.
 */
public class User implements Serializable {

    private Integer id;
    private String name;
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}