package com.example.android.demoapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.CategoryAdapter;
import com.example.android.demoapp.model.GiohangItem;
import com.example.android.demoapp.model.Sanpham;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView imageView;
    public static ArrayList<GiohangItem> mangGioHang = new ArrayList<>();
    public static ArrayList<Sanpham> mangYeuthich = new ArrayList<>() ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ArrayList<String> caNhan = new ArrayList<String>();
        caNhan.add(getString(R.string.thong_tin_ca_nhan));
        caNhan.add(getString(R.string.hinh_thuc_thanh_toan));
        caNhan.add(getString(R.string.cac_don_hang));
        caNhan.add(getString(R.string.dang_xuat));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);

            }
        });

        ArrayAdapter<String> caNhanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, caNhan);
        listView.setAdapter(caNhanAdapter);
    }

    @Override
    public void onBackPressed() {
       if(drawerLayout.isDrawerOpen(GravityCompat.END)){
           drawerLayout.closeDrawer(GravityCompat.END);
       }else{
        super.onBackPressed();}
    }
      /*  TabLayout tabLayout = findViewById(R.id.tab_layout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {

                    case 3:
                        Intent intent = new Intent(MainActivity.this, GioActivity.class);
                        MainActivity.this.startActivity(intent);
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

                    case 3:
                        Intent intent = new Intent(MainActivity.this, GioActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;

                    default:
                        break;
                }
            }



        });


    }


  /**  private void actionToolBar() {
        setSupportActionBar(toolbar);
        toolbar.set(R.drawable.iconnguoidung);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);

            }
        });  */




        private void init () {
            toolbar = findViewById(R.id.toolbar);
            drawerLayout = findViewById(R.id.drawer_layout);
            imageView = findViewById(R.id.anhcanhan);
            listView = findViewById(R.id.list_view);
            if (mangGioHang != null) {

            } else {
                mangGioHang = new ArrayList<>();
            }

            if (mangYeuthich != null) {
            } else {
                mangYeuthich = new ArrayList<>();
            }
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            CategoryAdapter viewPageAdapter = new CategoryAdapter(this, getSupportFragmentManager());
            viewPager.setAdapter(viewPageAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.getTabAt(0).setIcon(R.drawable.logokhongnen);
            tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
            tabLayout.getTabAt(2).setIcon(R.drawable.timdo_bar);
            tabLayout.getTabAt(3).setIcon(R.drawable.xe_hang);


        }



}