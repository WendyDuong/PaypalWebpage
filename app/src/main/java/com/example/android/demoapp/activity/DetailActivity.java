package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.CatalogAdapter;
import com.example.android.demoapp.adapter.GioHangAdapter;
import com.example.android.demoapp.adapter.YeuthichAdapter;
import com.example.android.demoapp.model.GiohangItem;
import com.example.android.demoapp.model.Sanpham;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    ImageView imgChiTiet, timImageView, imageViewHangSp;
    TextView tvTen, tvGia, tvMoTa, tvKhoiluong;
    Button btnDatMua;
    int idsanpham, giasp,idnhacungcap;
    String tensp, hinhanhsp,khoiluongsp, motasp;
    GioHangAdapter gioAdapter;
    Spinner spinner;
    YeuthichAdapter yeuthichAdapter;
    CatalogAdapter catalogAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout3);




        imgChiTiet = findViewById(R.id.image_view_chi_tiet);
        tvTen = findViewById(R.id.ten_san_pham);
        tvKhoiluong = findViewById(R.id.khoi_luong_san_pham);
        tvGia =  findViewById(R.id.gia_san_pham);
        tvMoTa = findViewById(R.id.mo_ta_san_pham);
        btnDatMua = findViewById(R.id.button_mua);
        timImageView = findViewById(R.id.tim_chi_tiet_activity);
        imageViewHangSp = findViewById(R.id.image_view_hang_san_pham);
        spinner = findViewById(R.id.spinner);
        gioAdapter=new GioHangAdapter(DetailActivity.this,MainActivity.mangGioHang);

        imageViewHangSp.setVisibility(View.INVISIBLE);

        getDuLieu();
        eventSpinner();

        switch (idnhacungcap){
            case 0:
              //  imageViewHangSp.setImageResource(R.drawable.hit);
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.hit));
                break;
            case 1:
               // imageViewHangSp.setImageResource(R.drawable.merc);
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.merc));

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
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icontimkiem));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.timdo_bar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.xe_hang));


        addEvent();
        timImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timImageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.timden24).getConstantState()) {
                    timImageView.setImageResource(R.drawable.timdo24);

                    /*for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                        if (MainActivity.mangGioHang.get(i).getIdsp() == idsanpham) {
                            Toast.makeText(context, "Sản Phẩm đã có trong danh sách yêu thích",Toast.LENGTH_SHORT).show();
                        }
                        else   */
                    MainActivity.mangYeuthich.add(new Sanpham(idsanpham,tensp,giasp,hinhanhsp,motasp,idnhacungcap,khoiluongsp));
                    Toast.makeText(DetailActivity.this,"Đã thêm "+tensp+ "vào danh sách yêu thích", Toast.LENGTH_SHORT).show();

                    yeuthichAdapter = new YeuthichAdapter(DetailActivity.this, MainActivity.mangYeuthich);

                    yeuthichAdapter.notifyDataSetChanged();

                }else{


                    timImageView.setImageResource(R.drawable.timden24);
                    for (int vitrixoa = 0; vitrixoa < MainActivity.mangYeuthich.size(); vitrixoa++) {
                        if (MainActivity.mangYeuthich.get(vitrixoa).getMidsanpham() == idsanpham)
                        MainActivity.mangYeuthich.remove(vitrixoa);}



                        yeuthichAdapter = new YeuthichAdapter(DetailActivity.this, MainActivity.mangYeuthich);
                        yeuthichAdapter.notifyDataSetChanged();







                }
            }});



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent0 = new Intent(DetailActivity.this, CatalogActivity.class);
                        intent0.putExtra("idnhacungcap",idnhacungcap);
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
                        intent0.putExtra("idnhacungcap",idnhacungcap);

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


    private void getDuLieu() {
        Sanpham sanPham = (Sanpham) getIntent().getSerializableExtra("chitietsanpham");
        idsanpham = sanPham.getMidsanpham();
        tensp = sanPham.getMtensanpham();
        giasp = sanPham.getMgiasanpham();
        hinhanhsp = sanPham.getMhinhanhsanpham();
        khoiluongsp = sanPham.getKhoiluongsanpham();
        idnhacungcap = sanPham.getIdnhacungcap();
        motasp = sanPham.getMmotasanpham();
        tvTen.setText(sanPham.getMtensanpham());
        tvMoTa.setText(sanPham.getMmotasanpham());
        tvKhoiluong.setText(sanPham.getKhoiluongsanpham());
        DecimalFormat deci = new DecimalFormat("###,###,###");
        tvGia.setText(deci.format(sanPham.getMgiasanpham()) + " Đ");
        Picasso.get().load(sanPham.getMhinhanhsanpham()).into(imgChiTiet);
        boolean exit = false;
        if (MainActivity.mangYeuthich.size() > 0) {
            for (int vitritim = 0; vitritim < MainActivity.mangYeuthich.size(); vitritim++) {
                if (MainActivity.mangYeuthich.get(vitritim).getMidsanpham() == idsanpham) {
                    timImageView.setImageResource(R.drawable.timdo24);
                    exit = true;
                }}
            if (exit == false)
                timImageView.setImageResource(R.drawable.timden24);
        }
        else
          { timImageView.setImageResource(R.drawable.timden24);}

        }

    @Override
    protected void onStart() {
        boolean exit = false;

        if (MainActivity.mangYeuthich.size() > 0) {
            for (int vitritim = 0; vitritim < MainActivity.mangYeuthich.size(); vitritim++) {
                if (MainActivity.mangYeuthich.get(vitritim).getMidsanpham() == idsanpham) {
                    timImageView.setImageResource(R.drawable.timdo24);
                    exit = true;
                }
            }
            if (exit == false)
                timImageView.setImageResource(R.drawable.timden24);
        }
        else
        { timImageView.setImageResource(R.drawable.timden24);}

        super.onStart();
    }

    private void addEvent() {
            btnDatMua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(MainActivity.mangGioHang.size()>0){
                        int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                        boolean exsist=false;
                        for(int i=0;i<MainActivity.mangGioHang.size();i++){
                            if(MainActivity.mangGioHang.get(i).getIdsp()==idsanpham){
                                MainActivity.mangGioHang.get(i).setSoluongsp(MainActivity.mangGioHang.get(i).getSoluongsp()+sl);
                                if(MainActivity.mangGioHang.get(i).getSoluongsp()>=20){
                                    MainActivity.mangGioHang.get(i).setSoluongsp(20);
                                }
                                MainActivity.mangGioHang.get(i).setGiasp(giasp*sl+MainActivity.mangGioHang.get(i).getGiasp());
                                exsist=true;
                            }
                        }
                        if(exsist==false){
                            int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                            int giamoi=soluong*giasp;
                            MainActivity.mangGioHang.add(new GiohangItem(idsanpham,tensp,giamoi,hinhanhsp, khoiluongsp, motasp, idnhacungcap, soluong));
                            gioAdapter.notifyDataSetChanged();
                        }
                    }else{
                        int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                        int giamoi=soluong*giasp;
                        MainActivity.mangGioHang.add(new GiohangItem(idsanpham,tensp,giamoi,hinhanhsp,khoiluongsp,motasp,idnhacungcap, soluong));
                        gioAdapter.notifyDataSetChanged();
                    }
                    Intent intent=new Intent(DetailActivity.this, GioHangActivity.class);
                    startActivity(intent);
                }
            });
        }




}

