package com.example.android.demoapp.model;

public class HangSanPham {
    private int idHang;
    private String tenHang, anhHang;

    public HangSanPham(int idHang, String tenHang, String anhHang) {
        this.idHang = idHang;
        this.tenHang = tenHang;
        this.anhHang = anhHang;
    }

    public int getIdHang() {
        return idHang;
    }

    public void setIdHang(int idHang) {
        this.idHang = idHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getAnhHang() {
        return anhHang;
    }

    public void setAnhHang(String anhHang) {
        this.anhHang = anhHang;
    }
}

