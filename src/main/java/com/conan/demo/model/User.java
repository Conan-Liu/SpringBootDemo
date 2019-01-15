package com.conan.demo.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/1/2.
 */
public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String password;
    private String phone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("[userId=%s, userName=%s, password=%s, phone=%s]", this.userId, this.userName, this.password, this.phone);
    }
}