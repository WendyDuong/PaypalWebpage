package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.demoapp.R;

public class TimKiemActivity extends AppCompatActivity {
  Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tim_kiem_activity);


        toolbar = findViewById(R.id.toolbar_tim_kiem);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.icon_favorite:
                Intent intentSearch = new Intent(this, YeuthichActivity.class);
                startActivity(intentSearch);
                break;

            case R.id.icon_gio_do:
                Intent intentGioHang = new Intent(this, GioHangActivity.class);
                startActivity(intentGioHang);
                break;

        }
        return super.onOptionsItemSelected(item);

    }



}
