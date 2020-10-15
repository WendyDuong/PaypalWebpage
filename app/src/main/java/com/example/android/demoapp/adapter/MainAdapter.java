package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.CatalogActivity;

import java.util.List;

public class MainGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Integer> mImageIds;

    public MainGridViewAdapter(Context context, List<Integer> imageIds)
    {
        mContext = context;
        mImageIds = imageIds;
    }
    @Override
    public int getCount() {
        return mImageIds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
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
}
