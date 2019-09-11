package com.t3h.chatApp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chat implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("name")
    private String Name;

    @SerializedName("pub_date")
    private String pubDate;

    @SerializedName("message")
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
