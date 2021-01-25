package com.example.android.demoapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.android.demoapp.adapter.FindAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.example.android.demoapp.model.SanPham;
import com.example.android.demoapp.utils.CheckConnection;
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

    ProgressBar mProgressBar;
    private boolean loading = false;
    private boolean limitData = false;
    mHandler mHandler;
    private int page = 1;
    private int visibleItemCount, totalItemCount, pastVisiblesItems;
    GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        populateUI();
        actionToolbar();


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TEN_SAN_PHAM_TIM_KIEM)) {

            mTenSanPham = intent.getStringExtra(EXTRA_TEN_SAN_PHAM_TIM_KIEM);
            String BarTitle;
            assert mTenSanPham != null;
            if (mTenSanPham.length() > 7) {
                BarTitle = mTenSanPham.substring(0, 8) + "...";
            } else {
                BarTitle = mTenSanPham;
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle(BarTitle);
            if (CheckConnection.haveNetworkConnection(FindActivity.this)) {
                getsanphamtheoten(mTenSanPham, page);
                loadMoreData();
                recyclerView.setAdapter(timKiemAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);


            } else {
                CheckConnection.showToast_Short(FindActivity.this, "Không có kết nối Internet!");
                FindActivity.this.finish();

            }
        }


    }
    private void loadMoreData() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    //Scrolling down
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && totalItemCount != 0 && loading == false && limitData== false) {

                        loading = true;
                        FindActivity.ThreadData threadData = new FindActivity.ThreadData();
                        threadData.start();
                    }
                }
            }
        });
    }


    private void actionToolbar() {
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
    }

    private void populateUI() {
        emptyTv = findViewById(R.id.find_empty_tv);
        sanPhams = new ArrayList<>();
        mDb = AppDatabase.getInstance(getApplicationContext());

        timKiemAdapter = new CatalogAdapter(FindActivity.this, sanPhams);
        mProgressBar = findViewById(R.id.progress_bar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_tim_do);
        recyclerView.setHasFixedSize(true);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720) {
            gridLayoutManager = new GridLayoutManager(FindActivity.this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            gridLayoutManager = new GridLayoutManager(FindActivity.this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

        //ViewModel
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
        //End of ViewModel
        mHandler = new FindActivity.mHandler();


    }

    private void getsanphamtheoten(String tenSanPham, final int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(FindActivity.this);
        final String duongdan = Server.duongdansanphamtheoten + tenSanPham +"&page=" + page;
        StringRequest str = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length()!=2) {
                    mProgressBar.setVisibility(View.GONE);
                    try {
                        JSONArray json = new JSONArray(response);
                        int dodai = json.length();
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject object = json.getJSONObject(i);
                            //TODO SALE
                            sanPhams.add(new SanPham(object.getInt("id"), object.getInt("idHang"), object.getString("tenSanPham"), object.getDouble("giaSanPham"),object.getDouble("giaKhuyenMai"), object.getString("hinhAnhSanPham"), object.getString("khoiLuong"), object.getString("moTa"), object.getString("thuongHieu"), object.getString("xuatXu")));
                            timKiemAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (sanPhams.size() > 0){
                        emptyTv.setVisibility(View.INVISIBLE);
                    }
                    else{
                        emptyTv.setVisibility(View.VISIBLE);}
                }else{
                    limitData = true;
                    mProgressBar.setVisibility(View.GONE);
                    CheckConnection.showToast_Short(getApplicationContext(),"Đã hết dữ liệu");

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put(EXTRA_TEN_SAN_PHAM_TIM_KIEM, EXTRA_TEN_SAN_PHAM_TIM_KIEM);
                return param;
            }
        };
        requestQueue.add(str);

    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    // TODO  recyclerView.addFooterView(footerview)
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    getsanphamtheoten(mTenSanPham,++page);
                    loading = false;
                    break;

            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);

            super.run();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        getViewModelStore().clear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        final YeuThichViewModel viewModel1
                = ViewModelProviders.of(this).get(YeuThichViewModel.class);

        viewModel1.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
            @Override
            public void onChanged(List<YeuThichEntry> yeuThichEntries) {
                viewModel1.getYeuThich().removeObserver(this);
                timKiemAdapter.setYeuThichs(yeuThichEntries);
                timKiemAdapter.notifyDataSetChanged();
            }
        });
    }
}