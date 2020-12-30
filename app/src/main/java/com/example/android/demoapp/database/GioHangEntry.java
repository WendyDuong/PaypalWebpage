package com.example.android.demoapp.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "giohang")
public class GioHangEntry implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idSanPham;
    private  String tenSanPham;
    private double giaSanPham;
    private String hinhAnhSanPham;
    private String khoiLuong;
    private int soLuong;
    private int idHang;
    private  String moTa;
    private String thuongHieu;
    private  String xuatXu;


    @Ignore
    public GioHangEntry(int idSanPham, String tenSanPham, double giaSanPham, String hinhAnhSanPham, String khoiLuong, int soLuong, int idHang, String moTa, String thuongHieu, String xuatXu) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.soLuong = soLuong;
        this.idHang = idHang;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
    }

    public GioHangEntry(int id, int idSanPham, String tenSanPham, double giaSanPham, String hinhAnhSanPham, String khoiLuong, int soLuong, int idHang, String moTa, String thuongHieu, String xuatXu) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.soLuong = soLuong;
        this.idHang = idHang;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
    }

    public int getIdHang() {
        return idHang;
    }

    public void setIdHang(int idHang) {
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }
}
