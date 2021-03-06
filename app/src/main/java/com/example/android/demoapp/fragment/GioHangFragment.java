package com.example.android.demoapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.YeuThichViewModel;
import com.example.android.demoapp.activity.DatHangActivity;
import com.example.android.demoapp.adapter.GioHangAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;

import java.text.DecimalFormat;
import java.util.List;


public class GioHangFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    Button buttonDatHang;
    RecyclerView giohangRecyclerView;
    View emptyView;
    TextView tvEmptyTilte;
    GioHangAdapter gioHangAdapter;
    @SuppressLint("StaticFieldLeak")
    public static TextView tvTongtien;
    private AppDatabase mDb;
    private double tongTienDonHang = 0;
    List<GioHangEntry> mGiohangs;
    double tongtien;
    YeuThichViewModel gioHangViewModel;
    String tongtienText;

    public GioHangFragment() {
        // Required empty public constructor
    }

    String language;

    @Override
    public void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.settings_language_key))){

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

            language = sharedPrefs.getString(
                    getString(R.string.settings_language_key),
                    getString(R.string.settings_language_default)
            );

            //Setting language
            switch (language) {
                case "de":
                    tvEmptyTilte.setText(R.string.empty_view_title_gio_hang_de);
                    buttonDatHang.setText(R.string.order_de);
                    tongtienText = getResources().getString(R.string.summe_de);
                    break;
                case "vn":
                    tvEmptyTilte.setText(R.string.empty_view_title_gio_hang);
                    buttonDatHang.setText(R.string.order_vn);
                    tongtienText = getResources().getString(R.string.summe_vn);
                    break;
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDb = AppDatabase.getInstance(getActivity());

        final View rootView = inflater.inflate(R.layout.gio_hang_fragment, container, false);

        //Get preference values
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        language = sharedPrefs.getString(
                getString(R.string.settings_language_key),
                getString(R.string.settings_language_default)
        );

        buttonDatHang = rootView.findViewById(R.id.button_dat_hang);
        emptyView = rootView.findViewById(R.id.empty_view);
        tvEmptyTilte = rootView.findViewById(R.id.empty_title_text);
        gioHangAdapter = new GioHangAdapter(getActivity(), language);
        tvTongtien = rootView.findViewById(R.id.tong_tien);
        giohangRecyclerView = rootView.findViewById(R.id.recycler_view_gio_hang);


        //Setting language
        switch (language) {
            case "de":
                tvEmptyTilte.setText(R.string.empty_view_title_gio_hang_de);
                buttonDatHang.setText(R.string.order_de);
                tongtienText = getResources().getString(R.string.summe_de);

                break;
            case "vn":
                tvEmptyTilte.setText(R.string.empty_view_title_gio_hang);
                buttonDatHang.setText(R.string.order_vn);
                tongtienText = getResources().getString(R.string.summe_vn);


                break;
        }
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720)
        {
            giohangRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        else
        {
            giohangRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        giohangRecyclerView.setAdapter(gioHangAdapter);

        gioHangViewModel = ViewModelProviders.of(this).get(YeuThichViewModel.class);
        gioHangViewModel.getGioHang().observe(getViewLifecycleOwner(), new Observer<List<GioHangEntry>>() {
            @Override
            public void onChanged(@Nullable List<GioHangEntry> gioHangEntries) {
                tvTongtien = rootView.findViewById(R.id.tong_tien);
                mGiohangs = gioHangEntries;
                assert mGiohangs != null;
                if (mGiohangs.size() == 0) {
                    tvTongtien.setVisibility(View.INVISIBLE);
                    emptyView.setVisibility(View.VISIBLE);

                }else {
                    tvTongtien.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.INVISIBLE);

                    //TODO SALE
                    for (int i = 0; i < mGiohangs.size(); i++) {
                        if (mGiohangs.get(i).getGiaKhuyenMai() != 0){
                            tongtien = mGiohangs.get(i).getGiaKhuyenMai();
                        }else {
                            tongtien = mGiohangs.get(i).getGiaSanPham();
                        }
                        tongTienDonHang += tongtien;
                    }

                    // rounding number in case 3.19x6 = 19.13999999
                    double gialamtron = Math.round(tongTienDonHang * 100.0) / 100.0;
                    tvTongtien.setText(tongtienText + ": €"+ gialamtron);
                    tongTienDonHang = 0;
                }
                gioHangAdapter.setGioHangs(gioHangEntries);

            }
        });


        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(giohangRecyclerView);
        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGiohangs.size() > 0) {
                    Intent intent = new Intent(getActivity(), DatHangActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Bạn chưa có sản phẩm nào trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    int position = viewHolder.getAdapterPosition();
                    List<GioHangEntry> gioHangs = gioHangAdapter.getGioHangs();
                    mDb.gioHangDao().deleteGioHang(gioHangs.get(position));
                }
            });
        }
    };

}