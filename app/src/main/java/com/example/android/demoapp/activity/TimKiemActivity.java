package com.example.android.demoapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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






































 /*       tabLayout = findViewById(R.id.tab_layout_tim_kiem_activity);

       tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));
        tabYeuThich = tabLayout.getTabAt(0);
        tabGioHang= tabLayout.getTabAt(1);
        badgeDrawableYeuThich = tabYeuThich.getOrCreateBadge();

        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        setupbadgeDrawable();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(TimKiemActivity.this, YeuthichActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(TimKiemActivity.this, GioHangActivity.class);
                        startActivity(intent1);

                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:

                        Intent intent0 = new Intent(TimKiemActivity.this, YeuthichActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(TimKiemActivity.this, GioHangActivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }
            }

        });  */



    /*private void setupbadgeDrawable() {
        Cursor cursorSoSanPhamYeuThich = getContentResolver().query(SanPhamContract.SanPhamEntry.CONTENT_URI_2, null,null,null,null);
        if (cursorSoSanPhamYeuThich.getCount() > 0)
        {
            badgeDrawableYeuThich.setVisible(true);

            badgeDrawableYeuThich.setNumber(cursorSoSanPhamYeuThich.getCount());
        }
        else
            badgeDrawableYeuThich.setVisible(false);

        cursorSoSanPhamYeuThich.close();

        int sosanphammua = 0;

        Cursor cursorSoSanPhamGioHang = getContentResolver().query(SanPhamContract.SanPhamEntry.CONTENT_URI, new String[] {SanPhamContract.SanPhamEntry.COLUMN_SOLUONG},null,null,null);
        if (cursorSoSanPhamGioHang.getCount() > 0){
            for (int i = 0; i < cursorSoSanPhamGioHang.getCount(); i++) {
                cursorSoSanPhamGioHang.moveToPosition(i);
                sosanphammua = sosanphammua + cursorSoSanPhamGioHang.getInt(cursorSoSanPhamGioHang.getColumnIndex(SanPhamContract.SanPhamEntry.COLUMN_SOLUONG));
            }
            badgeDrawableGioHang.setVisible(true);

            badgeDrawableGioHang.setNumber(sosanphammua);
        }
        else
            badgeDrawableGioHang.setVisible(false);
        cursorSoSanPhamGioHang.close();

    }

    @Override
    protected void onRestart() {
        setupbadgeDrawable();
        super.onRestart();
    }  */

