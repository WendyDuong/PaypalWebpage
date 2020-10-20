package com.example.android.demoapp.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.demoapp.R;

public class ThanhToan extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        TextView tv = findViewById(R.id.tv_thanh_toan);
        tv.setText(R.string.thong_tin_thanh_toan);

    }
}