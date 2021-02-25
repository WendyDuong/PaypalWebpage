package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.DetailActivity;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DatHangAdapter extends RecyclerView.Adapter<DatHangAdapter.viewHolderDatHang> {
    private Context mcontext;
    private AppDatabase mDb;
    private List<GioHangEntry> gioHangs;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";
    private String language;


    public DatHangAdapter(Context context, String language) {
        mcontext = context;
        this.language = language;
    }



    @Override
    public DatHangAdapter.viewHolderDatHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.dat_hang_item, parent, false);
        return new viewHolderDatHang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderDatHang holder, int position) {
        GioHangEntry gioHangEntry = gioHangs.get(position);

        //Determine the values of the wanted data

        String tensanpham = gioHangEntry.getTenSanPham();
        String tensanphamDE = gioHangEntry.getTenSanPhamDE();
        String khoiluongsanpham = gioHangEntry.getKhoiLuong();

        double giasanpham = gioHangEntry.getGiaSanPham();
        double giakhuyenmai = gioHangEntry.getGiaKhuyenMai();
        String hinhanhsanpham = gioHangEntry.getHinhAnhSanPham();
        int soluongsanpham = gioHangEntry.getSoLuong();
        double giaDonViSanPham = Math.round(giasanpham/soluongsanpham * 100.0) / 100.0;
        double giaDonViKhuyenMai = Math.round(giakhuyenmai/soluongsanpham * 100.0) / 100.0;
        int idsanpham = gioHangEntry.getIdSanPham();

        holder.textViewKhoiLuongItem.setText(khoiluongsanpham);
        //TODO SALE

        if (giakhuyenmai!= 0){
            //TODO SALE
            holder.textViewDonGia.setText("€"+ giaDonViSanPham);
            holder.textViewDonGia.setPaintFlags(holder.textViewDonGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textViewDonGiaKhuyenMai.setText("€"+ giaDonViKhuyenMai);
            holder.textViewDonGiaKhuyenMai.setVisibility(View.VISIBLE);
            holder.textViewGiaItem.setText("€"+ giakhuyenmai);

        }
        else{
            holder.textViewDonGiaKhuyenMai.setVisibility(View.INVISIBLE);
            holder.textViewDonGia.setText("€"+giaDonViSanPham);
            holder.textViewDonGia.setPaintFlags(holder.textViewDonGia.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textViewGiaItem.setText("€"+ giasanpham);

        }


        //Setting language
        switch (language) {
            case "de":
                holder.textViewTenItem.setText(tensanphamDE);
                holder.tvTongItemTitle.setText(R.string.summe_de);
                break;
            case "vn":
                holder.textViewTenItem.setText(tensanpham);
                holder.tvTongItemTitle.setText(R.string.summe_vn);
                break;
        }
        Picasso.get().load(hinhanhsanpham).into(holder.imageViewITem);
        holder.textViewSoLuongItem.setText(soluongsanpham + "x");
        holder.itemView.setTag(idsanpham);


    }

    @Override
    public int getItemCount() {
        if (gioHangs == null) {
            return 0;
        }
        return gioHangs.size();

    }

    public List<GioHangEntry> getDatHang() {
        return gioHangs;
    }

    public  void setDatHang (List<GioHangEntry> gioHangs){
        this.gioHangs = gioHangs;
        notifyDataSetChanged();

    }

    public class viewHolderDatHang extends RecyclerView.ViewHolder {

        TextView textViewTenItem, textViewGiaItem, textViewKhoiLuongItem, textViewSoLuongItem, textViewDonGia, textViewDonGiaKhuyenMai;
        TextView tvTongItemTitle;
        ImageView imageViewITem;

        public viewHolderDatHang(View view) {
            super(view);
            textViewDonGia = view.findViewById(R.id.text_view_don_gia);
            //TODO SALE
            textViewDonGiaKhuyenMai = view.findViewById(R.id.giakhuyenmai);
            textViewTenItem = view.findViewById(R.id.ten_item_dat);
            textViewGiaItem = view.findViewById(R.id.gia_item_dat);
            tvTongItemTitle = view.findViewById(R.id.tv_tong_item_title);
            textViewKhoiLuongItem = view.findViewById(R.id.khoiluong_item_dat);
            textViewSoLuongItem = view.findViewById(R.id.soluong_item_dat);
            imageViewITem = view.findViewById(R.id.image_view_item_dat);

            imageViewITem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int idintent = gioHangs.get(getLayoutPosition()).getIdSanPham();
                    int idHang = gioHangs.get(getLayoutPosition()).getIdHang();
                    Intent intentChiTiet = new Intent(mcontext, DetailActivity.class);
                    intentChiTiet.putExtra(EXTRA_SANPHAM_ID, idintent);
                    intentChiTiet.putExtra(EXTRA_HANG_ID,idHang );
                    intentChiTiet.putExtra("GioHangAdapter",gioHangs.get(getLayoutPosition()));

                    intentChiTiet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intentChiTiet);
                }
            });


    }
    }
}
