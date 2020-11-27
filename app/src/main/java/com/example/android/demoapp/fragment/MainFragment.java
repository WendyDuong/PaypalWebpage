package com.example.android.demoapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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
import com.example.android.demoapp.model.HangSanPham;
import com.example.android.demoapp.utils.CheckConnection;
import com.example.android.demoapp.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    int id = 0;
    String tenhang = "";
    String hinhanhhang = "";
    MainAdapter mainAdapter;
    ArrayList<HangSanPham> manghangsanpham;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMain;
    public static final ArrayList<String> ImageList = new ArrayList<>();

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
        if (CheckConnection.haveNetworkConnection(getActivity())) {

            gethangsanpham();
            actionViewFlipper();
            mainAdapter = new MainAdapter(getActivity(), ImageList);
            recyclerViewMain.setHasFixedSize(true);
            recyclerViewMain.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recyclerViewMain.setAdapter(mainAdapter);


        } else {
            CheckConnection.showToast_Short(getActivity(), "Không có kết nối Internet!");
            getActivity().finish();
        }

        return rootView;

    }

    private void gethangsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanhangsp, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    ImageList.clear();
                    for (int i = 0; i < 9; i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            id = object.getInt("id");

                            tenhang = object.getString("tenhang");
                            hinhanhhang = object.getString("hinhanhhang");
                            manghangsanpham.add(new HangSanPham(id, tenhang, hinhanhhang));

                            ImageList.add(i, hinhanhhang);
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
        ArrayList<Integer> mangquangcao = new ArrayList<>();
        mangquangcao.add(R.drawable.banner01);
        mangquangcao.add(R.drawable.banner04);

        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mangquangcao.get(i));
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(10000);
        viewFlipper.setAutoStart(true);
        Animation animationSlideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation animationSlideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animationSlideIn);
        viewFlipper.setOutAnimation(animationSlideOut);


    }

}

