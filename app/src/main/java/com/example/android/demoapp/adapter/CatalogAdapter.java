package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.DetailActivity;
import com.example.android.demoapp.activity.MainActivity;
import com.example.android.demoapp.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.itemHolder>{
    Context context;
    ArrayList<Sanpham>  arrayListSanpham;
    String tensanpham, hinhanhsanpham, khoiluongsanpham, motasanpham;
    int giasanpham,idsanpham,idnhacungcap;
    YeuthichAdapter yeuthichAdapter;

    public CatalogAdapter(Context context, ArrayList<Sanpham> arrayListSanpham) {
        this.context = context;
        this.arrayListSanpham = arrayListSanpham;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.san_pham_item,null);
        itemHolder itemHolder=new itemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(itemHolder holder, int position) {
        Sanpham sanPham ;
        sanPham=arrayListSanpham.get(position);
        holder.tvTensanpham.setText(sanPham.getMtensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.tvGiasanpham.setText(decimalFormat.format(sanPham.getMgiasanpham())+" Đ");
        Picasso.get().load(sanPham.getMhinhanhsanpham()).into(holder.imgHinhAnhSanpham);
        idsanpham = sanPham.getMidsanpham();
        if(MainActivity.mangYeuthich.size() > 0)
        { boolean exit = false;
        for (int vitritim = 0; vitritim < MainActivity.mangYeuthich.size(); vitritim++) {
            if (MainActivity.mangYeuthich.get(vitritim).getMidsanpham() == idsanpham){
                holder.imageViewTim.setImageResource(R.drawable.timdo24);
            exit = true;}}
            if(exit == false)
                holder.imageViewTim.setImageResource(R.drawable.timden24);
        }
        else
            holder.imageViewTim.setImageResource(R.drawable.timden24);
        }


    @Override
    public int getItemCount() {
        return arrayListSanpham.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhAnhSanpham;
        public TextView tvTensanpham;
        public TextView tvGiasanpham;
        public TextView tvDaban;
        public ImageView imageViewTim;
        public itemHolder(View itemView) {
            super(itemView);
            imgHinhAnhSanpham=itemView.findViewById(R.id.anhsp);
            tvTensanpham=itemView.findViewById(R.id.tensp);
            tvGiasanpham=itemView.findViewById(R.id.giasp);
            tvDaban = itemView.findViewById(R.id.da_ban);
            imageViewTim = itemView.findViewById(R.id.image_view_tim);
        itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DetailActivity.class);
                    intent.putExtra("chitietsanpham",arrayListSanpham.get(getLayoutPosition()));
                    Toast.makeText(context, arrayListSanpham.get(getLayoutPosition()).getMtensanpham(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);


                }
            });
        imageViewTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageViewTim.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.timden24).getConstantState()) {
                    imageViewTim.setImageResource(R.drawable.timdo24);
                    idsanpham = arrayListSanpham.get(getLayoutPosition()).getMidsanpham();
                    tensanpham = arrayListSanpham.get(getLayoutPosition()).getMtensanpham();
                    giasanpham = arrayListSanpham.get(getLayoutPosition()).getMgiasanpham();
                    hinhanhsanpham = arrayListSanpham.get(getLayoutPosition()).getMhinhanhsanpham();
                    motasanpham = arrayListSanpham.get(getLayoutPosition()).getMmotasanpham();
                    khoiluongsanpham = arrayListSanpham.get(getLayoutPosition()).getKhoiluongsanpham();
                    idnhacungcap = arrayListSanpham.get(getLayoutPosition()).getIdnhacungcap();

                    /*for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                        if (MainActivity.mangGioHang.get(i).getIdsp() == idsanpham) {
                            Toast.makeText(context, "Sản Phẩm đã có trong danh sách yêu thích",Toast.LENGTH_SHORT).show();
                        }
                        else   */
                     MainActivity.mangYeuthich.add(new Sanpham(idsanpham,tensanpham,giasanpham,hinhanhsanpham, motasanpham,idnhacungcap, khoiluongsanpham));

                    yeuthichAdapter = new YeuthichAdapter(context, MainActivity.mangYeuthich);
                    yeuthichAdapter.notifyDataSetChanged();

                }else{
                    idsanpham = arrayListSanpham.get(getLayoutPosition()).getMidsanpham();

                    Toast.makeText(context, ""+idsanpham, Toast.LENGTH_SHORT).show();

                    imageViewTim.setImageResource(R.drawable.timden24);
                    for (int vitrixoa = 0; vitrixoa < MainActivity.mangYeuthich.size(); vitrixoa++) {
                        if (MainActivity.mangYeuthich.get(vitrixoa).getMidsanpham() == idsanpham)
                    {MainActivity.mangYeuthich.remove(vitrixoa);}
                        yeuthichAdapter = new YeuthichAdapter(context, MainActivity.mangYeuthich);
                        yeuthichAdapter.notifyDataSetChanged();


                    }




            }
        }});



        }
    }
}
