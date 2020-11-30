package com.example.android.demoapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.demoapp.R;
import com.example.android.demoapp.fragment.MainFragment;

public class GiaoHang extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_hang);
        Toolbar toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainFragment.mangchinhsach.get(1).getTenbanner());

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        TextView tv = findViewById(R.id.tv_giao_hang);

        tv.setText(MainFragment.mangchinhsach.get(1).getAnhbanner());
    }

}