package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.FindViewModel;
import com.example.android.demoapp.ViewModel.FindViewModelFactory;
import com.example.android.demoapp.adapter.CatalogAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.SanPhamEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class FindActivity extends AppCompatActivity {
    private static final String TAG = FindActivity.class.getSimpleName();

    private static final String INSTANCE_ID = "instanceID";

    private static final String EXTRA_TEN_SAN_PHAM_TIM_KIEM = "tensanphamtimkiem";
    private static final String DEFAULT_STRING = "";

    String mTenSanPham;
    private AppDatabase mDb;

    Toolbar toolbar;
    private TabLayout tabLayout;
    private TabLayout.Tab tabGioHang;
    private TabLayout.Tab tabYeuThich;

    CatalogAdapter timKiemAdapter;
    RecyclerView recyclerView;
    public static boolean FindActivityOnCreat = false;

    public static BadgeDrawable badgeDrawableGioHang;
    public static BadgeDrawable badgeDrawableYeuthich;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        FindActivityOnCreat = true;
        toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        tabLayout = findViewById(R.id.tab_layout_tim_do);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kinh_lup_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));


        tabYeuThich = tabLayout.getTabAt(1);
        tabGioHang = tabLayout.getTabAt(2);
        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(FindActivity.this, TimKiemActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(FindActivity.this, YeuthichActivity.class);
                        startActivity(intent1);

                        break;
                    case 2:
                        Intent intent2 = new Intent(FindActivity.this, GioHangActivity.class);
                        startActivity(intent2);

                        break;


                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(FindActivity.this, TimKiemActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(FindActivity.this, YeuthichActivity.class);
                        startActivity(intent1);

                        break;
                    case 2:
                        Intent intent2 = new Intent(FindActivity.this, GioHangActivity.class);
                        startActivity(intent2);

                        break;


                    default:
                        break;
                }
            }

        });

        timKiemAdapter = new CatalogAdapter(FindActivity.this);
        mDb = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TEN_SAN_PHAM_TIM_KIEM)) {
            // populate the UI
            mTenSanPham = intent.getStringExtra(EXTRA_TEN_SAN_PHAM_TIM_KIEM);
            getSupportActionBar().setTitle(mTenSanPham);


            FindViewModelFactory factory = new FindViewModelFactory(mDb, mTenSanPham);
            // COMPLETED (11) Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
            // for that use the factory created above AddTaskViewModel
            final FindViewModel viewModel
                    = ViewModelProviders.of(this, factory).get(FindViewModel.class);

            viewModel.getSanPhams().observe(this, new Observer<List<SanPhamEntry>>() {
                @Override
                public void onChanged(@Nullable List<SanPhamEntry> sanPhams) {
                    Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                    timKiemAdapter.setSanPhams(sanPhams);
                }
            });

            viewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                @Override
                public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                    gioHangEntries = gioHang;
                    int sosanphammua = 0;

                    if (gioHangEntries.size() > 0){
                        for (int i = 0; i < gioHangEntries.size(); i++) {
                            sosanphammua += gioHangEntries.get(i).getSoLuong();
                        }
                        badgeDrawableGioHang.setVisible(true);

                        badgeDrawableGioHang.setNumber(sosanphammua);
                    }
                    else
                        badgeDrawableGioHang.setVisible(false);
                }
            });

            viewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                @Override
                public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                    yeuThichEntries = yeuThich;
                    if (yeuThichEntries.size()>0){
                        badgeDrawableYeuthich.setVisible(true);
                        badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                    }
                    else
                        badgeDrawableYeuthich.setVisible(false);

                }
            });

        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_tim_do);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(FindActivity.this, 2));
        recyclerView.setAdapter(timKiemAdapter);


    }

    @Override
    protected void onRestart() {
        timKiemAdapter.notifyDataSetChanged();
        super.onRestart();
    }
}