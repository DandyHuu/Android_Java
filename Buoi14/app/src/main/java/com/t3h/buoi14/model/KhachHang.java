package com.t3h.buoi14.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "KhachHang")
public class KhachHang {
    @PrimaryKey(autoGenerate = true)
    private long MaKhachHang;

    @ColumnInfo
    private String Ten;
    @ColumnInfo
    private String NgaySinh;
    @ColumnInfo
    private String CoQuan;
    @ColumnInfo
    private String ChucVu;
    @ColumnInfo
    private String Email;
    @ColumnInfo
    private String SoDienThoai;
    @ColumnInfo
    private String DiaChi;
    @ColumnInfo
    private boolean LoaiKhachHang;

    public long getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(long maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getCoQuan() {
        return CoQuan;
    }

    public void setCoQuan(String coQuan) {
        CoQuan = coQuan;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public boolean isLoaiKhachHang() {
        return LoaiKhachHang;
    }

    public void setLoaiKhachHang(boolean loaiKhachHang) {
        LoaiKhachHang = loaiKhachHang;
    }
}
