package com.example.android.demoapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.activity.GiaoHangActivity;
import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.LienHeActivity;
import com.example.android.demoapp.activity.SettingsActivity;
import com.example.android.demoapp.activity.ThanhToanActivity;
import com.example.android.demoapp.model.ChinhSach;
import com.example.android.demoapp.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class NavigationViewAdapter extends RecyclerView.Adapter<NavigationViewAdapter.myViewHolder>{
    Context context;
    String language;
    ArrayList<ChinhSach> chinhSach;


    public static final List<Integer> ImageList = new ArrayList<Integer>() {{
        add(R.drawable.ic_baseline_payment_24);
        add(R.drawable.ic_baseline_local_shipping_24);
        add(R.drawable.ic_baseline_language_24);
        add(R.drawable.ic_baseline_contact_mail_24);


    }};

    public NavigationViewAdapter(Context context, String language,ArrayList<ChinhSach> chinhSach) {
        this.context = context;
        this.language = language;
        this.chinhSach = chinhSach;
    }

    @Override
    public NavigationViewAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.navigation_recyclerview, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NavigationViewAdapter.myViewHolder holder, int position) {
        switch (language){
            case "de":
                holder.nav.setText(chinhSach.get(position).getTenchinhsachDE());
                break;
            case "vn" :
                holder.nav.setText(chinhSach.get(position).getTenchinhsach());
                break;
        }
        holder.iconView.setImageResource(ImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return chinhSach.size();
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
                    if (CheckConnection.haveNetworkConnection(context)) {

                        int pos = getLayoutPosition();
                        switch (pos) {
                            case 0:
                                Intent intentThanhToan = new Intent(context, ThanhToanActivity.class);
                                context.startActivity(intentThanhToan);
                                break;
                            case 1:
                                Intent intentGiaoHang = new Intent(context, GiaoHangActivity.class);
                                context.startActivity(intentGiaoHang);
                                break;
                            case 2:
                                Intent intentNgonNgu = new Intent(context, SettingsActivity.class);
                                context.startActivity(intentNgonNgu);
                                break;
                            case 3:
                                Intent intentLienHe = new Intent(context, LienHeActivity.class);
                                context.startActivity(intentLienHe);
                                break;
                        }

                    } else {
                        CheckConnection.showToast_Short(context, "No internet connection!");

                    }
                }
            });
        }
    }
}

