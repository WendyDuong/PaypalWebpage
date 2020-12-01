package com.example.android.demoapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ILoadMore;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.CatalogActivity;
import com.example.android.demoapp.activity.DetailActivity;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.SanPhamEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.example.android.demoapp.model.SanPham;
import com.example.android.demoapp.utils.Server;
import com.squareup.picasso.Picasso;

import org.apache.commons.math3.util.Precision;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<YeuThichEntry> mYeuThichEntries;
    private AppDatabase mDb;
    Context context;
    private ArrayList<SanPham> sanPhams;
    private int iD;
    private int idHang;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";
    private static final String EXTRA_ANH_HANG = "extraAnhHang";
    private final int VIEW_TYPE_ITEM = 0,VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;
    boolean isLoading;
    public static int visibleThreshold= 3;
    int lastVisibleItem,totalItemCount;



    public CatalogAdapter(RecyclerView recyclerView, Context context, ArrayList<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;

        final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = gridLayoutManager.getItemCount();
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold) && totalItemCount != 0 )
                {
                    if(loadMore != null)
                        loadMore.onLoadMore();
                    isLoading = true;
                }

            }
        });
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.san_pham_item, parent, false);
            return new ItemViewHolder(view);

        }else if(viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.progressbar,parent,false);
            return new LoadingViewHolder(view);
        }
            return null;
        }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  ItemViewHolder)
        {
            SanPham sanPham = sanPhams.get(position);
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.tvTensanpham.setText(sanPham.getTenSanPham());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            double giasp = sanPham.getGiaSanPham();

            //Rounding curency to make a easy reading
            giasp = Precision.round(giasp/1000, 0)*1000;
            viewHolder.tvGiasanpham.setText(decimalFormat.format(giasp) + " Đ");
            Picasso.get().load(sanPham.getHinhAnhSanPham()).into(viewHolder.imgHinhAnhSanpham);
            final int idsanpham = sanPham.getId();

            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewHolder.cardViewCatalog.getLayoutParams();
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
            viewHolder.cardViewCatalog.setLayoutParams(layoutParams);


            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    boolean exit = false;
                    if (mYeuThichEntries.size() > 0) {
                        for (int vitritim = 0; vitritim < mYeuThichEntries.size(); vitritim++) {
                            if (mYeuThichEntries.get(vitritim).getIdSanPham() == idsanpham) {
                                viewHolder.imageViewTim.setImageResource(R.drawable.timdo24);
                                exit = true;
                            }
                        }

                        if (!exit)
                            viewHolder.imageViewTim.setImageResource(R.drawable.timden24);
                    } else
                        viewHolder.imageViewTim.setImageResource(R.drawable.timden24);
                }
            });

        }
        else if(holder instanceof LoadingViewHolder)
        {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
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

        public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return sanPhams.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }
    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    public void setYeuThichs(List<YeuThichEntry> yeuThichEntries){
        this.mYeuThichEntries = yeuThichEntries;

    }

    class LoadingViewHolder extends RecyclerView.ViewHolder
    {
        public ProgressBar progressBar;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progress_bar);
        }
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewCatalog;
        public ImageView imgHinhAnhSanpham, imageViewTim;
        public TextView tvTensanpham;
        public TextView tvGiasanpham;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imgHinhAnhSanpham = itemView.findViewById(R.id.anhsanpham);
            tvTensanpham = itemView.findViewById(R.id.tensanpham);
            tvGiasanpham = itemView.findViewById(R.id.giasanpham);
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
                        final String hinhanhsanpham = sanPhams.get(getLayoutPosition()).getHinhAnhSanPham();
                        final String khoiluongsanpham = sanPhams.get(getLayoutPosition()).getKhoiLuong();
                        imageViewTim.setImageResource(R.drawable.timdo24);

                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.yeuThichDao().insertYeuThich(new YeuThichEntry(idsanpham, tensanpham, giasanpham, hinhanhsanpham, khoiluongsanpham,idhang ));
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



















