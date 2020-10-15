package com.example.android.demoapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.DatHangViewModel;
import com.example.android.demoapp.adapter.DatHangAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;

import java.text.DecimalFormat;
import java.util.List;

public class DatHangActivity extends AppCompatActivity{
    DatHangAdapter datHangAdapter;
    List<GioHangEntry> mDatHangs;

    private static final String TAG = DatHangActivity.class.getSimpleName();
   TextView tongTien;
   private double tongTienDonHang = 0;
    double tongtien;
    private AppDatabase mDb;


    RecyclerView datHangRecyclerView;
    EditText editTextTen,editTextSoDt, editTextDiaChi, editTextEmail;
    Button buttonDatHang, buttonMuaTiep;
    private static final int TASK_LOADER_ID = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat_hang_activity);
        mDb = AppDatabase.getInstance(this);

        datHangRecyclerView = findViewById(R.id.recycler_view_dat_hang);
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
                for ( int i = 0; i< mDatHangs.size(); i++) {
                    tongtien = mDatHangs.get(i).getGiaSanPham();
                    tongTienDonHang += tongtien;
                }
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                tongTien.setText( "Tổng số tiền: "+decimalFormat1.format(tongTienDonHang)+" Đ");

                Log.d(TAG, "load data in List dat hang");
                datHangAdapter.setDatHang(mDatHangs);
            }
        });

        buttonMuaTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intentMuaTiep = new Intent(DatHangActivity.this , MainActivity.class );
             startActivity(intentMuaTiep);

                         }
        });






      /* buttonDatHang.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             final String ten = editTextTen.getText().toString().trim();
             final String sdt = editTextSoDt.getText().toString().trim();
             final String email = editTextEmail.getText().toString().trim();
             final String diachi = editTextDiaChi.getText().toString().trim();
             if (ten.length() > 0 && sdt.length() >0 && email.length() >0 && diachi.length() > 0){
                 RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                 StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                     @Override
                     public void onResponse(final String madonhang) {
                        Log.d("madonhang",madonhang);
                        if(Integer.parseInt(madonhang)>0){
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest request = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.equals("1")){
                                    MainActivity.mangGioHang.clear();
                                    Toast.makeText(DatHangActivity.this, "Bạn đã đặt hàng thành công",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }else {
                                        Toast.makeText(DatHangActivity.this, "Dữ liệu giỏ hàng bị lỗi",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    JSONArray jsonArray = new JSONArray();
                                    for ( int i = 0 ; i < MainActivity.mangGioHang.size(); i++) {
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("madonhang", madonhang);
                                            jsonObject.put("masanpham", MainActivity.mangGioHang.get(i).getIdsp());
                                            jsonObject.put("tensanpham", MainActivity.mangGioHang.get(i).getTensp());
                                            jsonObject.put("giasanpham", MainActivity.mangGioHang.get(i).getGiasp());
                                            jsonObject.put("soluongsanpham", MainActivity.mangGioHang.get(i).getSoluongsp());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject);
                                    }

                                    HashMap<String,String> hashMap = new HashMap<String,String>();
                                    hashMap.put("json",jsonArray.toString());
                                    return hashMap;


                                }
                            };
                            queue.add(request);


                        }
                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {

                     }
                 }){
                     @Override
                     protected Map<String, String> getParams() throws AuthFailureError {
                         HashMap<String,String> hashMap = new HashMap<String,String>();
                         hashMap.put("tenkhachhang",ten);
                         hashMap.put("sodienthoai",sdt);
                         hashMap.put("email",email);
                         hashMap.put("diachi",diachi);

                         return hashMap;
                     }
                 };
                 requestQueue.add(stringRequest);


             }else{
                 Toast.makeText(DatHangActivity.this, "Thông tin cá nhân còn thiếu", Toast.LENGTH_SHORT).show();

             }


         }
     }); */

   buttonDatHang.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final String ten = editTextTen.getText().toString().trim();
              final String sdt = editTextSoDt.getText().toString().trim();
              final String email = editTextEmail.getText().toString().trim();
              final String diachi = editTextDiaChi.getText().toString().trim();
              if (ten.length() > 0 && sdt.length() >0 && email.length() >0 && diachi.length() > 0){
                  Intent intent = new Intent(Intent.ACTION_SENDTO);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  intent.setData(Uri.parse("mailto:"));
                  intent.putExtra(Intent.EXTRA_SUBJECT, "Order from " + ten);
                  String noidungtinnhan="Họ và tên: " + ten +"\nSố điện thoại: " + sdt + "\nĐịa chỉ: " + diachi + "\n" + "Danh sách sản phẩm mua: \n";


                  double tongtien=0;
                  DecimalFormat decimalFormat =  new  DecimalFormat("###,###,###");
                  for ( int i = 0; i< mDatHangs.size(); i++) {
                      noidungtinnhan += mDatHangs.get(i).getTenSanPham();
                      noidungtinnhan += "   x" + mDatHangs.get(i).getSoLuong();
                      noidungtinnhan += "         " +  decimalFormat.format(mDatHangs.get(i).getGiaSanPham()/mDatHangs.get(i).getSoLuong()) + " Đ" +"\n";
                      tongtien += mDatHangs.get(i).getGiaSanPham();
                      tongTienDonHang += tongtien;

                  }


                  noidungtinnhan += ( "\nTổng tiền: " + decimalFormat.format(tongtien)+" Đ");

                  intent.putExtra(Intent.EXTRA_TEXT, noidungtinnhan);

                  if(intent.resolveActivity(getPackageManager())!=null)
                  {
                      for ( int i = 0; i< mDatHangs.size(); i++) {

                          final int finalI = i;
                          AppExecutors.getInstance().diskIO().execute(new Runnable() {
                          @Override
                          public void run() {

                              mDb.gioHangDao().deleteGioHang(mDatHangs.get(finalI));
                          }

                      });
                          }
                      Intent intentMuaTiep = new Intent(DatHangActivity.this , MainActivity.class );
                      startActivity(intentMuaTiep);
                      Toast.makeText(DatHangActivity.this, "Tiến hành gửi Email đặt hàng",Toast.LENGTH_SHORT).show();
                      startActivity(intent);

                  }

              }
              else{
              Toast.makeText(DatHangActivity.this, "Thông tin cá nhân còn thiếu", Toast.LENGTH_SHORT).show();

          }
          }
      });


    }

    }


