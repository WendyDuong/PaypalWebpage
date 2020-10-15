package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.DetailActivity;
import com.example.android.demoapp.activity.MainActivity;
import com.example.android.demoapp.fragment.CartFragment;
import com.example.android.demoapp.model.GiohangItem;
import com.example.android.demoapp.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.viewHolderGioHang> {
    Context mcontext;
    ArrayList<GiohangItem> arrayGioHang;
    String tensanpham, hinhanhsanpham, khoiluongsanpham, motasanpham;
    int giasanpham,idsanpham, soluongsanpham,idnhacungcap;

    public GioHangAdapter(Context mcontext, ArrayList<GiohangItem> arrayGioHang) {
        this.mcontext = mcontext;
        this.arrayGioHang = arrayGioHang;
    }

    @Override
    public int getItemCount() {
        return arrayGioHang.size();
    }

    @NonNull
    @Override
    public GioHangAdapter.viewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gio_hang_item,null);
        viewHolderGioHang gioHangItemHolder=new viewHolderGioHang(view);
        return gioHangItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.viewHolderGioHang holder, int position) {
        GiohangItem giohangItem;
        giohangItem = arrayGioHang.get(position);
        holder.textViewTenItem.setText(giohangItem.getTensp());
        holder.textViewKhoiLuongItem.setText(giohangItem.getKhoiluongsanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.textViewGiaItem.setText(decimalFormat.format(giohangItem.getGiasp())+" Đ");
        Picasso.get().load(giohangItem.getHinhsp()).into(holder.imageViewITem);
        holder.textViewSoLuongItem.setText(giohangItem.getSoluongsp()+"");

        int sl = arrayGioHang.get(position).getSoluongsp();

        if(sl<2){
            holder.buttonGiam.setVisibility(View.INVISIBLE);
            holder.buttonTang.setVisibility(View.VISIBLE);
        }
        else if(sl>=2 && sl<20){
            holder.buttonGiam.setVisibility(View.VISIBLE);
            holder.buttonTang.setVisibility(View.VISIBLE);
        }
        else{
            holder.buttonTang.setVisibility(View.INVISIBLE);
            holder.buttonGiam.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }





    public class viewHolderGioHang extends RecyclerView.ViewHolder  {
        TextView textViewTenItem,textViewGiaItem,textViewKhoiLuongItem,textViewSoLuongItem;
        ImageView imageViewITem;
        Button buttonGiam,buttonTang;

        public viewHolderGioHang(View view){
            super(view);
            textViewTenItem = view.findViewById(R.id.ten_item_gio_hang);
            textViewGiaItem = view.findViewById(R.id.gia_item_gio_hang);
            textViewKhoiLuongItem = view.findViewById(R.id.khoi_luong_item_gio_hang);
            textViewSoLuongItem = view.findViewById(R.id.so_luong_item);
            imageViewITem=view.findViewById(R.id.anh_item_gio_hang);
            buttonGiam =view.findViewById(R.id.button_giam);
            buttonTang =view.findViewById(R.id.button_tang);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idsanpham = arrayGioHang.get(getLayoutPosition()).getIdsp();
                    tensanpham = arrayGioHang.get(getLayoutPosition()).getTensp();
                    giasanpham = arrayGioHang.get(getLayoutPosition()).getGiasp();
                    hinhanhsanpham = arrayGioHang.get(getLayoutPosition()).getHinhsp();
                    khoiluongsanpham = arrayGioHang.get(getLayoutPosition()).getKhoiluongsanpham();
                    motasanpham = arrayGioHang.get(getLayoutPosition()).getMotasp();
                    idnhacungcap = arrayGioHang.get(getLayoutPosition()).getIdnhacungcap();
                    Intent intentChiTiet= new Intent( mcontext, DetailActivity.class);
                    intentChiTiet.putExtra("chitietsanpham", new Sanpham(idsanpham,tensanpham,giasanpham,hinhanhsanpham,motasanpham,idnhacungcap,khoiluongsanpham));
                    intentChiTiet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intentChiTiet);

                }
            });

            buttonTang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idsanpham = arrayGioHang.get(getLayoutPosition()).getIdsp();
                    tensanpham = arrayGioHang.get(getLayoutPosition()).getTensp();
                    giasanpham = arrayGioHang.get(getLayoutPosition()).getGiasp();
                    hinhanhsanpham = arrayGioHang.get(getLayoutPosition()).getHinhsp();
                    khoiluongsanpham = arrayGioHang.get(getLayoutPosition()).getKhoiluongsanpham();
                    motasanpham = arrayGioHang.get(getLayoutPosition()).getMotasp();

                    int soluongsanphamcu=Integer.parseInt(textViewSoLuongItem.getText().toString());
                    int soluongsanphammoi=Integer.parseInt(textViewSoLuongItem.getText().toString());
                    soluongsanphammoi = soluongsanphammoi + 1;
                    textViewSoLuongItem.setText(soluongsanphammoi+"");
                    MainActivity.mangGioHang.get(getLayoutPosition()).setSoluongsp(soluongsanphammoi);
                    MainActivity.mangGioHang.get(getLayoutPosition()).setGiasp(giasanpham*soluongsanphammoi/soluongsanphamcu);
                    final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                    textViewGiaItem.setText(decimalFormat.format(MainActivity.mangGioHang.get(getLayoutPosition()).getGiasp())+"Đ");

                  long tongtien=0;
                    for(int i=0;i<MainActivity.mangGioHang.size();i++){
                        tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
                    }
                    DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                   // GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
                    CartFragment.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");

                    if(soluongsanphammoi>19){
                        buttonTang.setVisibility(View.INVISIBLE);
                        buttonGiam.setVisibility(View.VISIBLE);
                        textViewSoLuongItem.setText(soluongsanphammoi+"");
                    }
                    else{
                        buttonTang.setVisibility(View.VISIBLE);
                        buttonGiam.setVisibility(View.VISIBLE);
                        textViewSoLuongItem.setText(soluongsanphammoi+"");
                    }
                }}
            );
            buttonGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idsanpham = arrayGioHang.get(getLayoutPosition()).getIdsp();
                    tensanpham = arrayGioHang.get(getLayoutPosition()).getTensp();
                    giasanpham = arrayGioHang.get(getLayoutPosition()).getGiasp();
                    hinhanhsanpham = arrayGioHang.get(getLayoutPosition()).getHinhsp();
                    khoiluongsanpham = arrayGioHang.get(getLayoutPosition()).getKhoiluongsanpham();
                    motasanpham = arrayGioHang.get(getLayoutPosition()).getMotasp();

                    int soluongsanphamcu=Integer.parseInt(textViewSoLuongItem.getText().toString());
                    int soluongsanphammoi=Integer.parseInt(textViewSoLuongItem.getText().toString());
                    soluongsanphammoi = soluongsanphammoi - 1;
                    textViewSoLuongItem.setText(soluongsanphammoi + "");
                    MainActivity.mangGioHang.get(getLayoutPosition()).setSoluongsp(soluongsanphammoi);
                    MainActivity.mangGioHang.get(getLayoutPosition()).setGiasp(giasanpham*soluongsanphammoi/soluongsanphamcu);
                    final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                    textViewGiaItem.setText(decimalFormat.format(MainActivity.mangGioHang.get(getLayoutPosition()).getGiasp())+"Đ");
                    long tongtien=0;
                    for(int i=0;i<MainActivity.mangGioHang.size();i++){
                        tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
                    }
                    DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                   // GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
                    CartFragment.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
                    if(soluongsanphammoi<2){
                        buttonGiam.setVisibility(View.INVISIBLE);
                        buttonTang.setVisibility(View.VISIBLE);
                        textViewSoLuongItem.setText(soluongsanphammoi+"");
                    }
                    else{
                        buttonTang.setVisibility(View.VISIBLE);
                        buttonGiam.setVisibility(View.VISIBLE);
                        textViewSoLuongItem.setText(soluongsanphammoi+"");
                    }
                }
            });







        }


    }
}




   /* @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if (view == null )
            LayoutInflater layoutInflater= (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.gio_hang_item,null);
        final TextView textViewTenItem,textViewGiaItem,textViewKhoiLuongItem,textViewSoLuongItem;
        ImageView imageViewITem;
        final Button buttonGiam,buttonTang;
        textViewTenItem=view.findViewById(R.id.ten_item_gio_hang);
        textViewGiaItem=view.findViewById(R.id.gia_item_gio_hang);
        textViewKhoiLuongItem=view.findViewById(R.id.khoi_luong_item_gio_hang);
        imageViewITem=view.findViewById(R.id.anh_item_gio_hang);
        buttonGiam =view.findViewById(R.id.button_giam);
        buttonTang =view.findViewById(R.id.button_tang);
        textViewSoLuongItem=view.findViewById(R.id.so_luong_item);
        textViewTenItem.setText(arrayGioHang.get(i).getTensp());
        textViewKhoiLuongItem.setText(arrayGioHang.get(i).getKhoiluongsanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        textViewGiaItem.setText(decimalFormat.format(arrayGioHang.get(i).getGiasp())+" Đ");
        Log.d("test", String.valueOf(arrayGioHang.size()));
        Log.d("test",arrayGioHang.get(i).getHinhsp());
        Picasso.get().load(arrayGioHang.get(i).getHinhsp()).centerCrop().resize(150,150).into(imageViewITem);
        textViewSoLuongItem.setText(arrayGioHang.get(i).getSoluongsp()+"");
        final int sl= Integer.parseInt(textViewSoLuongItem.getText().toString());
        final int slmoi=sl;

        if(sl<2){
            buttonGiam.setVisibility(View.INVISIBLE);
            buttonTang.setVisibility(View.VISIBLE);
        }
        else if(sl>=2 && sl<20){
            buttonGiam.setVisibility(View.VISIBLE);
            buttonTang.setVisibility(View.VISIBLE);
        }
        else{
            buttonTang.setVisibility(View.INVISIBLE);
            buttonGiam.setVisibility(View.VISIBLE);
        }

        buttonTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl1=Integer.parseInt(textViewSoLuongItem.getText().toString());
                int slmoi=Integer.parseInt(textViewSoLuongItem.getText().toString());
                slmoi+=1;
                textViewSoLuongItem.setText(slmoi+"");
                MainActivity.mangGioHang.get(i).setSoluongsp(slmoi);
                MainActivity.mangGioHang.get(i).setGiasp(MainActivity.mangGioHang.get(i).getGiasp()*slmoi/(sl1));
                textViewGiaItem.setText(MainActivity.mangGioHang.get(i).getGiasp()+"");
                long tongtien=0;
                for(int i=0;i<MainActivity.mangGioHang.size();i++){
                    tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
                }
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
                textViewGiaItem.setText(decimalFormat1.format(tongtien)+" Đ");
                if(slmoi>19){
                    buttonTang.setVisibility(View.INVISIBLE);
                    buttonGiam.setVisibility(View.VISIBLE);
                    textViewSoLuongItem.setText(slmoi+"");
                }
                else{
                    buttonTang.setVisibility(View.VISIBLE);
                    buttonGiam.setVisibility(View.VISIBLE);
                    textViewSoLuongItem.setText(slmoi+"");
                }
            }
        });
        buttonGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl1=Integer.parseInt(textViewSoLuongItem.getText().toString());
                int slmoi=Integer.parseInt(textViewSoLuongItem.getText().toString());
                slmoi-=1;
                textViewSoLuongItem.setText(slmoi+"");
                MainActivity.mangGioHang.get(i).setSoluongsp(slmoi);
                MainActivity.mangGioHang.get(i).setGiasp(MainActivity.mangGioHang.get(i).getGiasp()*slmoi/(sl1));
                textViewGiaItem.setText(MainActivity.mangGioHang.get(i).getGiasp()+"");
                long tongtien=0;
                for(int i=0;i<MainActivity.mangGioHang.size();i++){
                    tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
                }
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
                textViewGiaItem.setText(decimalFormat1.format(tongtien)+" Đ");
                if(slmoi<2){
                    buttonGiam.setVisibility(View.INVISIBLE);
                    buttonTang.setVisibility(View.VISIBLE);
                    textViewSoLuongItem.setText(slmoi+"");
                }
                else{
                    buttonTang.setVisibility(View.VISIBLE);
                    buttonGiam.setVisibility(View.VISIBLE);
                    textViewSoLuongItem.setText(slmoi+"");
                }
            }
        });







        return view;
    }*/