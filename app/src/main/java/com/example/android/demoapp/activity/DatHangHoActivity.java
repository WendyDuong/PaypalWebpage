package com.example.android.demoapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.DatHangHoAdapter;
import com.example.android.demoapp.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class DatHangHoActivity extends AppCompatActivity {

    RecyclerView recyclerDatHangHo;

    public static final List<Integer> ImageList = new ArrayList<Integer>() {{
        add(R.drawable.logo_adidas);
        add(R.drawable.logo_nike);
        add(R.drawable.logo_amazon);
        add(R.drawable.logo_bershka);
        add(R.drawable.logo_ca);
        add(R.drawable.logo_esprit);
        add(R.drawable.logo_hm);
        add(R.drawable.logo_mango);
        add(R.drawable.logo_massimodutti);
        add(R.drawable.logo_zara);
        add(R.drawable.logo_pb);
        add(R.drawable.logo_douglas);
        add(R.drawable.logo_wmf);
        add(R.drawable.logo_silit);
        add(R.drawable.muller);
        add(R.drawable.logo_dm);
        add(R.drawable.logo_saturn);
        add(R.drawable.apotheke_logo);
        add(R.drawable.rossmann_logo);
        add(R.drawable.worldofsweet);
        add(R.drawable.mediamarkt_logo);
    }};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_tra);
        recyclerDatHangHo = findViewById(R.id.recycler_view_dathangho);

        DatHangHoAdapter datHangHoAdapter = new DatHangHoAdapter(DatHangHoActivity.this, ImageList);
        recyclerDatHangHo.setHasFixedSize(true);
        recyclerDatHangHo.setLayoutManager(new GridLayoutManager(DatHangHoActivity.this,3));
        recyclerDatHangHo.setAdapter(datHangHoAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        TextView tv = findViewById(R.id.tv_doi_tra);
        tv.setText(R.string.dat_hang_ho);




    }
}
