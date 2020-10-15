package com.example.android.demoapp.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android.demoapp.View.DangNhap.Fragment.FragmentDangKy;
import com.example.android.demoapp.View.DangNhap.Fragment.FragmentDangNhap;


/**
 * Created by Lenovo S410p on 6/29/2016.
 */
public class ViewPagerAdaterDangNhap extends FragmentStateAdapter {

    public ViewPagerAdaterDangNhap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FragmentDangNhap();
        }
        return new FragmentDangKy();

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}