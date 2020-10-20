package com.example.android.demoapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationViewAdapter extends RecyclerView.Adapter<NavigationViewAdapter.myViewHolder> {
    Context context;
    List<String> mData;
    public static final List<Integer> ImageList = new ArrayList<Integer>() {{
        add();
        add(R.drawable.merc);
        add(R.drawable.penny);
        add(R.drawable.muller);
        add(R.drawable.dell);
        add(R.drawable.audi);
        add(R.drawable.dior);
        add(R.drawable.skii);
        add(R.drawable.richy);

    }};

    public NavigationViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public NavigationViewAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.navigation_recyclerview, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NavigationViewAdapter.myViewHolder holder, int position) {
        holder.nav.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView nav;

        public myViewHolder(View itemView) {
            super(itemView);
            nav = (TextView) itemView.findViewById(R.id.nav);
        }
    }
}

