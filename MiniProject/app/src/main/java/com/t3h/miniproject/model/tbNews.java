package com.t3h.miniproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity(tableName = "tbnews")
public class tbNews implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String desc;

    @ColumnInfo
    private String url;

    @ColumnInfo
    private String image;

    @ColumnInfo
    private String date;

    @ColumnInfo
    private String content;

    @ColumnInfo
    private boolean hobbit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isHobbit() {
        return hobbit;
    }

    public void setHobbit(boolean hobbit) {
        this.hobbit = hobbit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
