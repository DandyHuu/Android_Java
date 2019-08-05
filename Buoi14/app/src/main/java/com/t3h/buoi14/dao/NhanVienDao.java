package com.t3h.buoi14.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.t3h.buoi14.model.KhachHang;
import com.t3h.buoi14.model.NhanVien;

import java.util.List;
@Dao
public interface NhanVienDao {
    @Query("SELECT * FROM NhanVien")
    List<NhanVien> getNhanVien();

    @Insert
    void insert(NhanVien student);

    @Update
    void update(NhanVien student);

    @Delete
    void delete(NhanVien student);
}
