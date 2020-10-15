package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    ImageView imgChiTiet, timImageView, imageViewHangSp;
    private static final String INSTANCE_SANPHAM_ID = "instanceID";
    private static final int DEFAULT_ID = -1 ;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId" ;
    private static final String EXTRA_HANG_ID = "extraHangId";
    private static final String TAG = DetailActivity.class.getSimpleName();
    private int idHang, id;

    private AppDatabase mDb;
    TextView tvTen, tvGia, tvMoTa, tvKhoiluong, tvThuongHieu, tvXuatXu;
    Button btnDatMua;
    int idsanpham, hinhanhsp;
    double giasp;
    String tensp,khoiluongsp, thuongHieu, xuatXu;
    private int mTaskId = DEFAULT_ID;
    Spinner spinner;

    TabLayout tabLayout;
    TabLayout.Tab tabGioHang;
    TabLayout.Tab tabYeuThich;
    BadgeDrawable badgeDrawableYeuthich;
    BadgeDrawable badgeDrawableGioHang;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;
    List<GioHangEntry> mgioHangEntries;
    TabLayout.Tab tabNhaCungCap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        mDb = AppDatabase.getInstance(getApplicationContext());
        imgChiTiet = findViewById(R.id.image_view_chi_tiet);
        tvTen = findViewById(R.id.ten_san_pham);
        tvKhoiluong = findViewById(R.id.khoi_luong_san_pham);
        tvGia =  findViewById(R.id.gia_san_pham);
        tvMoTa = findViewById(R.id.mo_ta_san_pham);
        tvThuongHieu = findViewById(R.id.san_pham_thuong_hieu);
        tvXuatXu = findViewById(R.id.san_pham_xuat_xu);
        btnDatMua = findViewById(R.id.button_mua);
        tabLayout = findViewById(R.id.tab_layout3);
        spinner = findViewById(R.id.spinner);
        timImageView = findViewById(R.id.tim_chi_tiet_activity);
        eventSpinner();

        addEvent();
        yeuthichEvent();
        imageViewHangSp = findViewById(R.id.image_view_hang_san_pham);

        imageViewHangSp.setVisibility(View.INVISIBLE);


        //TODO(4)

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_SANPHAM_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_SANPHAM_ID, DEFAULT_ID);
        }

        //TODO(3) get ID from Intent
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_HANG_ID)) {
            idHang = intent.getIntExtra(EXTRA_HANG_ID, DEFAULT_ID);


            switch (idHang) {
                case 0:
                    //  imageViewHangSp.setImageResource(R.drawable.hit);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.hit));
                    break;
                case 1:
                    // imageViewHangSp.setImageResource(R.drawable.merc);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.merclogo));

                    break;
                case 2:
                    //imageViewHangSp.setImageResource(R.drawable.penny);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.penny));

                    break;
                case 3:
                    //imageViewHangSp.setImageResource(R.drawable.muller);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.muller));

                    break;
                case 4:
                    //imageViewHangSp.setImageResource(R.drawable.dell);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.dell));

                    break;
                case 5:
                    //imageViewHangSp.setImageResource(R.drawable.audi);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.audi));

                    break;
                case 6:
                    //imageViewHangSp.setImageResource(R.drawable.dior);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.dior));

                    break;
                case 7:
                    //imageViewHangSp.setImageResource(R.drawable.skii);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.skii));

                    break;
                case 8:
                    //imageViewHangSp.setImageResource(R.drawable.richy);
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.richy));

                    break;
            }
        }
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kinh_lup_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));
        tabNhaCungCap = tabLayout.getTabAt(0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(DetailActivity.this, CatalogActivity.class);
                        intent0.putExtra(DetailActivity.EXTRA_HANG_ID , idHang);
                        DetailActivity.this.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(DetailActivity.this, TimKiemActivity.class);
                        DetailActivity.this.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(DetailActivity.this, YeuthichActivity.class);
                        DetailActivity.this.startActivity(intent2);
                        break;

                    case 3:
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

                        Intent intent0 = new Intent(DetailActivity.this, CatalogActivity.class);
                        intent0.putExtra(DetailActivity.EXTRA_HANG_ID,idHang);

                        DetailActivity.this.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(DetailActivity.this, TimKiemActivity.class);
                        DetailActivity.this.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(DetailActivity.this, YeuthichActivity.class);
                        DetailActivity.this.startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(DetailActivity.this, GioHangActivity.class);
                        DetailActivity.this.startActivity(intent3);
                        break;

                    default:
                        break;
                }
            }



        });

        tabYeuThich = tabLayout.getTabAt(2);
        tabGioHang = tabLayout.getTabAt(3);

        badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
        badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();


        if (intent != null && intent.hasExtra(EXTRA_SANPHAM_ID)) {
            if (mTaskId == DEFAULT_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_SANPHAM_ID, DEFAULT_ID);

                DetailViewModelFactory factory = new DetailViewModelFactory(mDb, mTaskId);
                final DetailViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(DetailViewModel.class);

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

                viewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                    @Override
                    public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                        yeuThichEntries = yeuThich;
                        KiemTraYeuThich();
                        if (yeuThichEntries.size()>0){
                            badgeDrawableYeuthich.setVisible(true);
                            badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                        }
                        else
                            badgeDrawableYeuthich.setVisible(false);

                    }

                    });

            }
        }






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

                    for (int vitrixoa = 0; vitrixoa <yeuThichEntries.size(); vitrixoa++) {
                        int  idsanphamxoa = yeuThichEntries.get(vitrixoa).getIdSanPham();
                        if (idsanphamxoa == idsanpham) {
                            final int finalVitrixoa = vitrixoa;
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.yeuThichDao().deleteYeuThich(yeuThichEntries.get(finalVitrixoa));
                                }});
                        }
                    }
                }


            }
        });


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_SANPHAM_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }

    private void populateUI(SanPhamEntry sanPham) {
        idsanpham = sanPham.getIdSanPham();
        id = sanPham.getId();
        tensp = sanPham.getTenSanPham();
        giasp = sanPham.getGiaSanPham();
        hinhanhsp = sanPham.getHinhAnh();
        khoiluongsp = sanPham.getKhoiLuong();
        thuongHieu = sanPham.getThuongHieu();
        xuatXu = sanPham.getXuatXu();

        tvTen.setText(tensp);
        tvMoTa.setText(sanPham.getMoTa());
        tvKhoiluong.setText(khoiluongsp);
        tvThuongHieu.setText("Thương hiệu: " + thuongHieu);
        tvXuatXu.setText("Xuất xứ: " + xuatXu);
        DecimalFormat deci = new DecimalFormat("###,###,###");
        tvGia.setText("Giá " + deci.format(giasp) + " Đ");
        imgChiTiet.setImageResource(hinhanhsp);
    }

    private void addEvent() {
        GioHangViewModel gioHangViewModel = ViewModelProviders.of(this).get(GioHangViewModel.class);
        gioHangViewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
            @Override
            public void onChanged(@Nullable List<GioHangEntry> gioHangEntries) {

                mgioHangEntries = gioHangEntries;
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
            }
        });


        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //TODO(7) them vao gio hang


                if(mgioHangEntries.size()>0){
                    final int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exsist=false;
                    for(int i=0;i<mgioHangEntries.size();i++){

                        if (mgioHangEntries.get(i).getIdSanPham()  ==idsanpham){
                            final int soluongcu = mgioHangEntries.get(i).getSoLuong();
                            final  int id = mgioHangEntries.get(i).getId();
                            // Put the task description and selected mPriority into the ContentValues

                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensp,giasp * (soluongcu + sl) ,hinhanhsp,khoiluongsp,soluongcu + sl ,idHang));



                                }
                            });
                            Toast.makeText(DetailActivity.this,"Đã cap nhat san pham" +" " + tensp + " trong giỏ hàng",Toast.LENGTH_SHORT).show();



                            if(soluongcu + sl >=20){


                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.gioHangDao().updateGioHang(new GioHangEntry(id , idsanpham, tensp,giasp * 20 ,hinhanhsp,khoiluongsp,20 ,idHang));



                                    }
                                });

                                Toast.makeText(DetailActivity.this,"Đã đủ 20 " +" " + tensp + " trong giỏ hàng",Toast.LENGTH_SHORT).show();

                            }



                            exsist=true;



                        }
                    }
                    if(exsist==false){
                        final int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                       final double giamoi = soluong*giasp;

                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanpham, tensp,giamoi ,hinhanhsp,khoiluongsp,soluong ,idHang));



                            }
                        });

                        Toast.makeText(DetailActivity.this,"Đã thêm " + soluong +" " + tensp +" vào giỏ hàng",Toast.LENGTH_SHORT).show();




                    }
                }else{
                    final int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                    final double giamoi=soluong*giasp;


                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanpham, tensp,giamoi ,hinhanhsp,khoiluongsp,soluong,idHang ));



                        }
                    });
                    // Display the URI that's returned with a Toast
                    // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete

                }



                Intent intent = new Intent(DetailActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void eventSpinner() {
        ArrayList<Integer> arr=new ArrayList<Integer>();
        for(int i=1;i<=10;i++){
            arr.add(i);
        }
        ArrayAdapter spinnerAdapter=new ArrayAdapter(DetailActivity.this,R.layout.spinner_item_custom,arr);
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

                    if (exit == false)
                        timImageView.setImageResource(R.drawable.timden24);


                } else
                    {  timImageView.setImageResource(R.drawable.timden24);}

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
                    //  imageViewHangSp.setImageResource(R.drawable.hit);
                    tabNhaCungCap.setIcon(R.drawable.hit);
                    break;
                case 1:
                    // imageViewHangSp.setImageResource(R.drawable.merc);
                    tabNhaCungCap.setIcon(R.drawable.merclogo);
                    break;
                case 2:
                    //imageViewHangSp.setImageResource(R.drawable.penny);
                    tabNhaCungCap.setIcon(R.drawable.penny);
                    break;
                case 3:
                    //imageViewHangSp.setImageResource(R.drawable.muller);
                    tabNhaCungCap.setIcon(R.drawable.muller);
                    break;
                case 4:
                    //imageViewHangSp.setImageResource(R.drawable.dell);
                    tabNhaCungCap.setIcon(R.drawable.dell);
                    break;
                case 5:
                    //imageViewHangSp.setImageResource(R.drawable.audi);
                    tabNhaCungCap.setIcon(R.drawable.audi);
                    break;
                case 6:
                    //imageViewHangSp.setImageResource(R.drawable.dior);
                    tabNhaCungCap.setIcon(R.drawable.dior);
                    break;
                case 7:
                    //imageViewHangSp.setImageResource(R.drawable.skii);
                    tabNhaCungCap.setIcon(R.drawable.skii);
                    break;
                case 8:
                    //imageViewHangSp.setImageResource(R.drawable.richy);
                    tabNhaCungCap.setIcon(R.drawable.richy);
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
                        viewModel.getDetailSanPham().removeObserver(this);
                        populateUI(sanPham);
                    }

                });

                viewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                    @Override
                    public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                        gioHangEntries = gioHang;
                        int sosanphammua = 0;

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

                viewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                    @Override
                    public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                        yeuThichEntries = yeuThich;
                        if (yeuThichEntries.size()>0){
                            badgeDrawableYeuthich.setVisible(true);
                            badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                        }
                        else
                            badgeDrawableYeuthich.setVisible(false);

                    }
                });

        }
        super.onNewIntent(intent);
    }


    @Override
    protected void onRestart() {
        DetailViewModelFactory factory = new DetailViewModelFactory(mDb, mTaskId);

        final DetailViewModel viewModel
                = ViewModelProviders.of(this, factory).get(DetailViewModel.class);


        viewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
            @Override
            public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                gioHangEntries = gioHang;
                int sosanphammua = 0;

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

        viewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
            @Override
            public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                yeuThichEntries = yeuThich;
                KiemTraYeuThich();
                if (yeuThichEntries.size()>0){
                    badgeDrawableYeuthich.setVisible(true);
                    badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                }
                else
                    badgeDrawableYeuthich.setVisible(false);

            }
        });
        super.onRestart();
    }
}


