package com.example.android.demoapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.DatHangViewModel;
import com.example.android.demoapp.adapter.DatHangAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.utils.CheckConnection;
import com.example.android.demoapp.utils.Server;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatHangActivity extends AppCompatActivity {
    DatHangAdapter datHangAdapter;
    List<GioHangEntry> mDatHangs;
    TextView tongTien;
    double tongtien;
    RecyclerView datHangRecyclerView;
    EditText editTextTen, editTextSoDt, editTextDiaChi, editTextEmail;
    Button buttonDatHang, buttonMuaTiep;
    private double tongTienDonHang = 0;
    private AppDatabase mDb;

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

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
                tongTien.setText("Tổng số tiền: " + decimalFormat1.format(tongTienDonHang) + " Đ");
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

                if (CheckConnection.haveNetworkConnection(DatHangActivity.this)) {

                    if (ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0 && isValidEmail(email)) {

                        final RequestQueue requestQueue = Volley.newRequestQueue(DatHangActivity.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String madonhang) {
                                if (Integer.parseInt(madonhang) > 0) {
                                    RequestQueue requestQueue1 = Volley.newRequestQueue(DatHangActivity.this);
                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equals("1")) {
                                                for (int i = 0; i < mDatHangs.size(); i++) {

                                                    final int finalI = i;
                                                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mDb.gioHangDao().deleteGioHang(mDatHangs.get(finalI));
                                                        }

                                                    });
                                                }
                                                Toast.makeText(DatHangActivity.this, "Bạn đã đặt hàng thành công, Shopping Square sẽ liên lạc để hoàn tất thanh toán!", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(DatHangActivity.this, MainActivity.class));
                                                Toast.makeText(DatHangActivity.this, "Mời bạn tiếp tục mua hàng", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(DatHangActivity.this, "Lỗi không tiến hành đặt hàng được!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                        }
                                    }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            JSONArray jsonArray = new JSONArray();
                                            for (int i = 0; i < mDatHangs.size(); i++) {
                                                JSONObject jsonObject = new JSONObject();
                                                try {
                                                    jsonObject.put("madonhang", madonhang);
                                                    jsonObject.put("masanpham", mDatHangs.get(i).getId());
                                                    jsonObject.put("tensanpham", mDatHangs.get(i).getTenSanPham());
                                                    jsonObject.put("giasanpham", mDatHangs.get(i).getGiaSanPham());
                                                    jsonObject.put("soluongsanpham", mDatHangs.get(i).getSoLuong());
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                jsonArray.put(jsonObject);
                                            }
                                            HashMap<String, String> hashMap = new HashMap<String, String>();
                                            hashMap.put("json", jsonArray.toString());
                                            return hashMap;
                                        }
                                    };
                                    requestQueue1.add(stringRequest1);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("tenkhachhang", ten);
                                hashMap.put("sodienthoai", sdt);
                                hashMap.put("email", email);
                                hashMap.put("diachi", diachi);

                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);

                    } else {
                        Toast.makeText(DatHangActivity.this, "Thông tin cá nhân còn thiếu hoặc địa chỉ email chưa đúng", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(DatHangActivity.this, "Kiểm tra kết nối internet!", Toast.LENGTH_SHORT).show();

                }

/*
                if (ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0 && isValidEmail(email)) {
                    final RequestQueue requestQueue = Volley.newRequestQueue(DatHangActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Toast.makeText(DatHangActivity.this, "madonhang"+madonhang, Toast.LENGTH_SHORT).show();

                            if (Integer.parseInt(madonhang) > 0) {
                                Toast.makeText(DatHangActivity.this, "madonhang > 0", Toast.LENGTH_SHORT).show();

                                RequestQueue requestQueue1 = Volley.newRequestQueue(DatHangActivity.this);
                                Toast.makeText(DatHangActivity.this, " make new chitietdonhang request", Toast.LENGTH_SHORT).show();

                                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(DatHangActivity.this, "response"+response, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(DatHangActivity.this, "response chitietdh"+response, Toast.LENGTH_SHORT).show();


                                        if (response.equals("1")) {
                                            Toast.makeText(DatHangActivity.this, "response sucess", Toast.LENGTH_SHORT).show();

                                            for (int i = 0; i < mDatHangs.size(); i++) {

                                                final int finalI = i;
                                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        mDb.gioHangDao().deleteGioHang(mDatHangs.get(finalI));
                                                        Toast.makeText(DatHangActivity.this, "xoa gio hang"+finalI, Toast.LENGTH_SHORT).show();

                                                    }

                                                });
                                            }
                                            Toast.makeText(DatHangActivity.this, "Bạn đã thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(DatHangActivity.this, MainActivity.class));
                                            Toast.makeText(DatHangActivity.this, "Mời bạn tiếp tục mua hàng", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(DatHangActivity.this, "Lỗi không tiến hành đặt hàng được, mời kiểm tra kết nối internet!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(DatHangActivity.this, "ko có response chitietdh", Toast.LENGTH_SHORT).show();


                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Toast.makeText(DatHangActivity.this, " get param", Toast.LENGTH_SHORT).show();

                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i < mDatHangs.size(); i++) {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang", madonhang);
                                                jsonObject.put("masanpham", mDatHangs.get(i).getId());
                                                jsonObject.put("tensanpham", mDatHangs.get(i).getTenSanPham());
                                                jsonObject.put("giasanpham", mDatHangs.get(i).getGiaSanPham());
                                                jsonObject.put("soluongsanpham", mDatHangs.get(i).getSoLuong());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("json", jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                requestQueue1.add(stringRequest1);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DatHangActivity.this, "ko có response don hang", Toast.LENGTH_SHORT).show();


                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Toast.makeText(DatHangActivity.this, "put don hang", Toast.LENGTH_SHORT).show();

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("tenkhachhang", ten);
                            hashMap.put("sodienthoai", sdt);
                            hashMap.put("email", email);
                            hashMap.put("diachi", diachi);

                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(DatHangActivity.this, "Thông tin còn thiếu hoặc địa chỉ email chưa đúng", Toast.LENGTH_SHORT).show();
                }
*/


            }
        });


    }

}


