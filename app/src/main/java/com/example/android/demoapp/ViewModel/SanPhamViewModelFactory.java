package com.example.android.demoapp.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.demoapp.database.AppDatabase;

public class SanPhamViewModelFactory<T> extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mId;

    public SanPhamViewModelFactory(AppDatabase database, int id) {
        mDb = database;
        mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new SanPhamViewModel(mDb, mId);

}
}
