package com.example.android.demoapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.TimKiemActivity;


public class FindFragment extends Fragment {

  Button buttonTimKiem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_find, container, false);
        buttonTimKiem  = rootView.findViewById(R.id.button_tim_kiem);
        buttonTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itentTimKiem = new Intent(getActivity(), TimKiemActivity.class);
                startActivity(itentTimKiem);
            }
        });


        return rootView;
    }
}