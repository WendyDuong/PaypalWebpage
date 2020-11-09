package com.example.android.demoapp.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.demoapp.database.AppDatabase;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;
    private final int mId;

    public DetailViewModelFactory(AppDatabase database, int id) {
        mDb = database;
        mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailViewModel(mDb, mId);
}}
