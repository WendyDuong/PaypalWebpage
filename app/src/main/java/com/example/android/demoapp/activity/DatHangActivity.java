package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.DatHangAdapter;
import com.example.android.demoapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DatHangActivity extends AppCompatActivity {
    DatHangAdapter datHangAdapter;
    ListView datHangListView;
    EditText editTextTen,editTextSoDt, editTextDiaChi, editTextEmail;
    Button buttonDatHang, buttonMuaTiep;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat_hang_activity);
        datHangListView = findViewById(R.id.list_view_dat_hang);
        datHangAdapter = new DatHangAdapter(this, MainActivity.mangGioHang);
        datHangListView.setAdapter(datHangAdapter);
        buttonDatHang = findViewById(R.id.dat_hang_button);
        buttonMuaTiep = findViewById(R.id.mua_tiep_button);
        editTextTen = findViewById(R.id.editText_ten);
        editTextSoDt = findViewById(R.id.editText_so_dt);
        editTextDiaChi = findViewById(R.id.editText_dia_chi);
        editTextEmail = findViewById(R.id.editTextText_email);

        buttonMuaTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intentMuaTiep = new Intent(DatHangActivity.this , MainActivity.class );
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
     });

    }
}
