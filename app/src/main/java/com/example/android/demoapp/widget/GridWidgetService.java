package com.example.android.demoapp.widget;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.CatalogActivity;
import com.example.android.demoapp.fragment.MainFragment;


public class GridWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext());
    }

}


class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;

    public GridRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;

    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.new_app_widget);
        int imgRes = MainFragment.ImageList.get(position);
        views.setImageViewResource(R.id.appwidget_icon, imgRes);
        Bundle extras = new Bundle();
        extras.putInt(CatalogActivity.EXTRA_HANG_ID, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        views.setOnClickFillInIntent(R.id.appwidget_icon, fillInIntent);
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1; // Treat all items in the GridView the same
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}