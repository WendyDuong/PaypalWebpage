package com.example.android.demoapp.View.DangNhap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.ViewPagerAdaterDangNhap;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * Created by Lenovo S410p on 6/29/2016.
 */
public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        tabLayout = (TabLayout) findViewById(R.id.tabDangNhap);
        viewPager = (ViewPager2) findViewById(R.id.viewPagerDangNhap);
        toolbar = (Toolbar) findViewById(R.id.toolBarDangNhap);

        setSupportActionBar(toolbar);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 : {
                        tab.setText("Đăng nhập");
                        break;
                    }
                    case 1 : {
                        tab.setText("Đăng ký");
                        break;
                }

             }}});


        ViewPagerAdaterDangNhap viewPagerAdaterDangNhap = new ViewPagerAdaterDangNhap(this);
        viewPager.setAdapter(viewPagerAdaterDangNhap);
        tabLayoutMediator.attach();
    }
}
