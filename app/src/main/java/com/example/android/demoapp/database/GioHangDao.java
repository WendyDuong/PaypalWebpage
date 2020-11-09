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
public interface GioHangDao {
    @Query("SELECT * FROM giohang")
    LiveData<List<GioHangEntry>> loadAllGioHang();
    @Insert
    void insertGioHang(GioHangEntry gioHangEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGioHang(GioHangEntry gioHangEntry);

    @Delete
    void deleteGioHang(GioHangEntry gioHangEntry);

}
