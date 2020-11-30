package com.example.android.demoapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.MainAdapter;
import com.example.android.demoapp.model.Banner;
import com.example.android.demoapp.model.HangSanPham;
import com.example.android.demoapp.utils.CheckConnection;
import com.example.android.demoapp.utils.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    int id, idchinhsach = 0;
    String tenhang, tenchinhsach = "";
    String hinhanhhang, noidungchinhsach = "";
    MainAdapter mainAdapter;
    public static ArrayList<HangSanPham> manghangsanpham;
    ArrayList<Banner> manganhbanner;
    public static ArrayList<Banner> mangchinhsach;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMain;
    ProgressBar mProgressBar;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        viewFlipper = rootView.findViewById(R.id.viewflipper);
        recyclerViewMain = rootView.findViewById(R.id.recycler_view_main);
        manghangsanpham = new ArrayList<>();
        manganhbanner = new ArrayList<>();
        mangchinhsach = new ArrayList<>();

        if (CheckConnection.haveNetworkConnection(getActivity())) {
            mProgressBar = rootView.findViewById(R.id.progress_bar);

            gethangsanpham();
            actionViewFlipper();
            getchinhsach();
            mainAdapter = new MainAdapter(getActivity(), manghangsanpham);
            recyclerViewMain.setHasFixedSize(true);
            recyclerViewMain.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recyclerViewMain.setAdapter(mainAdapter);
            recyclerViewMain.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);


        } else {
            CheckConnection.showToast_Short(getActivity(), "Không có kết nối Internet!");
        }

        return rootView;

    }

    private void getchinhsach() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanchinhsach, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            idchinhsach = object.getInt("id");
                            tenchinhsach = object.getString("tenchinhsach");
                            noidungchinhsach = object.getString("noidung");
                            mangchinhsach.add(new Banner(idchinhsach, tenchinhsach, noidungchinhsach));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);


    }

    private void gethangsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanhangsp, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < 9; i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            id = object.getInt("id");

                            tenhang = object.getString("tenhang");
                            hinhanhhang = object.getString("hinhanhhang");
                            manghangsanpham.add(new HangSanPham(id, tenhang, hinhanhhang));

                            mainAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mainAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void actionViewFlipper() {

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdananhbanner, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            id = object.getInt("id");

                            tenhang = object.getString("tenbanner");
                            hinhanhhang = object.getString("anhbanner");
                            manganhbanner.add(new Banner(id, tenhang, hinhanhhang));
                            ImageView imageView = new ImageView(getContext());
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            Picasso.get().load(hinhanhhang).into(imageView);
                            viewFlipper.addView(imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    viewFlipper.setFlipInterval(10000);
                    viewFlipper.setAutoStart(true);
                    Animation animationSlideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
                    Animation animationSlideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
                    viewFlipper.setInAnimation(animationSlideIn);
                    viewFlipper.setOutAnimation(animationSlideOut);
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

