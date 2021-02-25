package com.example.android.demoapp.fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.YeuThichViewModel;
import com.example.android.demoapp.adapter.YeuthichAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.YeuThichEntry;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class YeuThichFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    private AppDatabase mDb;
    RecyclerView recyclerViewYeuThich;
    YeuthichAdapter yeuThichAdapter;
    View emptyView;
    TextView tvEmptyTilte;
    List<YeuThichEntry> mYeuThichs;

    public YeuThichFragment() {
        // Required empty public constructor
    }

    String language;

    @Override
    public void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.settings_language_key))){

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

            language = sharedPrefs.getString(
                    getString(R.string.settings_language_key),
                    getString(R.string.settings_language_default)
            );

            //Setting language
            switch (language) {
                case "de":
                    tvEmptyTilte.setText(R.string.empty_view_tilte_yeu_thich_de);
                    break;
                case "vn":
                    tvEmptyTilte.setText(R.string.empty_view_tilte_yeu_thich);
                    break;
            }

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDb = AppDatabase.getInstance(getActivity());
        View rootView = inflater.inflate(R.layout.yeuthich_fragment, container, false);
        emptyView = rootView.findViewById(R.id.empty_view);
        tvEmptyTilte = rootView.findViewById(R.id.empty_title_text);
        recyclerViewYeuThich = rootView.findViewById(R.id.recycler_view_yeu_thich);

        //Get preference values
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        language = sharedPrefs.getString(
                getString(R.string.settings_language_key),
                getString(R.string.settings_language_default)
        );

        //Setting language
        switch (language) {
            case "de":
                tvEmptyTilte.setText(R.string.empty_view_tilte_yeu_thich_de);
                break;
            case "vn":
                tvEmptyTilte.setText(R.string.empty_view_tilte_yeu_thich);
                break;
        }

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720)
        {
            recyclerViewYeuThich.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        else
        {
            recyclerViewYeuThich.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerViewYeuThich);
        yeuThichAdapter = new YeuthichAdapter(getActivity(), language);
        recyclerViewYeuThich.setAdapter(yeuThichAdapter);
        YeuThichViewModel yeuThichViewModel = ViewModelProviders.of(this).get(YeuThichViewModel.class);
        yeuThichViewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
            @Override
            public void onChanged(@Nullable List<YeuThichEntry> yeuThichEntries) {
                mYeuThichs = yeuThichEntries;

                if (mYeuThichs.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                }
                else
                    emptyView.setVisibility(View.INVISIBLE);
                    yeuThichAdapter.setYeuThichs(yeuThichEntries);

            }
        });

        return rootView;
    }
    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    int position = viewHolder.getAdapterPosition();
                    List<YeuThichEntry> yeuThichs = yeuThichAdapter.getYeuThichs();
                    mDb.yeuThichDao().deleteYeuThich(yeuThichs.get(position));
                }
            });


        }
    };



}