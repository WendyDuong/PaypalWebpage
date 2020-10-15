package com.example.android.demoapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.demoapp.fragment.CartFragment;
import com.example.android.demoapp.fragment.FavoriteFragment;
import com.example.android.demoapp.fragment.FindFragment;
import com.example.android.demoapp.fragment.MainFragment;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;

  public CategoryAdapter(Context context, FragmentManager fm){
       super(fm);
      mContext = context;

  }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MainFragment();
        } else if (position == 1) {
            return new FindFragment();
        } else if (position == 2) {
            return new FavoriteFragment();
        } else {
            return new CartFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }
}
