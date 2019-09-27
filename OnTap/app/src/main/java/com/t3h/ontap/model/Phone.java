package com.t3h.ontap.model;

import java.io.Serializable;

public class Phone implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String image;

    public Phone(int id,String name, String phone,String image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
