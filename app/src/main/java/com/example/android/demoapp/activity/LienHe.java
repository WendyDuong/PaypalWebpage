package com.example.android.demoapp.activity;


import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.android.demoapp.R;
import com.example.android.demoapp.fragment.MainFragment;

public class LienHe extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);

        Toolbar toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainFragment.mangchinhsach.get(3).getTenbanner());

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        TextView tv = findViewById(R.id.tv_lien_he);
        tv.setText(MainFragment.mangchinhsach.get(3).getAnhbanner());


    }
}
