package com.example.android.demoapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;

import java.util.List;

public class DatHangViewModel extends AndroidViewModel {
    private static final String TAG = DatHangViewModel.class.getSimpleName();

    private LiveData<List<GioHangEntry>> sanPhams;


    public DatHangViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        sanPhams = database.gioHangDao().loadAllGioHang();

    }

    public LiveData<List<GioHangEntry>> getDatHang() {
        return sanPhams;
    }
}
