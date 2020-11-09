package com.example.android.demoapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.demoapp.R;

public class GiaoHang extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_hang);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        Toolbar toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        TextView tv = findViewById(R.id.tv_giao_hang);
        tv.setText(R.string.thong_tin_giao_hang);
    }

}