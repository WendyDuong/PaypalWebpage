package com.example.android.demoapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;

import java.util.List;

public class YeuThichViewModel extends AndroidViewModel {

    private static final String TAG = YeuThichViewModel.class.getSimpleName();

    private LiveData<List<YeuThichEntry>> sanPhams;
    private LiveData<List<GioHangEntry>> gioHang;

    public YeuThichViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        sanPhams = database.yeuThichDao().loadAllYeuThich();
        gioHang = database.gioHangDao().loadAllGioHang();
    }

    public LiveData<List<YeuThichEntry>> getYeuThich() {
        return sanPhams;
    }
    public LiveData<List<GioHangEntry>> getGioHang() {
        return gioHang;
    }

}
