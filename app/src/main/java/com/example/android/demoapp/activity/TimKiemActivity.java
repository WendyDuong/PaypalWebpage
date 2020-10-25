package com.example.android.demoapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.android.demoapp.R;
import com.google.android.material.tabs.TabLayout;

public class TimKiemActivity extends AppCompatActivity implements SearchView.OnQueryTextListener  {
  Toolbar toolbar;
    private TabLayout tabLayout;
    private  TabLayout.Tab tabGioHang;
    private  TabLayout.Tab tabYeuThich;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tim_kiem_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        toolbar = findViewById(R.id.toolbar_tim_kiem);
        setSupportActionBar(toolbar);
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
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) itSearch.getActionView();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnQueryTextListener(this);
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(TimKiemActivity.this,R.drawable.kinh_lup_icon));
        searchIcon.setColorFilter(Color.WHITE);
        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        searchClose.setColorFilter(Color.WHITE);
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.white));
        searchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));
        searchView.findViewById(androidx.appcompat.R.id.search_mag_icon).setVisibility(View.GONE);
        View v = searchView.findViewById(androidx.appcompat.R.id.search_plate);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(final String query) {
        final String tensanpham = query.trim();
        Intent intentTimKiem = new Intent(TimKiemActivity.this, FindActivity.class);
        intentTimKiem.putExtra("tensanphamtimkiem", tensanpham);
        startActivity(intentTimKiem);
        return false;

    }










    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}




