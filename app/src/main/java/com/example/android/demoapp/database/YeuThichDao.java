package com.example.android.demoapp.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface YeuThichDao {
    @Query("SELECT * FROM yeuthich")
    LiveData<List<YeuThichEntry>> loadAllYeuThich();

    @Insert
    void insertYeuThich(YeuThichEntry yeuThichEntry);

    @Delete
    void deleteYeuThich(YeuThichEntry yeuThichEntry);

    @Query("SELECT * FROM yeuthich")
    List<YeuThichEntry> loadDanhSachYeuThich();

}
