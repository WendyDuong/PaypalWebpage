package com.example.android.demoapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.DatHangViewModel;
import com.example.android.demoapp.adapter.DatHangAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DatHangActivity extends AppCompatActivity {
    DatHangAdapter datHangAdapter;
    List<GioHangEntry> mDatHangs;
    TextView tongTien;
    private double tongTienDonHang = 0;
    double tongtien;
    private AppDatabase mDb;
    RecyclerView datHangRecyclerView;
    EditText editTextTen, editTextSoDt, editTextDiaChi, editTextEmail;
    Button buttonDatHang, buttonMuaTiep;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat_hang_activity);
        mDb = AppDatabase.getInstance(this);

        datHangRecyclerView = findViewById(R.id.recycler_view_dat_hang);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720)
            datHangRecyclerView.setLayoutManager(new GridLayoutManager(DatHangActivity.this, 2));
        else
            datHangRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        datHangAdapter = new DatHangAdapter(this);
        datHangRecyclerView.setAdapter(datHangAdapter);

        tongTien = findViewById(R.id.tong_so_tien_hang_dat);
        buttonDatHang = findViewById(R.id.dat_hang_button);
        buttonMuaTiep = findViewById(R.id.mua_tiep_button);
        editTextTen = findViewById(R.id.editText_ten);
        editTextSoDt = findViewById(R.id.editText_so_dt);
        editTextDiaChi = findViewById(R.id.editText_dia_chi);
        editTextEmail = findViewById(R.id.editTextText_email);


        DatHangViewModel datHangViewModel = ViewModelProviders.of(this).get(DatHangViewModel.class);
        datHangViewModel.getDatHang().observe(this, new Observer<List<GioHangEntry>>() {
            @Override
            public void onChanged(@Nullable List<GioHangEntry> datHangs) {
                mDatHangs = datHangs;
                assert mDatHangs != null;
                for (int i = 0; i < mDatHangs.size(); i++) {
                    tongtien = mDatHangs.get(i).getGiaSanPham();
                    tongTienDonHang += tongtien;
                }

                DecimalFormat decimalFormat1 = new DecimalFormat("###,###,###");
                tongTien.setText( "Tổng số tiền: " + decimalFormat1.format(tongTienDonHang) + " Đ");
                datHangAdapter.setDatHang(mDatHangs);
            }
        });

        buttonMuaTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMuaTiep = new Intent(DatHangActivity.this, MainActivity.class);
                startActivity(intentMuaTiep);
            }
        });


        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten = editTextTen.getText().toString().trim();
                final String sdt = editTextSoDt.getText().toString().trim();
                final String email = editTextEmail.getText().toString().trim();
                final String diachi = editTextDiaChi.getText().toString().trim();

                if (ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0 && isValidEmail(email)) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Order from " + ten);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "cskh.shoppingsquare@gmail.com" });
                    String noidungtinnhan = "Họ và tên: " + ten + "\nEmail: " + email + "\nSố điện thoại: " + sdt + "\nĐịa chỉ: " + diachi + "\n" + "Danh sách sản phẩm mua: \n";


                    double tongtien = 0;
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    for (int i = 0; i < mDatHangs.size(); i++) {
                        noidungtinnhan += mDatHangs.get(i).getTenSanPham();
                        noidungtinnhan += "   x" + mDatHangs.get(i).getSoLuong();
                        noidungtinnhan += "         " + decimalFormat.format(mDatHangs.get(i).getGiaSanPham() / mDatHangs.get(i).getSoLuong()) + " Đ" + "\n";
                        tongtien += mDatHangs.get(i).getGiaSanPham();
                        tongTienDonHang += tongtien;

                    }
                    noidungtinnhan += ("\nTổng tiền: " + decimalFormat.format(tongtien) + " Đ");
                    intent.putExtra(Intent.EXTRA_TEXT, noidungtinnhan);

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        for (int i = 0; i < mDatHangs.size(); i++) {

                            final int finalI = i;
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {

                                    mDb.gioHangDao().deleteGioHang(mDatHangs.get(finalI));
                                }

                            });
                        }
                        Intent intentMuaTiep = new Intent(DatHangActivity.this, MainActivity.class);
                        startActivity(intentMuaTiep);
                        Toast.makeText(DatHangActivity.this, "Tiến hành gửi Email đặt hàng", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }

                } else {
                    Toast.makeText(DatHangActivity.this, "Thông tin cá nhân còn thiếu hoặc địa chỉ email chưa đúng", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}


