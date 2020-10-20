package com.example.android.demoapp.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.demoapp.R;

public class DoiTra extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_tra);
        TextView tv = findViewById(R.id.tv_doi_tra);
        tv.setText(R.string.thong_tin_doi_tra);


    }
}
