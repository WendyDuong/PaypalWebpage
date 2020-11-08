package com.example.android.demoapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.DetailViewModel;
import com.example.android.demoapp.ViewModel.DetailViewModelFactory;
import com.example.android.demoapp.ViewModel.GioHangViewModel;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.SanPhamEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.apache.commons.math3.util.Precision;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    ImageView imgChiTiet, timImageView, imageViewHangSp;
    private static final String INSTANCE_SANPHAM_ID = "instanceID";
    private static final int DEFAULT_ID = -1;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";
    private static final String TAG = DetailActivity.class.getSimpleName();
    private int idHang, idsanpham;
    ImageView expandedImageView;

    DetailViewModelFactory factory;
   DetailViewModel viewModel;

    Toolbar toolBarChiTietActivity;
    TextView tvMoTaTitle, tvChiTietTitle, devider1, devider2;

    View LayoutChiTietActivity, cardViewSpinner;
    private Animator currentAnimator;
    private int shortAnimationDuration;



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
        Toast.makeText(DetailActivity.this, "OnCreate", Toast.LENGTH_SHORT).show();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

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

        LayoutChiTietActivity = findViewById(R.id.card_view_detail);
        toolBarChiTietActivity = findViewById(R.id.toolbar3);
        tvMoTaTitle = findViewById(R.id.mo_ta_san_pham_tilte);
        tvChiTietTitle = findViewById(R.id.chi_tiet_san_pham_tilte);
        devider1 = findViewById(R.id.devider);
        devider2 = findViewById(R.id.devider2);
        cardViewSpinner = findViewById(R.id.card_view_spinner);

        expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);

        eventSpinner();
        yeuthichEvent();

        imgChiTiet.setClipToOutline(true);



        //TODO(3) get ID from Intent
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_ID);


            switch (idHang) {
                case 0:
                      imageViewHangSp.setImageResource(R.drawable.hit);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.hit));
                    break;
                case 1:
                     imageViewHangSp.setImageResource(R.drawable.merc);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.merclogo));

                    break;
                case 2:
                    imageViewHangSp.setImageResource(R.drawable.penny);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.penny));

                    break;
                case 3:
                    imageViewHangSp.setImageResource(R.drawable.rossmann_logo);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.rossmann));

                    break;
                case 4:
                    imageViewHangSp.setImageResource(R.drawable.apotheke_logo);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.apotheke_logo));

                    break;
                case 5:
                    imageViewHangSp.setImageResource(R.drawable.mediamarkt_logo);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.mediamarkt_logo));

                    break;
                case 6:
                    imageViewHangSp.setImageResource(R.drawable.dior);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.dior));

                    break;
                case 7:
                    imageViewHangSp.setImageResource(R.drawable.skii);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.skii));

                    break;
                case 8:
                    imageViewHangSp.setImageResource(R.drawable.richy);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.richy));

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

        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();


        if (intent != null && intent.hasExtra(EXTRA_SANPHAM_ID)) {
            if (mTaskId == DEFAULT_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_SANPHAM_ID, DEFAULT_ID);

                factory = new DetailViewModelFactory(mDb, mTaskId);
                viewModel
                        = ViewModelProviders.of(this, factory).get(DetailViewModel.class);

                viewModel.getDetailSanPham().observe(this, new Observer<SanPhamEntry>() {
                    @Override
                    public void onChanged(SanPhamEntry sanPham) {
                        Toast.makeText(DetailActivity.this, "Populate UI", Toast.LENGTH_SHORT).show();


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


        imgChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomImageFromThumb(imgChiTiet, hinhanhsp);
            }
        });
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);



    }

    private void yeuthichEvent() {


        timImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timImageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.timden24).getConstantState()) {
                    //    timImageView.setImageResource(R.drawable.timdo24);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.yeuThichDao().insertYeuThich(new YeuThichEntry(idsanpham, tensp, giasp, hinhanhsp, khoiluongsp, idHang));
                            Log.d("checkidsanphaminsert", " " + idsanpham);

                        }
                    });


                } else {


                    //  timImageView.setImageResource(R.drawable.timden24);

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
        tvKhoiluong.setText(khoiluongsp);
        tvThuongHieu.setText("Thương hiệu: " + thuongHieu);
        tvXuatXu.setText("Xuất xứ: " + xuatXu);
        DecimalFormat deci = new DecimalFormat("###,###,###");
        tvGia.setText("Giá " + deci.format(giasp) + " Đ");
        imgChiTiet.setImageResource(hinhanhsp);



/*        imgChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView image = new ImageView(DetailActivity.this);
                image.setImageResource(hinhanhsp);

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(DetailActivity.this).
                                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).
                                setView(image);
                builder.create().show();

            }
        });*/



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


                //TODO(7) them vao gio hang


                if (gioHangEntries.size() > 0) {
                    final int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exsist = false;
                    for (int i = 0; i < gioHangEntries.size(); i++) {

                        if (gioHangEntries.get(i).getIdSanPham() == idsanpham) {
                            final int soluongcu = gioHangEntries.get(i).getSoLuong();
                            final int id = gioHangEntries.get(i).getId();
                            final int soluongmoi = soluongcu + sl;
                            // Put the task description and selected mPriority into the ContentValues

                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensp, Precision.round((giasp * soluongmoi)/1000,0)*1000, hinhanhsp, khoiluongsp, soluongmoi, idHang));


                                }
                            });


                            if (soluongmoi >= 20) {


                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensp, Precision.round((giasp * 20)/1000,0)*1000, hinhanhsp, khoiluongsp, 20, idHang));


                                    }
                                });

                                Toast.makeText(DetailActivity.this, "Đã đủ 20 " + " " + tensp + " trong giỏ hàng", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(DetailActivity.this, "Đã thêm " + soluongmoi + " " + tensp + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            }


                            exsist = true;


                        }
                    }
                    if (exsist == false) {
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
           /*
                Intent intent = new Intent(DetailActivity.this, GioHangActivity.class);
                startActivity(intent);*/
            }
        });
    }

    private void eventSpinner() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
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
            Toast.makeText(DetailActivity.this, "idsanpham: "+ idsanpham, Toast.LENGTH_SHORT).show();

            for (int vitritim = 0; vitritim < yeuThichEntries.size(); vitritim++) {
                if (yeuThichEntries.get(vitritim).getIdSanPham() == idsanpham) {
                    timImageView.setImageResource(R.drawable.timdo24);
                    Toast.makeText(DetailActivity.this, "Tim Do", Toast.LENGTH_SHORT).show();

                    exit = true;
                }
            }

            if (exit == false) {
                Toast.makeText(DetailActivity.this, "Tim Den Exit false", Toast.LENGTH_SHORT).show();

                timImageView.setImageResource(R.drawable.timden24);
            }


        } else {
            Toast.makeText(DetailActivity.this, "Tim Den Mang = 0", Toast.LENGTH_SHORT).show();

            timImageView.setImageResource(R.drawable.timden24);
        }

    }


    @Override
    protected void onStop() {
        Toast.makeText(DetailActivity.this, "onStop", Toast.LENGTH_SHORT).show();
        getViewModelStore().clear();
        super.onStop();

    }


    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(DetailActivity.this, "onNewIntent", Toast.LENGTH_SHORT).show();
        setIntent(intent);
        intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_ID);
            switch (idHang) {
                case 0:
                    imageViewHangSp.setImageResource(R.drawable.hit);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.hit));
                    break;
                case 1:
                    imageViewHangSp.setImageResource(R.drawable.merc);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.merclogo));

                    break;
                case 2:
                    imageViewHangSp.setImageResource(R.drawable.penny);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.penny));

                    break;
                case 3:
                    imageViewHangSp.setImageResource(R.drawable.rossmann_logo);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.rossmann));

                    break;
                case 4:
                    imageViewHangSp.setImageResource(R.drawable.apotheke_logo);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.apotheke_logo));

                    break;
                case 5:
                    imageViewHangSp.setImageResource(R.drawable.mediamarkt_logo);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.mediamarkt_logo));

                    break;
                case 6:
                    imageViewHangSp.setImageResource(R.drawable.dior);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.dior));

                    break;
                case 7:
                    imageViewHangSp.setImageResource(R.drawable.skii);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.skii));

                    break;
                case 8:
                    imageViewHangSp.setImageResource(R.drawable.richy);
                    //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.richy));

                    break;
            }
        }
        if (intent != null && intent.hasExtra(EXTRA_SANPHAM_ID)) {
            // populate the UI
            mTaskId = intent.getIntExtra(EXTRA_SANPHAM_ID, DEFAULT_ID);

            DetailViewModelFactory factory = new DetailViewModelFactory(mDb, mTaskId);
            final DetailViewModel viewModel
                    = ViewModelProviders.of(this, factory).get(DetailViewModel.class);

            viewModel.getDetailSanPham().observe(this, new Observer<SanPhamEntry>() {
                @Override
                public void onChanged(SanPhamEntry sanPham) {
                    //viewModel.getDetailSanPham().removeObserver(this);
                    populateUI(sanPham);
                }

            });

            viewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                @Override
                public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                    gioHangEntries = gioHang;
                    int sosanphammua = 0;

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
        super.onNewIntent(intent);
    }


    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        expandedImageView.setImageResource(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        //  thumbView.setAlpha(0f);
        LayoutChiTietActivity.setVisibility(View.INVISIBLE);
        expandedImageView.setVisibility(View.VISIBLE);

        cardViewSpinner.setVisibility(View.INVISIBLE);
        btnDatMua.setVisibility(View.INVISIBLE);
        tvMoTa.setVisibility(View.INVISIBLE);
        tvXuatXu.setVisibility(View.INVISIBLE);
        tvThuongHieu.setVisibility(View.INVISIBLE);
        tvChiTietTitle.setVisibility(View.INVISIBLE);
        tvMoTaTitle.setVisibility(View.INVISIBLE);
        devider1.setVisibility(View.INVISIBLE);
        devider2.setVisibility(View.INVISIBLE);



        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(shortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentAnimator = null;
            }
        });
        set.start();
        currentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnimator != null) {
                    currentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(shortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //   thumbView.setAlpha(1f);
                        btnDatMua.setVisibility(View.VISIBLE);
                        cardViewSpinner.setVisibility(View.VISIBLE);
                        tvMoTa.setVisibility(View.VISIBLE);
                        tvXuatXu.setVisibility(View.VISIBLE);
                        tvThuongHieu.setVisibility(View.VISIBLE);
                        tvChiTietTitle.setVisibility(View.VISIBLE);
                        tvMoTaTitle.setVisibility(View.VISIBLE);
                        devider1.setVisibility(View.VISIBLE);
                        devider2.setVisibility(View.VISIBLE);
                        LayoutChiTietActivity.setVisibility(View.VISIBLE);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //      thumbView.setAlpha(1f);
                        btnDatMua.setVisibility(View.VISIBLE);
                        cardViewSpinner.setVisibility(View.VISIBLE);
                        tvMoTa.setVisibility(View.VISIBLE);
                        tvXuatXu.setVisibility(View.VISIBLE);
                        tvThuongHieu.setVisibility(View.VISIBLE);
                        tvChiTietTitle.setVisibility(View.VISIBLE);
                        tvMoTaTitle.setVisibility(View.VISIBLE);
                        devider1.setVisibility(View.VISIBLE);
                        devider2.setVisibility(View.VISIBLE);
                        LayoutChiTietActivity.setVisibility(View.VISIBLE);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }
                });
                set.start();
                currentAnimator = set;
            }
        });
    }
}




