package com.example.android.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.model.GiohangItem;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DatHangAdapter extends BaseAdapter {
    Context mcontext;
    ArrayList<GiohangItem> arrayGioHang;

    public  DatHangAdapter(Context mcontext, ArrayList<GiohangItem> arrayGioHang) {
        this.mcontext = mcontext;
        this.arrayGioHang = arrayGioHang;
    }

    @Override
    public int getCount() {
        return arrayGioHang.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater= (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.dat_hang_item,null);
        TextView textViewTenItem,textViewGiaItem,textViewKhoiLuongItem,textViewSoLuongItem;
        ImageView imageViewITem;
        textViewTenItem = view.findViewById(R.id.ten_item_dat);
        textViewGiaItem = view.findViewById(R.id.gia_item_dat);
        textViewKhoiLuongItem= view.findViewById(R.id.khoiluong_item_dat);
        textViewSoLuongItem = view.findViewById(R.id.soluong_item_dat);
        imageViewITem = view.findViewById(R.id.image_view_item_dat);

        textViewTenItem.setText(arrayGioHang.get(i).getTensp());
        textViewKhoiLuongItem.setText(arrayGioHang.get(i).getKhoiluongsanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        textViewGiaItem.setText(decimalFormat.format(arrayGioHang.get(i).getGiasp())+" Đ");
        Picasso.get().load(arrayGioHang.get(i).getHinhsp()).into(imageViewITem);
        textViewSoLuongItem.setText( "x"+arrayGioHang.get(i).getSoluongsp());



        return view;
    }
}
