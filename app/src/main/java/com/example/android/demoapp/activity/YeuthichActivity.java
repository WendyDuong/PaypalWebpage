package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.YeuThichViewModel;
import com.example.android.demoapp.database.GioHangEntry;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class YeuthichActivity  extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private  TabLayout.Tab tabGioHang;
    public static   BadgeDrawable badgeDrawableGioHang;
    public  static boolean yeuThichActivityOnCreat = false;


    List<GioHangEntry> gioHangEntries;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeuthich_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        yeuThichActivityOnCreat = true;
        toolbar = findViewById(R.id.toolbar_yeu_thich_activity);


        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        tabLayout = findViewById(R.id.tab_layout_yeu_thich_activity);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kinh_lup_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));
        tabGioHang= tabLayout.getTabAt(1);
        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(YeuthichActivity.this, TimKiemActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(YeuthichActivity.this, GioHangActivity.class);
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

                        Intent intent0 = new Intent(YeuthichActivity.this, TimKiemActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(YeuthichActivity.this, GioHangActivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }
            }

    });


        YeuThichViewModel viewModel = ViewModelProviders.of(this).get(YeuThichViewModel.class);
        viewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
            @Override
            public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                gioHangEntries = gioHang;
                int sosanphammua = 0;

                if (gioHangEntries.size() > 0){
                    for (int i = 0; i < gioHangEntries.size(); i++) {
                        sosanphammua += gioHangEntries.get(i).getSoLuong();
                    }
                    badgeDrawableGioHang.setVisible(true);

                    badgeDrawableGioHang.setNumber(sosanphammua);
                }
                else
                    badgeDrawableGioHang.setVisible(false);
            }
        });
    }

/*
    private void setupbadgeDrawableGioHang() {

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
         setupbadgeDrawableGioHang();
        super.onRestart();
    }
*/


}






