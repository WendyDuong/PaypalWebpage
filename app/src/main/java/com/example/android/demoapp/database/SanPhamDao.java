package com.example.android.demoapp.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SanPhamDao {
    //Hien thi danh sach san pham cua hang
    @Query("SELECT * FROM sanpham WHERE idHang = :idHang")
    LiveData<List<SanPhamEntry>> loadSanPhamHang(int idHang);

    //Hien thi chi tiet san pham theo id san pham
    @Query("SELECT * FROM sanpham WHERE id = :id")
    LiveData<SanPhamEntry> loadSanPhamById(int id);

    @Query("SELECT * FROM sanpham WHERE tenSanPham LIKE '%' || :tensp || '%'")
    LiveData<List<SanPhamEntry>> loadSanPhamByTen( String tensp);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSanPham(SanPhamEntry sanPhamEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSanPham(SanPhamEntry sanPhamEntry);

    @Delete
    void deleteSanPham(SanPhamEntry sanPhamEntry);

}

