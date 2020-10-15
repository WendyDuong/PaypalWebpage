package com.example.android.demoapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<GioHangEntry>> gioHang;
    private LiveData<List<YeuThichEntry>> yeuThich;


    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        gioHang = database.gioHangDao().loadAllGioHang();
        yeuThich = database.yeuThichDao().loadAllYeuThich();

    }

    public LiveData<List<GioHangEntry>> getGioHang() {
        return gioHang;
    }

    public LiveData<List<YeuThichEntry>> getYeuThich(){
        return yeuThich;
    }
}
