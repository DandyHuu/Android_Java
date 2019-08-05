package com.t3h.buoi14.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.t3h.buoi14.model.KhachHang;

@Database(entities = {KhachHang.class}, version = 1)
public abstract class AppDataKhachHang extends RoomDatabase {
    private static AppDataKhachHang instance_;

    public static AppDataKhachHang getInstance_KH(Context context) {
        if (instance_ == null) {
            instance_ = Room.databaseBuilder(
                    context,
                    AppDataKhachHang.class,
                    "khachhang-db")
                    .allowMainThreadQueries()
                    .build();

        }
        return instance_;
    }

    public abstract KhachHangDao getKhachHangDao();
}
