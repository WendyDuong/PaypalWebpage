package com.example.android.demoapp.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "sanpham")
public class SanPhamEntry {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    private  int idHang;
    private String tenSanPham;
    private double giaSanPham;
    private int hinhAnh;
    private String khoiLuong;
    private  String moTa;
    private String thuongHieu;
    private  String xuatXu;


    @Ignore
    public SanPhamEntry(int idHang, String tenSanPham, double giaSanPham, int hinhAnh,
                        String khoiLuong, String moTa, String thuongHieu, String xuatXu) {
        this.idHang = idHang;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnh = hinhAnh;
        this.khoiLuong = khoiLuong;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
    }

    public SanPhamEntry(int id, int idHang, String tenSanPham, double giaSanPham,
                        int hinhAnh, String khoiLuong, String moTa, String thuongHieu, String xuatXu) {
        this.id = id;
        this.idHang = idHang;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnh = hinhAnh;
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

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
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
}
