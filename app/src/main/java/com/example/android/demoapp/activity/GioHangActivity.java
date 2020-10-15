package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.GioHangAdapter;

public class GioHangActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static TextView tvTongtien;
    RecyclerView giohangRecyclerView;
    GioHangAdapter gioAdapter;
    Button buttonDatHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gio_hang_activity);
        toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();

            }
        });

    /*    giohangRecyclerView = findViewById(R.id.recycler_view_gio_hang_2);
        giohangRecyclerView.setHasFixedSize(true);
        giohangRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gioAdapter = new GioHangAdapter(this, MainActivity.mangGioHang);

        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(giohangRecyclerView);
        giohangRecyclerView.setAdapter(gioAdapter);

        tvTongtien = findViewById(R.id.so_tien_2);
        buttonDatHang = findViewById(R.id.button_dat_hang_2);
        eventUtil();



        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.mangGioHang.size() > 0) {
                    Intent intent = new Intent(GioHangActivity.this, DatHangActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(GioHangActivity.this, "Bạn chưa có sản phẩm nào trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
   */

    }






        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_gio_hang, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.icon_tim_kiem:

                    Intent intentTimKiem = new Intent(this, TimKiemActivity.class);
                    startActivity(intentTimKiem);
                    break;



                case R.id.icon_yeu_thich:
                    Intent intentYeuThich = new Intent(this, YeuthichActivity.class);
                    startActivity(intentYeuThich);


                break;
            }



            return super.onOptionsItemSelected(item);


        }




    }








