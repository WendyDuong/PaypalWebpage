package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DatHangHoAdapter extends  RecyclerView.Adapter<DatHangHoAdapter.viewHolderDatHangHo> {
    private Context mContext;
    private ArrayList<String> mImageIds;
    private int iD;
    private static final String EXTRA_HANG_ID = "extraHangId";
    private String browser;


    public DatHangHoAdapter(Context context, ArrayList<String> imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }

    @NonNull
    @Override
    public DatHangHoAdapter.viewHolderDatHangHo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, parent, false);
        return new DatHangHoAdapter.viewHolderDatHangHo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolderDatHangHo holder, int position) {
        Picasso.get().load(mImageIds.get(position)).error(R.drawable.error).into(holder.imageView);
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
        return mImageIds.size();
    }

    class viewHolderDatHangHo extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;

        public viewHolderDatHangHo(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_grid_view);
            cardView = view.findViewById(R.id.card_view_main);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iD = getLayoutPosition();
                    switch (iD){
                        case 0:
                            browser ="https://www.wmf.com/de/";
                            break;
                        case 1:
                            browser ="https://www.wmf.com/de/catalogsearch/result/index/?filtermarke=Silit&followSearch=9998&q=Silit";
                            break;
                        case 2:
                            browser ="https://www.mueller.de/";
                            break;
                        case 3:
                            browser ="https://www.dm.de/";
                            break;
                        case 4:
                            browser ="https://www.saturn.de/";
                            break;
                        case 5:
                            browser ="https://www.shop-apotheke.com/";
                            break;
                        case 6:
                            browser ="https://www.rossmann.de/de/";
                            break;
                        case 7:
                            browser ="https://www.worldofsweets.de/";
                            break;
                        case 8:
                            browser ="https://www.mediamarkt.de/";
                            break;
                        case 9:
                            browser ="https://www.adidas.de/";

                            break;
                        case 10:
                            browser ="https://www.nike.com/de";
                            break;
                        case 11:
                            browser ="https://www.amazon.de/";
                            break;
                        case 12:
                            browser ="https://www.bershka.com/";
                            break;
                        case 13:
                            browser ="https://www.c-and-a.com/eu/en/shop";
                            break;
                        case 14:
                            browser ="https://www.esprit.de/";
                            break;
                        case 15:
                            browser ="https://www2.hm.com/de_de/index.html";
                            break;
                        case 16:
                            browser ="https://shop.mango.com/de";
                            break;
                        case 17:
                            browser ="https://www.massimodutti.com/de/";
                            break;
                        case 18:
                            browser ="https://www.zara.com/de/";
                            break;
                        case 19:
                            browser ="https://www.pullandbear.com/";
                            break;
                        case 20:
                            browser ="https://www.douglas.de/";
                            break;

                    }

                    if (!browser.startsWith("http://") && !browser.startsWith("https://"))
                        browser = "http://" + browser;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(browser));
                    mContext.startActivity(browserIntent);


                }
            });
        }
    }
}


