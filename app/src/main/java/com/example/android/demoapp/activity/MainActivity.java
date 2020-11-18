package com.example.android.demoapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.MainViewModel;
import com.example.android.demoapp.adapter.CategoryAdapter;
import com.example.android.demoapp.adapter.NavigationViewAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;
    CategoryAdapter viewPageAdapter;
    TabLayout.Tab tabGioHang;
    TabLayout.Tab tabYeuThich;
    RecyclerView list;
    NavigationViewAdapter recyclerAdapter;
    DrawerLayout mDrawerLayout;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    MainViewModel mainViewModel;
    AppDatabase mDb;
    private BadgeDrawable badgeDrawableYeuthich;
    private BadgeDrawable badgeDrawableGioHang;
    private static final int REQUEST_OVERLAY_PERMISSION = 5469;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ArrayList<String> caNhan = new ArrayList<>();

        caNhan.add(getString(R.string.hinh_thuc_thanh_toan));
        caNhan.add(getString(R.string.giao_hang));
        caNhan.add(getString(R.string.chinh_sach_doi_tra));
        caNhan.add(getString(R.string.lien_he));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.END);

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        recyclerAdapter = new NavigationViewAdapter(MainActivity.this, caNhan);
        list.setAdapter(recyclerAdapter);

    }



    @Override
    public void onBackPressed() {
       if(mDrawerLayout.isDrawerOpen(GravityCompat.END)){
           mDrawerLayout.closeDrawer(GravityCompat.END);
       }else{
        super.onBackPressed();}
    }


        private void init () {
            toolbar = findViewById(R.id.toolbar);
            mDrawerLayout = findViewById(R.id.drawer_layout);
            imageView = findViewById(R.id.anhcanhan);
            list = (RecyclerView) findViewById(R.id.recycleview);
            viewPager2 = (ViewPager2) findViewById(R.id.viewpager);
            viewPageAdapter = new CategoryAdapter(this);
            viewPager2.setAdapter(viewPageAdapter);

            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    switch (position) {
                        case 0: {
                            tab.setIcon(R.drawable.iconhome);
                            break;
                        }
                        case 1: {
                            tab.setIcon(R.drawable.kinh_lup_icon);
                            break;
                        }
                        case 2: {
                            tab.setIcon(R.drawable.timdo_bar);
                            break;
                        }
                        case 3: {
                            tab.setIcon(R.drawable.xe_hang);
                            break;
                        }
                    }
                }
            });

            tabLayoutMediator.attach();
            tabYeuThich = tabLayout.getTabAt(2);
            tabGioHang = tabLayout.getTabAt(3);
            assert tabGioHang != null;
            badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
            assert tabYeuThich != null;
            badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();
            badgeDrawableGioHang.setMaxCharacterCount(3);
            badgeDrawableYeuthich.setMaxCharacterCount(3);

            mDb = AppDatabase.getInstance(getApplicationContext());
            mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
            mainViewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                @Override
                public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                    gioHangEntries = gioHang;
                    int sosanphammua = 0;
                    assert gioHangEntries != null;
                    if (gioHangEntries.size() > 0) {
                        for (int i = 0; i < gioHangEntries.size(); i++) {
                            sosanphammua += gioHangEntries.get(i).getSoLuong();
                        }
                        badgeDrawableGioHang.setVisible(true);

                        badgeDrawableGioHang.setNumber(sosanphammua);
                    } else
                        badgeDrawableGioHang.setVisible(false);
                }
            });

            mainViewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                @Override
                public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                    yeuThichEntries = yeuThich;
                    assert yeuThichEntries != null;
                    if (yeuThichEntries.size() > 0) {
                        badgeDrawableYeuthich.setVisible(true);
                        badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                        if(yeuThichEntries.size() > 99){
                            badgeDrawableYeuthich.setVisible(true);
                            badgeDrawableYeuthich.setNumber(yeuThichEntries.size());
                        }

                    } else
                        badgeDrawableYeuthich.setVisible(false);

                }
            });

        }
        }







