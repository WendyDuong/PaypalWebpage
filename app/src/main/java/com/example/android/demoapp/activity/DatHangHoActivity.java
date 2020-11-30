package com.example.android.demoapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.android.demoapp.fragment.MainFragment;
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
    ProgressBar progressBar;

    Context context;

    ArrayList<HangSanPham> manghangsanpham;
    DatHangHoAdapter datHangHoAdapter;
    RecyclerView recyclerDatHangHo;
    public static final ArrayList<String> ImageList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang_ho);
        Toolbar toolbar = findViewById(R.id.toolbar_tim_do);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(MainFragment.mangchinhsach.get(2).getTenbanner());

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        TextView tv = findViewById(R.id.tv_dat_hang_ho_thong_tin);


        recyclerDatHangHo = findViewById(R.id.recycler_view_dathangho);
        manghangsanpham = new ArrayList<>();
        if (CheckConnection.haveNetworkConnection(DatHangHoActivity.this)) {

            progressBar = findViewById(R.id.progress_bar);
            tv.setText(MainFragment.mangchinhsach.get(2).getAnhbanner());
            gethangsanpham();


        datHangHoAdapter = new DatHangHoAdapter(DatHangHoActivity.this, ImageList);
        recyclerDatHangHo.setHasFixedSize(true);
        recyclerDatHangHo.setLayoutManager(new GridLayoutManager(DatHangHoActivity.this,3));
        recyclerDatHangHo.setAdapter(datHangHoAdapter);
        recyclerDatHangHo.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        } else {
            CheckConnection.showToast_Short(DatHangHoActivity.this, "Không có kết nối Internet!");
            DatHangHoActivity.this.finish();
        }




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
