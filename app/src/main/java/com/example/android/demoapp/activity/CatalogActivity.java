package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.CatalogAdapter;
import com.example.android.demoapp.model.Sanpham;
import com.example.android.demoapp.ultil.Server;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {
    ArrayList<Sanpham> mangSanpham;
    RecyclerView recyclerView;
    CatalogAdapter sanphamAdapter;
    ImageView imageViewNhaCungCap;
    int idnhacungcap;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_activity);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.iconhome));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icontimkiem));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));
        imageViewNhaCungCap = findViewById(R.id.image_view_nha_cung_cap);
        mangSanpham = new ArrayList<>();
        sanphamAdapter = new CatalogAdapter(CatalogActivity.this, mangSanpham);
        recyclerView = findViewById(R.id.recycler_view_catalog);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(CatalogActivity.this, 2));
        recyclerView.setAdapter(sanphamAdapter);
        getData();
        switch (idnhacungcap){
            case 0:
                imageViewNhaCungCap.setImageResource(R.drawable.hit);
                break;
            case 1:
                imageViewNhaCungCap.setImageResource(R.drawable.merc);
                break;
            case 2:
                imageViewNhaCungCap.setImageResource(R.drawable.penny);
                break;
            case 3:
                imageViewNhaCungCap.setImageResource(R.drawable.muller);
                break;
            case 4:
                imageViewNhaCungCap.setImageResource(R.drawable.dell);
                break;
            case 5:
                imageViewNhaCungCap.setImageResource(R.drawable.audi);
                break;
            case 6:
                imageViewNhaCungCap.setImageResource(R.drawable.dior);
                break;
            case 7:
                imageViewNhaCungCap.setImageResource(R.drawable.skii);
                break;
            case 8:
                imageViewNhaCungCap.setImageResource(R.drawable.richy);
                break;
           }


            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(CatalogActivity.this, MainActivity.class);
                        CatalogActivity.this.startActivity(intent0);
                        break;

                    case 1 :
                        Intent intent1 = new Intent(CatalogActivity.this, TimKiemActivity.class);
                        CatalogActivity.this.startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(CatalogActivity.this, YeuthichActivity.class);
                        CatalogActivity.this.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(CatalogActivity.this, GioHangActivity.class);
                        CatalogActivity.this.startActivity(intent3);
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
                        Intent intent0 = new Intent(CatalogActivity.this, MainActivity.class);
                        CatalogActivity.this.startActivity(intent0);
                        break;
                    case 1 :
                        Intent intent1 = new Intent(CatalogActivity.this, TimKiemActivity.class);
                        CatalogActivity.this.startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(CatalogActivity.this, YeuthichActivity.class);
                        CatalogActivity.this.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(CatalogActivity.this, GioHangActivity.class);
                        CatalogActivity.this.startActivity(intent3);
                        break;

                    default:
                        break;
                }

            }
        });
    }
    @Override
    protected void onStart() {
       sanphamAdapter.notifyDataSetChanged();
        super.onStart();
    }


    private void getData() {
      idnhacungcap = getIntent().getIntExtra("idnhacungcap", 0);
        String duongdan;
        switch (idnhacungcap){
            case 0 :
                duongdan = Server.duongdansanphamhit;
                break;
            case 1 :
                duongdan = Server.duongdansanphammerc;
                break;
            case 2:
                duongdan = Server.duongdansanphampenny;
                break;
            case 3:
                duongdan = Server.duongdansanphammuller;
                break;
            case 4:
                duongdan = Server.duongdansanphamdell;
                break;
            case 5:
                duongdan = Server.duongdansanphamaudi;
                break;
            case 6:
                duongdan = Server.duongdansanphamdior;
                break;
            case 7:
                duongdan = Server.duongdansanphamskii;
                break;

            case 8:
                duongdan = Server.duongdansanphamrichy;
                break;
            default:
                duongdan = Server.duongdansanphampenny;
                break;
        }
        RequestQueue requestQueue= Volley.newRequestQueue(CatalogActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(duongdan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                            JSONObject object = response.getJSONObject(i);
                            mangSanpham.add(new Sanpham(object.getInt("id"),
                                    object.getString("tensanpham"),
                                    object.getInt("giasanpham"),
                                    object.getString("hinhanhsanpham"),
                                    object.getString("motasanpham"),
                                    object.getInt("idnhacungcap"),
                                    object.getString("khoiluongsanpham"),
                                    object.getInt("yeuthich")));
                                    sanphamAdapter.notifyDataSetChanged();
                        Log.d("kiemtra", "da vao thanh cong");


                        }
                    catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(CatalogActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("kiemtra", error.toString());
                Toast.makeText(CatalogActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }


}
