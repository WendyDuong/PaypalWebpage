package com.example.android.demoapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.activity.GiaoHang;
import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.DatHangHoActivity;
import com.example.android.demoapp.activity.LienHe;
import com.example.android.demoapp.activity.ThanhToan;

import java.util.ArrayList;
import java.util.List;

public class NavigationViewAdapter extends RecyclerView.Adapter<NavigationViewAdapter.myViewHolder> {
    Context context;
    List<String> mData;

    public static final List<Integer> ImageList = new ArrayList<Integer>() {{
        add(R.drawable.ic_baseline_payment_24);
        add(R.drawable.ic_baseline_local_shipping_24);
        add(R.drawable.ic_baseline_assignment_return_24);
        add(R.drawable.ic_baseline_contact_mail_24);
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
        holder.iconView.setImageResource(ImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView nav;
        ImageView iconView;

        public myViewHolder(final View itemView) {
            super(itemView);
            nav = (TextView) itemView.findViewById(R.id.nav);
            iconView = itemView.findViewById(R.id.icon_image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getLayoutPosition();
                    switch (pos) {
                        case 0:
                            Intent intentThanhToan = new Intent(context, ThanhToan.class);
                            context.startActivity(intentThanhToan);
                            break;
                        case 1:
                            Intent intentGiaoHang = new Intent(context, GiaoHang.class);
                            context.startActivity(intentGiaoHang);
                            break;
                        case 2:
                            Intent intentDoiTra = new Intent(context, DatHangHoActivity.class);
                            context.startActivity(intentDoiTra);
                            break;
                        case 3:
                            Intent intentLienHe = new Intent(context, LienHe.class);
                            context.startActivity(intentLienHe);
                            break;
                    }
                }
            });
        }
    }
}

