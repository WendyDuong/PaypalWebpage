package com.example.android.demoapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.example.android.demoapp.model.SanPham;
import com.example.android.demoapp.utils.Server;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FindActivity extends AppCompatActivity {
    private static final String EXTRA_TEN_SAN_PHAM_TIM_KIEM = "tensanphamtimkiem";
    String mTenSanPham;
    private AppDatabase mDb;
    TextView emptyTv;
    Toolbar toolbar;
    private TabLayout tabLayout;
    private TabLayout.Tab tabGioHang;
    private TabLayout.Tab tabYeuThich;
    CatalogAdapter timKiemAdapter;
    RecyclerView recyclerView;
    public static BadgeDrawable badgeDrawableGioHang;
    public static BadgeDrawable badgeDrawableYeuthich;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;
    ArrayList<SanPham> sanPhams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        emptyTv = findViewById(R.id.find_empty_tv);
        toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        tabLayout = findViewById(R.id.tab_layout_tim_do);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kinh_lup_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));
        tabYeuThich = tabLayout.getTabAt(1);
        tabGioHang = tabLayout.getTabAt(2);
        assert tabGioHang != null;
        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        assert tabYeuThich != null;
        badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();
        badgeDrawableGioHang.setMaxCharacterCount(3);
        badgeDrawableYeuthich.setMaxCharacterCount(3);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(FindActivity.this, TimKiemActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(FindActivity.this, YeuthichActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(FindActivity.this, GioHangActivity.class);
                        startActivity(intent2);
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
                        Intent intent0 = new Intent(FindActivity.this, TimKiemActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(FindActivity.this, YeuthichActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(FindActivity.this, GioHangActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }

        });

        sanPhams = new ArrayList<>();
        mDb = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TEN_SAN_PHAM_TIM_KIEM)) {

            mTenSanPham = intent.getStringExtra(EXTRA_TEN_SAN_PHAM_TIM_KIEM);
            String BarTitle;
            assert mTenSanPham != null;
            if (mTenSanPham.length() > 8) {
                BarTitle = mTenSanPham.substring(0, 9) + "...";
            } else {
                BarTitle = mTenSanPham;
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle(BarTitle);
            getsanphamtheoten(mTenSanPham);


            final YeuThichViewModel viewModel
                    = ViewModelProviders.of(this).get(YeuThichViewModel.class);


            viewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                @Override
                public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                    gioHangEntries = gioHang;
                    int sosanphammua = 0;
                    assert gioHangEntries != null;
                    if (gioHangEntries.size() > 0) {
                        for (int i = 0; i < gioHangEntries.size(); i++) {
                            sosanphammua += gioHangEntries.get(i).getSoLuong();
                        }
                        badgeDrawableGioHang.setVisible(true);

                        badgeDrawableGioHang.setNumber(sosanphammua);
                    } else
                        badgeDrawableGioHang.setVisible(false);
                }
            });

            viewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                @Override
                public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                    yeuThichEntries = yeuThich;
                    timKiemAdapter.setYeuThichs(yeuThich);
                    assert yeuThichEntries != null;
                    if (yeuThichEntries.size() > 0) {
                        badgeDrawableYeuthich.setVisible(true);
                        badgeDrawableYeuthich.setNumber(yeuThichEntries.size());
                    } else
                        badgeDrawableYeuthich.setVisible(false);
                }
            });

        }

        timKiemAdapter = new CatalogAdapter(FindActivity.this, sanPhams);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_tim_do);
        recyclerView.setHasFixedSize(true);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720) {
            recyclerView.setLayoutManager(new GridLayoutManager(FindActivity.this, 3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(FindActivity.this, 2));
        }
        recyclerView.setAdapter(timKiemAdapter);

    }

    private void getsanphamtheoten(String tenSanPham) {
        RequestQueue requestQueue= Volley.newRequestQueue(FindActivity.this);
        final String duongdan = Server.duongdansanphamtheoten+tenSanPham;
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
                            timKiemAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                Toast.makeText(FindActivity.this, "sanphamSize: "+sanPhams.size(),Toast.LENGTH_SHORT).show();

                if (sanPhams.size() > 0)
                    emptyTv.setVisibility(View.INVISIBLE);
                else
                    emptyTv.setVisibility(View.VISIBLE);

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
                param.put(EXTRA_TEN_SAN_PHAM_TIM_KIEM, EXTRA_TEN_SAN_PHAM_TIM_KIEM);
                return param;
            }
        }
                ;
        requestQueue.add(str);

    }

    @Override
    protected void onRestart() {
        timKiemAdapter.notifyDataSetChanged();
        super.onRestart();
    }
}