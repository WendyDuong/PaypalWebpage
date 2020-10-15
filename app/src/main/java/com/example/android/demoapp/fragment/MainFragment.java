package com.example.android.demoapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {


    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMain;
    public static final List<Integer> ImageList = new ArrayList<Integer>() {{
        add(R.drawable.hit);
        add(R.drawable.merc);
        add(R.drawable.penny);
        add(R.drawable.muller);
        add(R.drawable.dell);
        add(R.drawable.audi);
        add(R.drawable.dior);
        add(R.drawable.skii);
        add(R.drawable.richy);

    }};

    public MainFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        viewFlipper = rootView.findViewById(R.id.viewflipper);
        recyclerViewMain = rootView.findViewById(R.id.recycler_view_main);
        actionViewFlipper();
        MainAdapter mainAdapter = new MainAdapter(getActivity(), ImageList);
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerViewMain.setAdapter(mainAdapter);


        return rootView;

    }

    private void actionViewFlipper() {
        ArrayList<Integer> mangquangcao = new ArrayList<>();
        mangquangcao.add(R.drawable.banner01);
        mangquangcao.add(R.drawable.banner02);
        mangquangcao.add(R.drawable.banner03);
        mangquangcao.add(R.drawable.banner04);
        mangquangcao.add(R.drawable.banner05);

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

