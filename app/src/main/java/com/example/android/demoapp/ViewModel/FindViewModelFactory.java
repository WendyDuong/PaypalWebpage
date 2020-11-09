package com.example.android.demoapp.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.demoapp.database.AppDatabase;

public class FindViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;
    private final String mTenSanPham;

    public FindViewModelFactory(AppDatabase database, String tenSanPham) {
        mDb = database;
        mTenSanPham = tenSanPham;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new FindViewModel(mDb, mTenSanPham);
    }
}
