package com.example.android.demoapp.activity;


import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.demoapp.R;

public class LienHe extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        TextView tv = findViewById(R.id.tv_lien_he);
        tv.setText(R.string.thong_tin_lien_he);


    }
}
