package com.example.android.demoapp.database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;


@Database(entities = {SanPhamEntry.class, GioHangEntry.class, YeuThichEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "demoapp";
    private static AppDatabase sInstance;

    public synchronized static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = buildDatabase(context);
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                AppDatabase.DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("kiem tra insert sanpham", "da insert");

                            }
                        });
                    }
                })
                .build();

    }

    public abstract SanPhamDao sanPhamDao();

    public abstract GioHangDao gioHangDao();

    public abstract YeuThichDao yeuThichDao();
}
