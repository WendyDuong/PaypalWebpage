package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.CatalogActivity;
import com.example.android.demoapp.model.HangSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends  RecyclerView.Adapter<MainAdapter.viewHolderMain> {
    private Context mContext;
    //private List<String> anhhangsp;
    private ArrayList<HangSanPham> manghangsanpham;
    private int iD;
    private String anhHang;
    private static final String EXTRA_HANG_ID = "extraHangId";
    private static final String EXTRA_ANH_HANG = "extraAnhHang";

    public MainAdapter(Context context, ArrayList<HangSanPham> manghangsanpham) {
        mContext = context;
        this.manghangsanpham = manghangsanpham;
    }

    @NonNull
    @Override
    public MainAdapter.viewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, parent, false);
        return new viewHolderMain(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.viewHolderMain holder, int position) {
        Picasso.get().load(manghangsanpham.get(position).getAnhHang()).error(R.drawable.error).into(holder.imageView);

        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.cardView.getLayoutParams();
        int left = dptoPx(24);
        int top = dptoPx(12);
        int right = dptoPx(24);
        int bottom = dptoPx(12);

        boolean isFirst3Iteme = position < 3;
        boolean isLast3tems = position > getItemCount() - 3 ;
        if ( isFirst3Iteme){
            top = dptoPx(24);
        }
        if (isLast3tems){
            bottom = dptoPx(24);
        }

        boolean isLeftSide =  (position + 3 ) % 3 == 0;
        boolean isRightSide = ( position + 1) % 3 == 0;

        if (isLeftSide) {
            right = dptoPx(0);
            left = dptoPx(24);
        }
        if(isRightSide) {
            left = dptoPx(0);
            right = dptoPx(24);
        }
        boolean isMiddle = (position + 2) % 3 == 0;
        if ( isMiddle)
        {
            right = dptoPx(12);
            left = dptoPx(12);
        }
        layoutParams.setMargins(left,top,right,bottom);
        holder.cardView.setLayoutParams(layoutParams);
    }

    private int dptoPx(int dp){
    float px = dp + mContext.getResources().getDisplayMetrics().density;
    return (int) px;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public int getItemCount() {
        return manghangsanpham.size();
    }

    class viewHolderMain extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;

        public viewHolderMain(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_grid_view);
            cardView = view.findViewById(R.id.card_view_main);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iD = getLayoutPosition();
                    anhHang = manghangsanpham.get(iD).getAnhHang();
                    Intent intent = new Intent(mContext, CatalogActivity.class);
                    intent.putExtra(EXTRA_HANG_ID, iD);
                    intent.putExtra(EXTRA_ANH_HANG, anhHang);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
