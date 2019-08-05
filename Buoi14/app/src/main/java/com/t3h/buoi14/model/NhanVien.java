package com.t3h.buoi14.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NhanVien")
public class NhanVien {
    @PrimaryKey(autoGenerate = true)
    private long MaNhanVien;

    @ColumnInfo
    private String User;
    @ColumnInfo
    private String Pass;

    public long getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(long maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
