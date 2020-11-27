package com.example.android.demoapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.DatHangHoAdapter;
import com.example.android.demoapp.model.HangSanPham;
import com.example.android.demoapp.utils.CheckConnection;
import com.example.android.demoapp.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DatHangHoActivity extends AppCompatActivity {
    int id = 0;
    String tenhang = "";
    String hinhanhhang = "";

    Context context;

    ArrayList<HangSanPham> manghangsanpham;
    DatHangHoAdapter datHangHoAdapter;
    RecyclerView recyclerDatHangHo;
    public static final ArrayList<String> ImageList = new ArrayList<>();

/*
    public static final List<Integer> ImageList = new ArrayList<Integer>() {{
        add(R.drawable.logo_adidas);
        add(R.drawable.logo_nike);
        add(R.drawable.logo_amazon);
        add(R.drawable.logo_bershka);
        add(R.drawable.logo_ca);
        add(R.drawable.logo_esprit);
        add(R.drawable.logo_hm);
        add(R.drawable.logo_mango);
        add(R.drawable.logo_massimodutti);
        add(R.drawable.logo_zara);
        add(R.drawable.logo_pb);
        add(R.drawable.logo_douglas);
        add(R.drawable.logo_wmf);
        add(R.drawable.logo_silit);
        add(R.drawable.muller);
        add(R.drawable.logo_dm);
        add(R.drawable.logo_saturn);
        add(R.drawable.apotheke_logo);
        add(R.drawable.rossmann_logo);
        add(R.drawable.worldofsweet);
        add(R.drawable.mediamarkt_logo);
    }};
*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang_ho);
        recyclerDatHangHo = findViewById(R.id.recycler_view_dathangho);
        manghangsanpham = new ArrayList<>();
        if (CheckConnection.haveNetworkConnection(DatHangHoActivity.this)) {
            gethangsanpham();

        datHangHoAdapter = new DatHangHoAdapter(DatHangHoActivity.this, ImageList);
        recyclerDatHangHo.setHasFixedSize(true);
        recyclerDatHangHo.setLayoutManager(new GridLayoutManager(DatHangHoActivity.this,3));
        recyclerDatHangHo.setAdapter(datHangHoAdapter);

        } else {
            CheckConnection.showToast_Short(DatHangHoActivity.this, "Không có kết nối Internet!");
        }

        Toolbar toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Đặt hàng theo yêu cầu");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        TextView tv = findViewById(R.id.tv_dat_hang_ho_thong_tin);
        tv.setText(R.string.dat_hang_ho);

    }
    private void gethangsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue(DatHangHoActivity.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanhangsp, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    ImageList.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            id = object.getInt("id");

                            tenhang = object.getString("tenhang");
                            hinhanhhang = object.getString("hinhanhhang");
                            manghangsanpham.add(new HangSanPham(id, tenhang, hinhanhhang));

                            ImageList.add(i, hinhanhhang);
                            datHangHoAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    datHangHoAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

}
