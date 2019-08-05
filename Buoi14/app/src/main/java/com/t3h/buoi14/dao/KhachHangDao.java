package com.t3h.buoi14.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.t3h.buoi14.model.KhachHang;

import java.util.List;
@Dao
public interface KhachHangDao {
    @Query("SELECT * FROM KhachHang")
    List<KhachHang> getKhachHang();
    @Query("SELECT * FROM KhachHang WHERE Ten LIKE :key")
    List<KhachHang> getKhachHangWithKeySearch(String key);
    @Insert
    void insert(KhachHang student);

    @Update
    void update(KhachHang student);

    @Delete
    void delete(KhachHang student);
}
