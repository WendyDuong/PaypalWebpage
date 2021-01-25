package com.example.android.demoapp.model;


import java.io.Serializable;

public class SanPham implements Serializable {
    private  int id;
    private  int idHang;
    private String tenSanPham;
    private double giaSanPham;
    //TODO SALE
    private double giaKhuyenMai;
    private String hinhAnhSanPham;
    private String khoiLuong;
    private  String moTa;
    private String thuongHieu;
    private  String xuatXu;


    public SanPham (int idHang ,String tenSanPham, double giaSanPham, String hinhAnhSanPham,
                        String khoiLuong, String moTa, String thuongHieu, String xuatXu) {
        this.idHang = idHang;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
    }

    public SanPham (int id, int idHang, String tenSanPham, double giaSanPham,
                    String hinhAnhSanPham, String khoiLuong, String moTa, String thuongHieu, String xuatXu) {
        this.id = id;
        this.idHang = idHang;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
    }

    public SanPham(int id, int idHang, String tenSanPham, double giaSanPham,
                   double giaKhuyenMai, String hinhAnhSanPham, String khoiLuong, String moTa, String thuongHieu, String xuatXu) {
        this.id = id;
        this.idHang = idHang;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.giaKhuyenMai = giaKhuyenMai;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.khoiLuong = khoiLuong;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getIdHang() {
        return idHang;
    }

    public void setIdHang(int idHang) {
        this.idHang = idHang;
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

    public double getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setGiaKhuyenMai(double giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }
}
