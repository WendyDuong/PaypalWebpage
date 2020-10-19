package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
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

import java.text.DecimalFormat;
import java.util.List;

public class DatHangAdapter extends RecyclerView.Adapter<DatHangAdapter.viewHolderDatHang> {
    private Context mcontext;
    private AppDatabase mDb;
    private List<GioHangEntry> gioHangs;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";


    public DatHangAdapter(Context context) {
        mcontext = context;
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
        String khoiluongsanpham = gioHangEntry.getKhoiLuong();
        double giasanpham = gioHangEntry.getGiaSanPham();
        int hinhanhsanpham = gioHangEntry.getHinhAnh();
        int soluongsanpham = gioHangEntry.getSoLuong();
        double giaDonViSanPham = giasanpham/soluongsanpham;


        int idsanpham = gioHangEntry.getIdSanPham();

        holder.textViewTenItem.setText(tensanpham);
        holder.textViewKhoiLuongItem.setText(khoiluongsanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewGiaItem.setText(decimalFormat.format(giasanpham) + " Đ");
        holder.textViewDonGia.setText(decimalFormat.format(giaDonViSanPham) + " Đ");
        holder.imageViewITem.setImageResource(hinhanhsanpham);
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

        TextView textViewTenItem, textViewGiaItem, textViewKhoiLuongItem, textViewSoLuongItem, textViewDonGia;
        ImageView imageViewITem;

        public viewHolderDatHang(View view) {
            super(view);
            textViewDonGia = view.findViewById(R.id.text_view_don_gia);
            textViewTenItem = view.findViewById(R.id.ten_item_dat);
            textViewGiaItem = view.findViewById(R.id.gia_item_dat);
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
                    intentChiTiet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intentChiTiet);
                }
            });


    }
    }
}
