package com.example.android.demoapp.ViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.demoapp.database.AppDatabase;

public class SanPhamViewModelFactory<T> extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mId;
    //private String mClassViewModelName;

    public SanPhamViewModelFactory(AppDatabase database, int id) {
        mDb = database;
        mId = id;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new SanPhamViewModel(mDb, mId);
/*
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (mClassViewModelName.equals("DetailViewModel")) {
            //noinspection unchecked
            return (T) new DetailViewModel(mDb, mId);
        }
        else {
            //noinspection unchecked
            return (T) new SanPhamViewModel(mDb, mId);
        }
*/

}
}
