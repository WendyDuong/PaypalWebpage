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

import java.util.List;

public class MainAdapter extends  RecyclerView.Adapter<MainAdapter.viewHolderMain> {
    private Context mContext;
    private List<Integer> mImageIds;
    private int iD;
    private static final String EXTRA_HANG_ID = "extraHangId";

    public MainAdapter(Context context, List<Integer> imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }


    @NonNull
    @Override
    public MainAdapter.viewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, parent, false);
        return new viewHolderMain(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.viewHolderMain holder, int position) {

        holder.imageView.setImageResource(mImageIds.get(position));


        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.cardView.getLayoutParams();

       /* layoutParams.height = holder.cardView.getMeasuredWidth();
        holder.cardView.requestLayout();*/



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
                    Log.i("MainGridViewAdapter", Integer.toString(iD));

                    Intent intent = new Intent(mContext, CatalogActivity.class);
                    intent.putExtra(EXTRA_HANG_ID, iD);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
   /* @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grid_view_item,null);
        }
        imageView = (ImageView) convertView.findViewById(R.id.image_grid_view);
            // Define the layout parameters
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        // Set the image resource and return the newly created ImageView
        imageView.setImageResource(mImageIds.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CatalogActivity.class);
                intent.putExtra("idnhacungcap", position);
                mContext.startActivity(intent);
            }
        });

        return imageView;
    }
}*/
