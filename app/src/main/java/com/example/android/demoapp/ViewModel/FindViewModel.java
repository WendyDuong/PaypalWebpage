package com.example.android.demoapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.SanPhamEntry;
import com.example.android.demoapp.database.YeuThichEntry;

import java.util.List;

public class FindViewModel extends ViewModel {
    private LiveData<List<SanPhamEntry>> sanPhams;
    private LiveData<List<GioHangEntry>> gioHang;
    private LiveData<List<YeuThichEntry>> yeuThich;

    public FindViewModel(AppDatabase database, String tenSanPham) {
        sanPhams = database.sanPhamDao().loadSanPhamByTen(tenSanPham);
        gioHang = database.gioHangDao().loadAllGioHang();
        yeuThich = database.yeuThichDao().loadAllYeuThich();
    }

    public LiveData<List<SanPhamEntry>> getSanPhams() {
        return sanPhams;
    }

    public LiveData<List<GioHangEntry>> getGioHang() {
        return gioHang;
    }

    public LiveData<List<YeuThichEntry>> getYeuThich(){
        return yeuThich;
    }

}
