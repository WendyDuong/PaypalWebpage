package com.example.android.demoapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.SanPhamEntry;
import com.example.android.demoapp.database.YeuThichEntry;

import java.util.List;


//TODO(5) add DetailViewModel class
public class DetailViewModel extends ViewModel {

    private LiveData<SanPhamEntry> sanPhams;
    private LiveData<List<GioHangEntry>> gioHang;
    private LiveData<List<YeuThichEntry>> yeuThich;

    public DetailViewModel(AppDatabase database, int id) {
        sanPhams = database.sanPhamDao().loadSanPhamById(id);
        gioHang = database.gioHangDao().loadAllGioHang();
        yeuThich = database.yeuThichDao().loadAllYeuThich();

    }

    public LiveData<SanPhamEntry> getDetailSanPham() {
        return sanPhams;
    }
    public LiveData<List<GioHangEntry>> getGioHang() {
        return gioHang;
    }

    public LiveData<List<YeuThichEntry>> getYeuThich(){
        return yeuThich;
    }

}
