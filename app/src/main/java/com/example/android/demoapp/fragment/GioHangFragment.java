package com.example.android.demoapp.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.DatHangActivity;
import com.example.android.demoapp.activity.MainActivity;
import com.example.android.demoapp.adapter.GioHangAdapter;
import com.example.android.demoapp.database.SanPhamContract;

import java.text.DecimalFormat;
import java.util.Objects;


public class CartFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor>, GioHangAdapter.ListItemClickListener{

    Button buttonDatHang;
    RecyclerView giohangRecyclerView;
    View emptyView;
    GioHangAdapter gioAdapter;
    public static TextView tvTongtien;

    private static final String TAG = CartFragment.class.getSimpleName();

    private static final int TASK_LOADER_ID = 0;

    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.gio_hang_fragment, container, false);
        buttonDatHang = rootView.findViewById(R.id.button_dat_hang);
        giohangRecyclerView = rootView.findViewById(R.id.recycler_view_gio_hang);
        emptyView = rootView.findViewById(R.id.empty_view);
        giohangRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gioAdapter = new GioHangAdapter( this , getActivity());
        giohangRecyclerView.setAdapter(gioAdapter);

        emptyView.setVisibility(View.INVISIBLE);

       // giohangRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
         //       DividerItemDecoration.VERTICAL));
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(giohangRecyclerView);
        tvTongtien = rootView.findViewById(R.id.so_tien);

        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requireActivity().getContentResolver().query(SanPhamContract.SanPhamEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null).getCount() > 0) {
                    Intent intent = new Intent(getActivity(), DatHangActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Bạn chưa có sản phẩm nào trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getLoaderManager().initLoader(TASK_LOADER_ID, null, this);

        eventUtil();
        return rootView;

    }
    @Override
    public void onResume() {
        tvTongtien = getView().findViewById(R.id.so_tien);

        long tongtien=0;
        String[] projection2 = {
                SanPhamContract.SanPhamEntry.COLUMN_GIA,

        };

        Cursor cursorGiaTien = requireActivity().getContentResolver().query(SanPhamContract.SanPhamEntry.CONTENT_URI,projection2,null,null,null);
        DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");

        if( cursorGiaTien.getCount()>0){
            for(int i = 0; i< cursorGiaTien.getCount(); i++){
                cursorGiaTien.moveToPosition(i);
                tongtien+= cursorGiaTien.getDouble(cursorGiaTien.getColumnIndex(SanPhamContract.SanPhamEntry.COLUMN_GIA));
            }
            cursorGiaTien.close();
            // GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
            tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");}
        else
            tvTongtien.setText(decimalFormat1.format(0)+" Đ");
        getLoaderManager().restartLoader(TASK_LOADER_ID, null, CartFragment.this);
        super.onResume();
    }



    private void eventUtil() {

        long tongtien=0;
        String[] projection2 = {
                SanPhamContract.SanPhamEntry.COLUMN_GIA,

        };

        Cursor cursorGiaTien = requireActivity().getContentResolver().query(SanPhamContract.SanPhamEntry.CONTENT_URI,projection2,null,null,null);
        DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");

        if( cursorGiaTien.getCount()>0){
        for(int i = 0; i< cursorGiaTien.getCount(); i++){
            cursorGiaTien.moveToPosition(i);
            tongtien+= cursorGiaTien.getDouble(cursorGiaTien.getColumnIndex(SanPhamContract.SanPhamEntry.COLUMN_GIA));
        }
        cursorGiaTien.close();
        // GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
        tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");}
        else
            tvTongtien.setText(decimalFormat1.format(0)+" Đ");
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, final Bundle loaderArgs) {

        return new AsyncTaskLoader<Cursor>(Objects.requireNonNull(getActivity())) {

            // Initialize a Cursor, this will hold all the task data
            Cursor mTaskData = null;

            // onStartLoading() is called when a loader first starts loading data
            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    // Delivers any previously loaded data immediately
                    deliverResult(mTaskData);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            // loadInBackground() performs asynchronous loading of data
            @Override
            public Cursor loadInBackground() {
                // Will implement to load data

                // Query and load all task data in the background; sort by priority
                // [Hint] use a try/catch block to catch any errors in loading data

                try {
                    return requireActivity().getContentResolver().query(SanPhamContract.SanPhamEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                           null);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };

    }


    /**
     * Called when a previously created loader has finished its load.
     *
     * @param loader The Loader that has finished.
     * @param data The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update the data that the adapter uses to create ViewHolders

        gioAdapter.swapCursor(data);
        if ( data.getCount() == 0)
            emptyView.setVisibility(View.VISIBLE);
        else {
            emptyView.setVisibility(View.INVISIBLE);
        }


    }





    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.
     * onLoaderReset removes any references this activity had to the loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        gioAdapter.swapCursor(null);
    }



    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int id = (int) viewHolder.itemView.getTag();

            // Build appropriate uri with String row id appended
            String stringId = Integer.toString(id);
            Uri uri = SanPhamContract.SanPhamEntry.CONTENT_URI;
            uri = uri.buildUpon().appendPath(stringId).build();

            // COMPLETED (2) Delete a single row of data using a ContentResolver
            requireActivity().getContentResolver().delete(uri, null, null);
            getLoaderManager().restartLoader(TASK_LOADER_ID, null, CartFragment.this);

            // COMPLETED (3) Restart the loader to re-query for all tasks after a deletion
            eventUtil();


            Cursor cursorSoSanPhamGioHang = requireActivity().getContentResolver().query(SanPhamContract.SanPhamEntry.CONTENT_URI, new String[] {SanPhamContract.SanPhamEntry.COLUMN_SOLUONG},null,null,null);
            int sosanphammua= 0;
            assert cursorSoSanPhamGioHang != null;
            if (cursorSoSanPhamGioHang.getCount() > 0){
                for (int i = 0; i < cursorSoSanPhamGioHang.getCount(); i++) {
                    cursorSoSanPhamGioHang.moveToPosition(i);
                    sosanphammua = sosanphammua + cursorSoSanPhamGioHang.getInt(cursorSoSanPhamGioHang.getColumnIndex(SanPhamContract.SanPhamEntry.COLUMN_SOLUONG));
                }
                cursorSoSanPhamGioHang.close();

                if (MainActivity.mainActivityOnCreat)
           {     MainActivity.badgeDrawableGioHang.setVisible(true);

                MainActivity.badgeDrawableGioHang.setNumber(sosanphammua);
            }}
            else {
                if (MainActivity.mainActivityOnCreat)
                    MainActivity.badgeDrawableGioHang.setVisible(false);
                }

        }
    };


    @Override
    public void onListItemClick() {
        getLoaderManager().restartLoader(TASK_LOADER_ID, null, CartFragment.this);

    }
}

