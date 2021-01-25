package com.example.android.demoapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.DetailActivity;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.YeuThichEntry;
import com.example.android.demoapp.model.SanPham;
import com.squareup.picasso.Picasso;

import org.apache.commons.math3.util.Precision;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.itemHolder> {
    private List<YeuThichEntry> mYeuThichEntries;
    private AppDatabase mDb;
    Context context;
    private ArrayList<SanPham> sanPhams;
    private int iD;
    private int idHang;
    public static int timDoList;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";
    private static final String EXTRA_ANH_HANG = "extraAnhHang";

    public CatalogAdapter(Context context, ArrayList<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }


    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.san_pham_item, parent, false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(final itemHolder holder, final int position) {

        SanPham sanPham = sanPhams.get(position);
        holder.tvTensanpham.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        double giasp = sanPham.getGiaSanPham();

        //Rounding curency to make a easy reading
        giasp = Precision.round(giasp/1000, 0)*1000;

        if (sanPham.getGiaKhuyenMai() != 0){
            //TODO SALE
            holder.tvGiasanpham.setText(decimalFormat.format(giasp) + " Đ");
            holder.tvGiasanpham.setPaintFlags(holder.tvGiasanpham.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvGiakhuyenmai.setText(sanPham.getGiaKhuyenMai()+ " Đ");
            holder.tvGiakhuyenmai.setVisibility(View.VISIBLE);
        }
        else{
            holder.tvGiakhuyenmai.setVisibility(View.INVISIBLE);
            holder.tvGiasanpham.setText(decimalFormat.format(giasp) + " Đ");
            holder.tvGiasanpham.setPaintFlags(holder.tvGiasanpham.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        Picasso.get().load(sanPham.getHinhAnhSanPham()).into(holder.imgHinhAnhSanpham);
        final int idsanpham = sanPham.getId();

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.cardViewCatalog.getLayoutParams();
        int left = dptoPx(24);
        int top = dptoPx(12);
        int right = dptoPx(24);
        int bottom = dptoPx(12);
        boolean isFirst2Iteme = position < 2;
        boolean isLast2tems = position > getItemCount() - 2;
        if (isFirst2Iteme) {
            top = dptoPx(24);
        }
        if (isLast2tems) {
            bottom = dptoPx(24);
        }

        boolean isLeftSide = (position + 1) % 2 != 0;
        boolean isRightSide = !isLeftSide;

        if (isLeftSide) {
            right = dptoPx(12);
        }
        if (isRightSide) {
            left = dptoPx(12);
        }


        Configuration config = context.getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720)
        {
            boolean isFirst3Iteme = position < 3;
            boolean isLast3tems = position > getItemCount() - 3 ;
            if ( isFirst3Iteme){
                top = dptoPx(24);
            }
            if (isLast3tems){
                bottom = dptoPx(24);
            }

            isLeftSide =  position % 3 == 0;
            isRightSide = ( position + 1) % 3 == 0;

            if (isLeftSide) {
                right = dptoPx(0);
                left = dptoPx(36);
            }
            if(isRightSide) {
                left = dptoPx(0);
                right = dptoPx(36);
            }
            boolean isMiddle = (position + 2) % 3 == 0;
            if ( isMiddle)
            {
                right = dptoPx(18);
                left = dptoPx(18);
            }
        }

        layoutParams.setMargins(left, top, right, bottom);
        holder.cardViewCatalog.setLayoutParams(layoutParams);


        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                boolean exit = false;
                if (mYeuThichEntries.size() > 0) {
                    for (int vitritim = 0; vitritim < mYeuThichEntries.size(); vitritim++) {
                        if (mYeuThichEntries.get(vitritim).getIdSanPham() == idsanpham) {
                            holder.imageViewTim.setImageResource(R.drawable.timdo24);
                            exit = true;
                        }
                    }

                    if (!exit)
                        holder.imageViewTim.setImageResource(R.drawable.timden24);
                } else
                    holder.imageViewTim.setImageResource(R.drawable.timden24);
            }
        });
    }

    private int dptoPx(int dp) {
        float px = dp + context.getResources().getDisplayMetrics().density;
        return (int) px;
    }

    @Override
    public int getItemCount() {
        if (sanPhams == null)
            return 0;
        return sanPhams.size();
    }


    public void setYeuThichs(List<YeuThichEntry> yeuThichEntries){
        this.mYeuThichEntries = yeuThichEntries;
        //notifyDataSetChanged();
    }


    public class itemHolder extends RecyclerView.ViewHolder {
        CardView cardViewCatalog;
        public ImageView imgHinhAnhSanpham, imageViewTim;
        public TextView tvTensanpham;
        public TextView tvGiasanpham;
        public TextView tvGiakhuyenmai;
        public itemHolder(View itemView) {
            super(itemView);
            imgHinhAnhSanpham = itemView.findViewById(R.id.anhsanpham);
            tvTensanpham = itemView.findViewById(R.id.tensanpham);
            tvGiasanpham = itemView.findViewById(R.id.giasanpham);
            tvGiakhuyenmai = itemView.findViewById(R.id.giakhuyenmai);
            cardViewCatalog = itemView.findViewById(R.id.card_view_catalog);
            imageViewTim = itemView.findViewById(R.id.image_view_tim);
            mDb = AppDatabase.getInstance(context);

            imgHinhAnhSanpham.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SanPham sanPham = sanPhams.get(getLayoutPosition());
                    iD = sanPham.getId();
                    idHang = sanPham.getIdHang();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(EXTRA_SANPHAM_ID, iD);
                    intent.putExtra(EXTRA_HANG_ID, idHang);
                    intent.putExtra("CatalogAdapter", "CatalogAdapter" );
                    intent.putExtra("CatalogAdapter",sanPhams.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });

            imageViewTim.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onClick(View view) {
                    if (imageViewTim.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.timden24).getConstantState()) {
                        final int idhang = sanPhams.get(getLayoutPosition()).getIdHang();
                        final int idsanpham = sanPhams.get(getLayoutPosition()).getId();
                        final String tensanpham = sanPhams.get(getLayoutPosition()).getTenSanPham();
                        final double giasanpham = sanPhams.get(getLayoutPosition()).getGiaSanPham();
                        final double giakhuyemai = sanPhams.get(getLayoutPosition()).getGiaKhuyenMai();
                        final String hinhanhsanpham = sanPhams.get(getLayoutPosition()).getHinhAnhSanPham();
                        final String khoiluongsanpham = sanPhams.get(getLayoutPosition()).getKhoiLuong();
                        final String moTa = sanPhams.get(getLayoutPosition()).getMoTa();
                        final String thuongHieu = sanPhams.get(getLayoutPosition()).getThuongHieu();
                        final String xuatXu = sanPhams.get(getLayoutPosition()).getXuatXu();

                        imageViewTim.setImageResource(R.drawable.timdo24);

                        //TODO SALE
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.yeuThichDao().insertYeuThich(new YeuThichEntry(idsanpham, tensanpham, giasanpham, giakhuyemai, hinhanhsanpham, khoiluongsanpham,idhang, moTa, thuongHieu, xuatXu));
                            }
                        });
                    }
                    else {
                        final int idsanpham = sanPhams.get(getLayoutPosition()).getId();
                        imageViewTim.setImageResource(R.drawable.timden24);
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                for (int vitrixoa = 0; vitrixoa < mYeuThichEntries.size(); vitrixoa++) {
                                    int idsanphamxoa = mYeuThichEntries.get(vitrixoa).getIdSanPham() ;
                                    if (idsanphamxoa == idsanpham) {
                                        mDb.yeuThichDao().deleteYeuThich(mYeuThichEntries.get(vitrixoa));
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}