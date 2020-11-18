package com.example.android.demoapp.activity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.DetailViewModel;
import com.example.android.demoapp.ViewModel.DetailViewModelFactory;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.SanPhamEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.github.chrisbanes.photoview.PhotoView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import org.apache.commons.math3.util.Precision;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    ImageView timImageView, imageViewHangSp;
    private static final int DEFAULT_ID = -1;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";
    private int idHang, idsanpham;
    DetailViewModelFactory factory;
    DetailViewModel viewModel;
    Toolbar toolBarChiTietActivity;
    TextView tvMoTaTitle, tvChiTietTitle, devider1, devider2;
    View cardViewSpinner;
    PhotoView imgChiTiet;
    private AppDatabase mDb;
    TextView tvTen, tvGia, tvMoTa, tvKhoiluong, tvThuongHieu, tvXuatXu;
    ExtendedFloatingActionButton btnDatMua;
    int  hinhanhsp;
    double giasp;
    String tensp, khoiluongsp, thuongHieu, xuatXu;
    private int mTaskId = DEFAULT_ID;
    Spinner spinner;
    TabLayout tabLayout;
    TabLayout.Tab tabGioHang;
    TabLayout.Tab tabYeuThich;
    BadgeDrawable badgeDrawableYeuthich;
    BadgeDrawable badgeDrawableGioHang;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        mDb = AppDatabase.getInstance(getApplicationContext());
        imgChiTiet = findViewById(R.id.image_view_chi_tiet);
        tvTen = findViewById(R.id.ten_san_pham);
        tvKhoiluong = findViewById(R.id.khoi_luong_san_pham);
        tvGia = findViewById(R.id.gia_san_pham);
        tvMoTa = findViewById(R.id.mo_ta_san_pham);
        tvThuongHieu = findViewById(R.id.san_pham_thuong_hieu);
        tvXuatXu = findViewById(R.id.san_pham_xuat_xu);
        btnDatMua = findViewById(R.id.button_mua);
        tabLayout = findViewById(R.id.tab_layout3);
        spinner = findViewById(R.id.spinner);
        timImageView = findViewById(R.id.tim_chi_tiet_activity);
        imageViewHangSp = findViewById(R.id.iv_hang);
        toolBarChiTietActivity = findViewById(R.id.toolbar3);
        tvMoTaTitle = findViewById(R.id.mo_ta_san_pham_tilte);
        tvChiTietTitle = findViewById(R.id.chi_tiet_san_pham_tilte);
        devider1 = findViewById(R.id.devider);
        devider2 = findViewById(R.id.devider2);
        cardViewSpinner = findViewById(R.id.card_view_spinner);
        imgChiTiet.setClipToOutline(true);


        eventSpinner();
        yeuthichEvent();



        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_ID);
            switch (idHang) {
                case 0:
                    imageViewHangSp.setImageResource(R.drawable.logo_wmf);
                    break;
                case 1:
                    imageViewHangSp.setImageResource(R.drawable.logo_silit);
                    break;
                case 2:
                    imageViewHangSp.setImageResource(R.drawable.muller);
                    break;
                case 3:
                    imageViewHangSp.setImageResource(R.drawable.logo_dm);
                    break;
                case 4:
                    imageViewHangSp.setImageResource(R.drawable.logo_saturn);
                    break;
                case 5:
                    imageViewHangSp.setImageResource(R.drawable.apotheke_logo);
                    break;
                case 6:
                    imageViewHangSp.setImageResource(R.drawable.rossmann_logo);
                    break;
                case 7:
                    imageViewHangSp.setImageResource(R.drawable.worldofsweet);
                    break;
                case 8:
                    imageViewHangSp.setImageResource(R.drawable.mediamarkt_logo);
                    break;
            }
        }
        imageViewHangSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent0 = new Intent(DetailActivity.this, CatalogActivity.class);
                intent0.putExtra(DetailActivity.EXTRA_HANG_ID, idHang);
                DetailActivity.this.startActivity(intent0);
            }
        });
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kinh_lup_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent1 = new Intent(DetailActivity.this, TimKiemActivity.class);
                        DetailActivity.this.startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(DetailActivity.this, YeuthichActivity.class);
                        DetailActivity.this.startActivity(intent2);
                        break;

                    case 2:
                        Intent intent3 = new Intent(DetailActivity.this, GioHangActivity.class);
                        DetailActivity.this.startActivity(intent3);
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
                        Intent intent1 = new Intent(DetailActivity.this, TimKiemActivity.class);
                        DetailActivity.this.startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(DetailActivity.this, YeuthichActivity.class);
                        DetailActivity.this.startActivity(intent2);
                        break;

                    case 2:
                        Intent intent3 = new Intent(DetailActivity.this, GioHangActivity.class);
                        DetailActivity.this.startActivity(intent3);
                        break;

                    default:
                        break;
                }
            }


        });

        tabYeuThich = tabLayout.getTabAt(1);
        tabGioHang = tabLayout.getTabAt(2);

        assert tabGioHang != null;
        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        assert tabYeuThich != null;
        badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();
        badgeDrawableGioHang.setMaxCharacterCount(3);
        badgeDrawableYeuthich.setMaxCharacterCount(3);

        if (intent != null && intent.hasExtra(EXTRA_SANPHAM_ID)) {
            if (mTaskId == DEFAULT_ID) {
                mTaskId = intent.getIntExtra(EXTRA_SANPHAM_ID, DEFAULT_ID);
                factory = new DetailViewModelFactory(mDb, mTaskId);
                viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);
                viewModel.getDetailSanPham().observe(this, new Observer<SanPhamEntry>() {
                    @Override
                    public void onChanged(SanPhamEntry sanPham) {
                        populateUI(sanPham);
                    }

                });

                viewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                    @Override
                    public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                        gioHangEntries = gioHang;
                        int sosanphammua = 0;
                        addEvent();
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
            }
        }


 /*       imgChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomImageFromThumb(imgChiTiet, hinhanhsp);
            }
        });
        shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
*/
    }



    private void yeuthichEvent() {
        timImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                if (timImageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.timden24).getConstantState()) {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.yeuThichDao().insertYeuThich(new YeuThichEntry(idsanpham, tensp, giasp, hinhanhsp, khoiluongsp, idHang));

                        }
                    });
                } else {
                    for (int vitrixoa = 0; vitrixoa < yeuThichEntries.size(); vitrixoa++) {
                        int idsanphamxoa = yeuThichEntries.get(vitrixoa).getIdSanPham();
                        if (idsanphamxoa == idsanpham) {
                            final int finalVitrixoa = vitrixoa;
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.yeuThichDao().deleteYeuThich(yeuThichEntries.get(finalVitrixoa));
                                }
                            });
                        }
                    }
                }
            }
        });
    }


    private void populateUI(SanPhamEntry sanPham) {
        idsanpham = sanPham.getId();
        tensp = sanPham.getTenSanPham();
        hinhanhsp = sanPham.getHinhAnh();
        khoiluongsp = sanPham.getKhoiLuong();
        thuongHieu = sanPham.getThuongHieu();
        xuatXu = sanPham.getXuatXu();

        //Rounding currency to make a easy reading
        giasp = sanPham.getGiaSanPham();
        giasp = Precision.round(giasp/1000, 0)*1000;

        tvTen.setText(tensp);
        tvMoTa.setText(sanPham.getMoTa());
        tvKhoiluong.setText("Chi tiết: " + khoiluongsp);
        tvThuongHieu.setText("Thương hiệu: " + thuongHieu);
        tvXuatXu.setText("Xuất xứ: " + xuatXu);
        DecimalFormat deci = new DecimalFormat("###,###,###");
        tvGia.setText(deci.format(giasp) + " Đ");
        imgChiTiet.setImageResource(hinhanhsp);

        viewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
            @Override
            public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                yeuThichEntries = yeuThich;
                KiemTraYeuThich();
                if (yeuThichEntries.size() > 0) {
                    badgeDrawableYeuthich.setVisible(true);
                    badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                } else
                    badgeDrawableYeuthich.setVisible(false);


            }

        });
    }

    private void addEvent() {
        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gioHangEntries.size() > 0) {
                    final int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exsist = false;
                    for (int i = 0; i < gioHangEntries.size(); i++) {

                        if (gioHangEntries.get(i).getIdSanPham() == idsanpham) {
                            final int soluongcu = gioHangEntries.get(i).getSoLuong();
                            final int id = gioHangEntries.get(i).getId();
                            final int soluongmoi = soluongcu + sl;

                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensp, Precision.round((giasp * soluongmoi)/1000,0)*1000, hinhanhsp, khoiluongsp, soluongmoi, idHang));


                                }
                            });


                            if (soluongmoi >= 50) {


                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensp, Precision.round((giasp * 20)/1000,0)*1000, hinhanhsp, khoiluongsp, 50, idHang));


                                    }
                                });

                                Toast.makeText(DetailActivity.this, "Đã đủ 50 "+ tensp + " trong giỏ hàng", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(DetailActivity.this, "Đã thêm " + soluongmoi + " " + tensp + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            }


                            exsist = true;


                        }
                    }
                    if (!exsist) {
                        final int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        final double giamoi = soluong * giasp;

                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanpham, tensp,Precision.round((giamoi)/1000,0)*1000 , hinhanhsp, khoiluongsp, soluong, idHang));


                            }
                        });

                        Toast.makeText(DetailActivity.this, "Đã thêm " + soluong + " " + tensp + " vào giỏ hàng", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    final int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    final double giamoi = soluong * giasp;


                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanpham, tensp,Precision.round((giamoi)/1000,0)*1000 , hinhanhsp, khoiluongsp, soluong, idHang));


                        }
                    });
                    Toast.makeText(DetailActivity.this, "Đã thêm " + soluong + " " + tensp + " vào giỏ hàng", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

    private void eventSpinner() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            arr.add(i);
        }
        ArrayAdapter spinnerAdapter = new ArrayAdapter(DetailActivity.this, R.layout.spinner_item_custom, arr);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


    }

    private void KiemTraYeuThich() {
        boolean exit = false;

        if (yeuThichEntries.size() > 0) {
            for (int vitritim = 0; vitritim < yeuThichEntries.size(); vitritim++) {
                if (yeuThichEntries.get(vitritim).getIdSanPham() == idsanpham) {
                    timImageView.setImageResource(R.drawable.timdo24);

                    exit = true;
                }
            }

            if (!exit) {

                timImageView.setImageResource(R.drawable.timden24);
            }

        } else {

            timImageView.setImageResource(R.drawable.timden24);
        }

    }

    @Override
    protected void onStop() {
        getViewModelStore().clear();
        super.onStop();

    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_ID);
            switch (idHang) {
                case 0:
                    imageViewHangSp.setImageResource(R.drawable.logo_wmf);
                    break;
                case 1:
                    imageViewHangSp.setImageResource(R.drawable.logo_silit);
                    break;
                case 2:
                    imageViewHangSp.setImageResource(R.drawable.muller);
                    break;
                case 3:
                    imageViewHangSp.setImageResource(R.drawable.logo_dm);
                    break;
                case 4:
                    imageViewHangSp.setImageResource(R.drawable.logo_saturn);
                    break;
                case 5:
                    imageViewHangSp.setImageResource(R.drawable.apotheke_logo);
                    break;
                case 6:
                    imageViewHangSp.setImageResource(R.drawable.rossmann_logo);
                    break;
                case 7:
                    imageViewHangSp.setImageResource(R.drawable.worldofsweet);
                    break;
                case 8:
                    imageViewHangSp.setImageResource(R.drawable.mediamarkt_logo);
                    break;
            }
        }
        if (intent != null && intent.hasExtra(EXTRA_SANPHAM_ID)) {
            mTaskId = intent.getIntExtra(EXTRA_SANPHAM_ID, DEFAULT_ID);
            factory = new DetailViewModelFactory(mDb, mTaskId);
            viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);
            viewModel.getDetailSanPham().observe(this, new Observer<SanPhamEntry>() {
                @Override
                public void onChanged(SanPhamEntry sanPham) {
                    populateUI(sanPham);
                }

            });

        }
        super.onNewIntent(intent);
    }

}




