package com.example.android.demoapp.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "yeuthich")
public class YeuThichEntry implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idSanPham;
    private String tenSanPham;
    private double giaSanPham;
    private double giaKhuyenMai;
    private String hinhAnhSanPham;
    private String khoiLuong;
    private int idHang;
    private  String moTa;
    private String thuongHieu;
    private  String xuatXu;
    private String tenSanPhamDE;
    private String moTaDE;
    private  int idShopBan;


    @Ignore
    public YeuThichEntry(int idSanPham, String tenSanPham, double giaSanPham, double giaKhuyenMai, String hinhAnhSanPham, String khoiLuong, int idHang, String moTa, String thuongHieu, String xuatXu, String tenSanPhamDE, String moTaDE, int idShopBan) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.giaKhuyenMai = giaKhuyenMai;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.idHang = idHang;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.tenSanPhamDE = tenSanPhamDE;
        this.moTaDE = moTaDE;
        this.idShopBan = idShopBan;
    }

    @Ignore
    public YeuThichEntry(int idSanPham, String tenSanPham, double giaSanPham, String hinhAnhSanPham, String khoiLuong, int idHang, String moTa, String thuongHieu, String xuatXu, String tenSanPhamDE, String moTaDE, int idShopBan) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.idHang = idHang;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.tenSanPhamDE = tenSanPhamDE;
        this.moTaDE = moTaDE;
        this.idShopBan = idShopBan;
    }



    @Ignore
    public YeuThichEntry(int id, int idSanPham, String tenSanPham, double giaSanPham, double giaKhuyenMai, String hinhAnhSanPham, String khoiLuong, int idHang, String moTa, String thuongHieu, String xuatXu, String tenSanPhamDE, String moTaDE, int idShopBan) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.giaKhuyenMai = giaKhuyenMai;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.idHang = idHang;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.tenSanPhamDE = tenSanPhamDE;
        this.moTaDE = moTaDE;
        this.idShopBan = idShopBan;
    }

    public YeuThichEntry(int id, int idSanPham, String tenSanPham, double giaSanPham, String hinhAnhSanPham, String khoiLuong, int idHang, String moTa, String thuongHieu, String xuatXu, String tenSanPhamDE, String moTaDE, int idShopBan) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.idHang = idHang;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.tenSanPhamDE = tenSanPhamDE;
        this.moTaDE = moTaDE;
        this.idShopBan = idShopBan;
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

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public double getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setGiaKhuyenMai(double giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }

    public String getTenSanPhamDE() {
        return tenSanPhamDE;
    }

    public void setTenSanPhamDE(String tenSanPhamDE) {
        this.tenSanPhamDE = tenSanPhamDE;
    }

    public String getMoTaDE() {
        return moTaDE;
    }

    public void setMoTaDE(String moTaDE) {
        this.moTaDE = moTaDE;
    }

    public int getIdShopBan() {
        return idShopBan;
    }

    public void setIdShopBan(int idShopBan) {
        this.idShopBan = idShopBan;
    }
}


