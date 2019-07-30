package com.t3h.baitapbuoi9.model;

public class SinhVien {
    private String maSV;
    private String tenSV;
    private String lop;
    private double diem;
    private int img;

    public SinhVien(String maSV, String tenSV, String lop, double diem, int img) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.lop = lop;
        this.diem = diem;
        this.img = img;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public String getLop() {
        return lop;
    }

    public double getDiem() {
        return diem;
    }

    public int getImg() {
        return img;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
