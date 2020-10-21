package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.android.demoapp.database.SanPhamEntry;
import com.example.android.demoapp.database.YeuThichEntry;

import java.text.DecimalFormat;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.itemHolder> {
    private List<YeuThichEntry> mYeuThichEntries;
    private AppDatabase mDb;

    Context context;
    private List<SanPhamEntry> sanPhams;
    private int iD;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private int idHang;
    private static final String EXTRA_HANG_ID = "extraHangId";

    public CatalogAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.san_pham_item, null);
        itemHolder itemHolder = new itemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(final itemHolder holder, final int position) {
        SanPhamEntry sanPham = sanPhams.get(position);
        holder.tvTensanpham.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiasanpham.setText("Giá :" + decimalFormat.format(sanPham.getGiaSanPham()) + " Đ");
        holder.imgHinhAnhSanpham.setImageResource(sanPham.getHinhAnh());
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

        layoutParams.setMargins(left, top, right, bottom);
        holder.cardViewCatalog.setLayoutParams(layoutParams);


        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb = AppDatabase.getInstance(context);
                mYeuThichEntries = mDb.yeuThichDao().loadDanhSachYeuThich();
                boolean exit = false;

                if (mYeuThichEntries.size() > 0) {
                    for (int vitritim = 0; vitritim < mYeuThichEntries.size(); vitritim++) {
                        if (mYeuThichEntries.get(vitritim).getIdSanPham() == idsanpham) {
                            holder.imageViewTim.setImageResource(R.drawable.timdo24);
                            exit = true;
                        }
                    }

                    if (exit == false)
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


    public List<SanPhamEntry> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPhamEntry> sanPhams) {
        this.sanPhams = sanPhams;
        notifyDataSetChanged();

    }

    public class itemHolder extends RecyclerView.ViewHolder {
        CardView cardViewCatalog;
        public ImageView imgHinhAnhSanpham, imageViewTim;
        public TextView tvTensanpham;
        public TextView tvGiasanpham;
        public TextView tvDaban;

        public itemHolder(View itemView) {
            super(itemView);
            imgHinhAnhSanpham = itemView.findViewById(R.id.anhsanpham);
            tvTensanpham = itemView.findViewById(R.id.tensanpham);
            tvGiasanpham = itemView.findViewById(R.id.giasanpham);
            tvDaban = itemView.findViewById(R.id.da_ban);
            cardViewCatalog = itemView.findViewById(R.id.card_view_catalog);
            imageViewTim = itemView.findViewById(R.id.image_view_tim);


            imgHinhAnhSanpham.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SanPhamEntry sanPham = sanPhams.get(getLayoutPosition());
                    iD = sanPham.getId();
                    idHang = sanPham.getIdHang();
                    Log.i("MainGridViewAdapter", Integer.toString(iD));

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(EXTRA_SANPHAM_ID, iD);
                    intent.putExtra(EXTRA_HANG_ID, idHang);
                    context.startActivity(intent);

                }
            });

            imageViewTim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (imageViewTim.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.timden24).getConstantState()) {
                        final int idhang = sanPhams.get(getLayoutPosition()).getIdHang();
                        final int idsanpham = sanPhams.get(getLayoutPosition()).getId();
                        final String tensanpham = sanPhams.get(getLayoutPosition()).getTenSanPham();
                        final double giasanpham = sanPhams.get(getLayoutPosition()).getGiaSanPham();
                        final int hinhanhsanpham = sanPhams.get(getLayoutPosition()).getHinhAnh();
                        final String khoiluongsanpham = sanPhams.get(getLayoutPosition()).getKhoiLuong();
                        imageViewTim.setImageResource(R.drawable.timdo24);



                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb = AppDatabase.getInstance(context);
                                    mDb.yeuThichDao().insertYeuThich(new YeuThichEntry(idsanpham, tensanpham, giasanpham, hinhanhsanpham, khoiluongsanpham,idhang ));
                                    Log.d("checkidsanphaminsert", " "+ idsanpham);

                                }
                            });


                        }
                     else {
                        final int idsanpham = sanPhams.get(getLayoutPosition()).getId();

                        imageViewTim.setImageResource(R.drawable.timden24);

                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb = AppDatabase.getInstance(context);
                                mYeuThichEntries = mDb.yeuThichDao().loadDanhSachYeuThich();
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



















