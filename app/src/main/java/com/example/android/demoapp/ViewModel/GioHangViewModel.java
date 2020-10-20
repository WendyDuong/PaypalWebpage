package com.example.android.demoapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;

import java.util.List;

public class GioHangViewModel extends AndroidViewModel {
    private static final String TAG = GioHangViewModel.class.getSimpleName();

    private LiveData<List<GioHangEntry>> sanPhams;
    private LiveData<List<YeuThichEntry>> yeuThich;

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onLifeCycleStop(){

    }

    public GioHangViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        sanPhams = database.gioHangDao().loadAllGioHang();
        yeuThich = database.yeuThichDao().loadAllYeuThich();


    }

    public LiveData<List<GioHangEntry>> getGioHang() {
        return sanPhams;
    }
    public LiveData<List<YeuThichEntry>> getYeuThich(){
        return yeuThich;
    }

}
