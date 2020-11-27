package com.example.android.demoapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.YeuThichViewModel;
import com.example.android.demoapp.adapter.CatalogAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.example.android.demoapp.fragment.MainFragment;
import com.example.android.demoapp.model.SanPham;
import com.example.android.demoapp.utils.Server;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CatalogAdapter catalogAdapter;
    private AppDatabase mDb;
    private int idHang;
    private String anhHang;
    private static final String TAG = CatalogActivity.class.getSimpleName();
    public static final String EXTRA_HANG_ID = "extraHangId";
    private static final String EXTRA_ANH_HANG = "extraAnhHang";

    private static final int DEFAULT_HANG_ID = -1;
    ImageView imageViewNhaCungCap;
    TabLayout tabLayout;
    TabLayout.Tab tabGioHang;
    TabLayout.Tab tabYeuThich;
    public static ArrayList<SanPham> sanPhams;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;
    public static BadgeDrawable badgeDrawableYeuthich;
    public static BadgeDrawable badgeDrawableGioHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sanPhams = new ArrayList<SanPham>();
        setContentView(R.layout.catalog_activity);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.iconhome));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kinh_lup_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));
        imageViewNhaCungCap = findViewById(R.id.image_view_nha_cung_cap);

        tabYeuThich = tabLayout.getTabAt(2);
        tabGioHang = tabLayout.getTabAt(3);


        assert tabGioHang != null;
        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        assert tabYeuThich != null;
        badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();
        badgeDrawableGioHang.setMaxCharacterCount(3);
        badgeDrawableYeuthich.setMaxCharacterCount(3);
        mDb = AppDatabase.getInstance(getApplicationContext());


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {

            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_HANG_ID);
            anhHang = MainFragment.ImageList.get(idHang);
            Picasso.get().load(anhHang).error(R.drawable.error).into(imageViewNhaCungCap);

            //Load data from server in sanphams array
            getData();
            catalogAdapter = new CatalogAdapter(CatalogActivity.this, sanPhams);


            final YeuThichViewModel viewModel1
                    = ViewModelProviders.of(this).get(YeuThichViewModel.class);

            viewModel1.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                @Override
                public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                    gioHangEntries = gioHang;
                    int sosanphammua = 0;
                    assert gioHangEntries != null;
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

            viewModel1.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                @Override
                public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                    yeuThichEntries = yeuThich;
                    catalogAdapter.setYeuThichs(yeuThich);
                    assert yeuThichEntries != null;
                    if (yeuThichEntries.size()>0){
                        badgeDrawableYeuthich.setVisible(true);
                        badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                    }
                    else
                        badgeDrawableYeuthich.setVisible(false);
                }
            });
        }


        recyclerView = findViewById(R.id.recycler_view_catalog);
        recyclerView.setHasFixedSize(true);

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720)
        {
            recyclerView.setLayoutManager(new GridLayoutManager(CatalogActivity.this, 3));
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(CatalogActivity.this, 2));
        }
        recyclerView.setAdapter(catalogAdapter);


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


    private void getData() {
        RequestQueue requestQueue= Volley.newRequestQueue(CatalogActivity.this);
        String duongdan;
        switch (idHang){
            case 0:
                duongdan = Server.duongdansanphamhang0;
                break;
            case 1:
                duongdan = Server.duongdansanphamhang1;
                break;
            case 2:
                duongdan = Server.duongdansanphamhang2;
                break;
            case 3:
                duongdan = Server.duongdansanphamhang3;
                break;
            case 4:
                duongdan = Server.duongdansanphamhang4;
                break;
            case 5:
                duongdan = Server.duongdansanphamhang5;
                break;
            case 6:
                duongdan = Server.duongdansanphamhang6;
                break;
            case 7:
                duongdan = Server.duongdansanphamhang7;
                break;
            default:
                duongdan = Server.duongdansanphamhang8;
                break;
        }
        StringRequest str = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null){
                    try {
                        JSONArray json=new JSONArray(response);
                        int dodai = json.length();
                        for(int i = 0; i < json.length(); i++){
                            JSONObject object=json.getJSONObject(i);
                            sanPhams.add(new SanPham(object.getInt("id"),object.getInt("idHang"),object.getString("tenSanPham"),object.getDouble("giaSanPham"),object.getString("hinhAnhSanPham"),object.getString("khoiLuong"),object.getString("moTa"),object.getString("thuongHieu"),object.getString("xuatXu")));

                            catalogAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put(EXTRA_HANG_ID, String.valueOf(idHang));
                return param;
            }
        }
                ;
        requestQueue.add(str);
    }

    @Override
    protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            setIntent(intent);

            intent = getIntent();
            if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
                idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_HANG_ID);
                anhHang = MainFragment.ImageList.get(idHang);
                Picasso.get().load(anhHang).error(R.drawable.error).into(imageViewNhaCungCap);


                final YeuThichViewModel viewModel1
                        = ViewModelProviders.of(this).get(YeuThichViewModel.class);

                viewModel1.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                    @Override
                    public void onChanged(List<YeuThichEntry> yeuThichEntries) {
                        catalogAdapter.setYeuThichs(yeuThichEntries);
                    }
                });
            }


        }


    @Override
    protected void onStop() {
        super.onStop();
        getViewModelStore().clear();
    }
}