package com.example.android.demoapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.android.demoapp.utils.CheckConnection;
import com.example.android.demoapp.utils.Server;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    public static final String EXTRA_HANG_ID = "extraHangId";
    private static final String TAG = CatalogActivity.class.getSimpleName();
    private static final String EXTRA_ANH_HANG = "extraAnhHang";
    private static final int DEFAULT_HANG_ID = -1;
    public static ArrayList<SanPham> sanPhams;
    public static BadgeDrawable badgeDrawableYeuthich;
    public static BadgeDrawable badgeDrawableGioHang;
    RecyclerView recyclerView;
    CatalogAdapter catalogAdapter;
    ProgressBar mProgressBar;
    mHandler mHandler;
    ImageView imageViewNhaCungCap;
    TabLayout tabLayout;
    TabLayout.Tab tabGioHang;
    TabLayout.Tab tabYeuThich;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;
    GridLayoutManager gridLayoutManager;
    private AppDatabase mDb;
    private int idHang;
    private String anhHang;

    private boolean loading = false;
    private boolean limitData = false;
    private int page = 1;
    private int visibleItemCount, totalItemCount, pastVisiblesItems;
    String language;
    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getViewModelStore().clear();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.settings_language_key))){

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

            language = sharedPrefs.getString(
                    getString(R.string.settings_language_key),
                    getString(R.string.settings_language_default)
            );

        }
    }


    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_activity);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        language = sharedPrefs.getString(
                getString(R.string.settings_language_key),
                getString(R.string.settings_language_default)
        );
        populateUI();
        actionToolbar();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_HANG_ID);
            anhHang = MainFragment.manghangsanpham.get(idHang).getAnhHang();
            Picasso.get().load(anhHang).error(R.drawable.error).into(imageViewNhaCungCap);
        }

        //Load data from server in sanphams array
        if (CheckConnection.haveNetworkConnection(CatalogActivity.this)) {
            getData(page);
            loadMoreData();
            catalogAdapter = new CatalogAdapter(CatalogActivity.this, sanPhams, language);
            recyclerView.setAdapter(catalogAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);


        } else {
            CheckConnection.showToast_Short(CatalogActivity.this, "Không có kết nối Internet!");
            CatalogActivity.this.finish();

        }

        final YeuThichViewModel viewModel1
                = ViewModelProviders.of(this).get(YeuThichViewModel.class);

        viewModel1.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
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

        viewModel1.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
            @Override
            public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                yeuThichEntries = yeuThich;
                catalogAdapter.setYeuThichs(yeuThich);
                assert yeuThichEntries != null;
                if (yeuThichEntries.size() > 0) {
                    badgeDrawableYeuthich.setVisible(true);
                    badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                } else
                    badgeDrawableYeuthich.setVisible(false);
            }
        });


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

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && totalItemCount != 0 && loading == false && limitData == false) {
                        loading = true;
                        ThreadData threadData = new ThreadData();
                        threadData.start();
                    }
                }
            }
        });
    }

    private void actionToolbar() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.iconhome));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kinh_lup_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));
        imageViewNhaCungCap = findViewById(R.id.image_view_nha_cung_cap);

        tabYeuThich = tabLayout.getTabAt(2);
        tabGioHang = tabLayout.getTabAt(3);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(CatalogActivity.this, MainActivity.class);
                        CatalogActivity.this.startActivity(intent0);
                        break;

                    case 1:
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
                    case 1:
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

        assert tabGioHang != null;
        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        assert tabYeuThich != null;
        badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();
        badgeDrawableGioHang.setMaxCharacterCount(3);
        badgeDrawableYeuthich.setMaxCharacterCount(3);


    }

    private void populateUI() {
        sanPhams = new ArrayList<SanPham>();
        mDb = AppDatabase.getInstance(getApplicationContext());
        mProgressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view_catalog);

        recyclerView.setHasFixedSize(true);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720) {
            gridLayoutManager = new GridLayoutManager(CatalogActivity.this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            gridLayoutManager = new GridLayoutManager(CatalogActivity.this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }


        mHandler = new mHandler();

    }

    private void getData(final int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(CatalogActivity.this);
        String duongdan;
        duongdan = Server.duongdansanphamhang + page + "&idHang=" + idHang;

        StringRequest str = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() != 4) {
                    // TODO  recyclerView.removeFooterView(footerview)
                    mProgressBar.setVisibility(View.GONE);
                    try {
                        JSONArray json = new JSONArray(response);
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject object = json.getJSONObject(i);
                            final double giakhuyenmai = object.getDouble("giaKhuyenMai");
                            sanPhams.add(new SanPham(object.getInt("id"), object.getInt("idHang"), object.getString("tenSanPham"), object.getDouble("giaSanPham"), giakhuyenmai, object.getString("hinhAnhSanPham"), object.getString("khoiLuong"), object.getString("moTa"), object.getString("thuongHieu"), object.getString("xuatXu"), object.getString("tenSanPhamDE"),object.getString("moTaDE"), object.getInt("idShopBan")));
                            catalogAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catalogAdapter.notifyDataSetChanged();


                } else {
                    limitData = true;
                    mProgressBar.setVisibility(View.GONE);
                    CheckConnection.showToast_Short(getApplicationContext(), "Đã hết dữ liệu");

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(str);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            sanPhams.clear();
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_HANG_ID);
            anhHang = MainFragment.manghangsanpham.get(idHang).getAnhHang();
            Picasso.get().load(anhHang).error(R.drawable.error).into(imageViewNhaCungCap);
            getData(page);
            loadMoreData();


        }


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
                catalogAdapter.setYeuThichs(yeuThichEntries);
                catalogAdapter.notifyDataSetChanged();
            }
        });
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    // TODO  recyclerView.addFooterView(footerview)
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    getData(++page);
                    loading = false;
                    break;

            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread {
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
}

