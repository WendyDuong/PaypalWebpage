package com.example.android.demoapp.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "yeuthich")
public class YeuThichEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idSanPham;
    private String tenSanPham;
    private double giaSanPham;
    private String hinhAnhSanPham;
    private String khoiLuong;
    private int idHang;

    @Ignore
    public YeuThichEntry(int idSanPham, String tenSanPham, double giaSanPham, String hinhAnhSanPham, String khoiLuong, int idHang) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.idHang = idHang;
    }

    public YeuThichEntry(int id, int idSanPham, String tenSanPham, double giaSanPham, String hinhAnhSanPham, String khoiLuong, int idHang) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.idHang = idHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getHinhAnhSanPham() {
        return hinhAnhSanPham;
    }

    public void setHinhAnhSanPham(String hinhAnhSanPham) {
        this.hinhAnhSanPham = hinhAnhSanPham;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }
    public int getIdHang() {
        return idHang;
    }

    public void setIdHang(int idHang) {
        this.idHang = idHang;
    }
}


