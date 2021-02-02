package com.example.android.demoapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.android.demoapp.ViewModel.YeuThichViewModel;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangDao;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.example.android.demoapp.fragment.MainFragment;
import com.example.android.demoapp.model.SanPham;
import com.example.android.demoapp.utils.CheckConnection;
import com.github.chrisbanes.photoview.PhotoView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.apache.commons.math3.util.Precision;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    ImageView timImageView, imageViewHangSp;
    private static final int DEFAULT_ID = -1;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";

    private int idHang;
    YeuThichViewModel viewModel;
    Toolbar toolBarChiTietActivity;
    TextView tvMoTaTitle, tvChiTietTitle, devider1, devider2;
    View cardViewSpinner;
    PhotoView imgChiTiet;
    private AppDatabase mDb;
    TextView tvTen, tvGia, tvGiaKhuyenMai, tvMoTa, tvKhoiluong, tvThuongHieu, tvXuatXu;
    ExtendedFloatingActionButton btnDatMua;
    String hinhanhsp;
    double giasp, giakhuyenmai;
    String tensp, khoiluongsp, moTa, thuongHieu, xuatXu;
    private int idsanpham = DEFAULT_ID;
    Spinner spinner;
    TabLayout tabLayout;
    TabLayout.Tab tabGioHang;
    TabLayout.Tab tabYeuThich;
    BadgeDrawable badgeDrawableYeuthich;
    BadgeDrawable badgeDrawableGioHang;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        mDb = AppDatabase.getInstance(getApplicationContext());
        imgChiTiet = findViewById(R.id.image_view_chi_tiet);
        tvTen = findViewById(R.id.ten_san_pham);
        tvKhoiluong = findViewById(R.id.khoi_luong_san_pham);
        tvGia = findViewById(R.id.gia_san_pham);
        tvGiaKhuyenMai = findViewById(R.id.giakhuyenmai);
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
        intent = getIntent();
        getsanpham(intent);

        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_ID);
            Picasso.get().load(MainFragment.manghangsanpham.get(idHang).getAnhHang()).into(imageViewHangSp);

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
            idsanpham = intent.getIntExtra(EXTRA_SANPHAM_ID, DEFAULT_ID);
            YeuThichViewModel viewModel = ViewModelProviders.of(this).get(YeuThichViewModel.class);
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

    private void getsanpham(Intent intent) {
        if (intent.hasExtra("CatalogAdapter")) {
            Log.i("LogData", "CatalogAdapter");
            SanPham sanPham = (SanPham) getIntent().getSerializableExtra("CatalogAdapter");
            assert sanPham != null;
            idsanpham = sanPham.getId();
            tensp = sanPham.getTenSanPham();
            hinhanhsp = sanPham.getHinhAnhSanPham();
            khoiluongsp = sanPham.getKhoiLuong();
            thuongHieu = sanPham.getThuongHieu();
            xuatXu = sanPham.getXuatXu();
            moTa = sanPham.getMoTa();
            giasp = Math.round(sanPham.getGiaSanPham() * 100.0) / 100.0;
            giakhuyenmai = Math.round(sanPham.getGiaKhuyenMai() * 100.0) / 100.0;

            //TODO SALE
            if (sanPham.getGiaKhuyenMai() != 0) {
                tvGia.setText("€"+giasp);
                tvGia.setPaintFlags(tvGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvGiaKhuyenMai.setText("€"+giakhuyenmai);
                tvGiaKhuyenMai.setVisibility(View.VISIBLE);
            } else {
                tvGiaKhuyenMai.setVisibility(View.INVISIBLE);
                tvGia.setText("€"+giasp);
                tvGia.setPaintFlags(tvGia.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

            tvTen.setText(tensp);
            tvMoTa.setText(moTa);
            tvKhoiluong.setText("Chi tiết: " + khoiluongsp);
            tvThuongHieu.setText("Thương hiệu: " + thuongHieu);
            tvXuatXu.setText("Xuất xứ: " + xuatXu);
            Picasso.get().load(hinhanhsp).into(imgChiTiet);
        } else if (intent.hasExtra("GioHangAdapter")) {
            Log.i("LogData", "GioHangAdapter");

            GioHangEntry gioHang = (GioHangEntry) getIntent().getSerializableExtra("GioHangAdapter");
            assert gioHang != null;
            idsanpham = gioHang.getIdSanPham();
            tensp = gioHang.getTenSanPham();
            hinhanhsp = gioHang.getHinhAnhSanPham();
            khoiluongsp = gioHang.getKhoiLuong();
            thuongHieu = gioHang.getThuongHieu();
            xuatXu = gioHang.getXuatXu();
            moTa = gioHang.getMoTa();
            giasp = Math.round(gioHang.getGiaSanPham()/gioHang.getSoLuong() * 100.0) / 100.0;
            giakhuyenmai = Math.round(gioHang.getGiaKhuyenMai()/gioHang.getSoLuong() * 100.0) / 100.0;

            tvTen.setText(tensp);
            tvMoTa.setText(moTa);
            tvKhoiluong.setText("Chi tiết: " + khoiluongsp);
            tvThuongHieu.setText("Thương hiệu: " + thuongHieu);
            tvXuatXu.setText("Xuất xứ: " + xuatXu);

            //TODO SALE
            if (giakhuyenmai!= 0) {
                tvGia.setText("€"+giasp);
                tvGia.setPaintFlags(tvGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvGiaKhuyenMai.setText("€"+giakhuyenmai);
                tvGiaKhuyenMai.setVisibility(View.VISIBLE);
            } else {
                tvGiaKhuyenMai.setVisibility(View.INVISIBLE);
                tvGia.setText("€"+giasp);
                tvGia.setPaintFlags(tvGia.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
            Picasso.get().load(hinhanhsp).into(imgChiTiet);
        } else if (intent.hasExtra("YeuThichAdapter")) {
            Log.i("LogData", "YeuThichAdapter");

            YeuThichEntry yeuThich = (YeuThichEntry) getIntent().getSerializableExtra("YeuThichAdapter");
            assert yeuThich != null;
            idsanpham = yeuThich.getIdSanPham();
            tensp = yeuThich.getTenSanPham();
            hinhanhsp = yeuThich.getHinhAnhSanPham();
            khoiluongsp = yeuThich.getKhoiLuong();
            thuongHieu = yeuThich.getThuongHieu();
            xuatXu = yeuThich.getXuatXu();
            moTa = yeuThich.getMoTa();
            giasp = Math.round(yeuThich.getGiaSanPham() * 100.0) / 100.0;
            giakhuyenmai = Math.round(yeuThich.getGiaKhuyenMai() * 100.0) / 100.0;
            tvTen.setText(tensp);
            tvMoTa.setText(moTa);
            tvKhoiluong.setText("Chi tiết: " + khoiluongsp);
            tvThuongHieu.setText("Thương hiệu: " + thuongHieu);
            tvXuatXu.setText("Xuất xứ: " + xuatXu);

            //TODO SALE
            if (giakhuyenmai!= 0) {
                tvGia.setText("€"+giasp);
                tvGia.setPaintFlags(tvGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvGiaKhuyenMai.setText("€"+giakhuyenmai);
                tvGiaKhuyenMai.setVisibility(View.VISIBLE);
            } else {
                tvGiaKhuyenMai.setVisibility(View.INVISIBLE);
                tvGia.setText("€"+giasp);
                tvGia.setPaintFlags(tvGia.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
            Picasso.get().load(hinhanhsp).into(imgChiTiet);
        } else
            Toast.makeText(DetailActivity.this, "Error!", Toast.LENGTH_SHORT).show();


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
                            //TODO SALE
                            mDb.yeuThichDao().insertYeuThich(new YeuThichEntry(idsanpham, tensp, giasp,giakhuyenmai, hinhanhsp, khoiluongsp, idHang, moTa, thuongHieu, xuatXu));

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
                                    mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensp, giasp * soluongmoi,giakhuyenmai * soluongmoi, hinhanhsp, khoiluongsp, soluongmoi, idHang, moTa, thuongHieu, xuatXu));


                                }
                            });


                            if (soluongmoi >= 50) {


                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensp, giasp * 20,giakhuyenmai * soluongmoi, hinhanhsp, khoiluongsp, 50, idHang, moTa, thuongHieu, xuatXu));


                                    }
                                });

                                Toast.makeText(DetailActivity.this, "Đã đủ 50 " + tensp + " trong giỏ hàng", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(DetailActivity.this, "Đã thêm " + soluongmoi + " " + tensp + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            }


                            exsist = true;


                        }
                    }
                    if (!exsist) {
                        final int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        final double giaspmoi = soluong * giasp;
                        final double giakhuyenmaimoi = soluong * giakhuyenmai;

                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanpham, tensp, giaspmoi, giakhuyenmaimoi, hinhanhsp, khoiluongsp, soluong, idHang, moTa, thuongHieu, xuatXu));


                            }
                        });

                        Toast.makeText(DetailActivity.this, "Đã thêm " + soluong + " " + tensp + " vào giỏ hàng", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    final int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    final double giaspmoi = soluong * giasp;
                    final double giakhuyenmaimoi = soluong * giakhuyenmai;


                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanpham, tensp, giaspmoi,giakhuyenmaimoi,  hinhanhsp, khoiluongsp, soluong, idHang, moTa, thuongHieu, xuatXu));


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
        Log.i("Intent", "OnNewIntent");

        this.setIntent(intent);
        intent = getIntent();
        getsanpham(intent);
        Log.i("Intent", "Reload getsanpham()");


/*
        if (intent != null && intent.hasExtra(EXTRA_HANG_ID) && intent.hasExtra(EXTRA_SANPHAM_ID)) {
*/
        if (intent != null) {

            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_ID);
            Picasso.get().load(MainFragment.manghangsanpham.get(idHang).getAnhHang()).into(imageViewHangSp);
            idsanpham = intent.getIntExtra(EXTRA_SANPHAM_ID, DEFAULT_ID);

        }
        super.onNewIntent(intent);
    }


}




