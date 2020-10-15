package com.example.android.demoapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android.demoapp.fragment.GioHangFragment;
import com.example.android.demoapp.fragment.YeuThichFragment;
import com.example.android.demoapp.fragment.TimKiemFragment;
import com.example.android.demoapp.fragment.MainFragment;

public class CategoryAdapter extends FragmentStateAdapter {
    private Context mContext;

    public CategoryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public  Fragment createFragment(int position) {
        if (position == 0) {
            return new MainFragment();
        } else if (position == 1) {
            return new TimKiemFragment();
        } else if (position == 2) {
            return new YeuThichFragment();


        } else  {
            return new GioHangFragment();

        }
    }




    @Override
    public int getItemCount() {
        return 4;
    }
}
