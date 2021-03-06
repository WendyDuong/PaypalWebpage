package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.DetailActivity;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.viewHolderGioHang> {
    Context context;
    private AppDatabase mDb;
    private List<GioHangEntry> gioHangs;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";
    private String language;


    public GioHangAdapter(Context context, String language) {
        this.context = context;
        this.language = language;
    }

    @NonNull
    @Override
    public GioHangAdapter.viewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gio_hang_item, parent, false);
        return new viewHolderGioHang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.viewHolderGioHang holder, int position) {
        GioHangEntry gioHangEntry = gioHangs.get(position);
        String tensanpham = gioHangEntry.getTenSanPham();
        String tensanphamDE = gioHangEntry.getTenSanPhamDE();
        String khoiluongsanpham = gioHangEntry.getKhoiLuong();
        String hinhanhsanpham = gioHangEntry.getHinhAnhSanPham();
        int soluongsanpham = gioHangEntry.getSoLuong();
        int idsanpham = gioHangEntry.getIdSanPham();
        double giasanpham = gioHangEntry.getGiaSanPham();
        double giakhuyenmai = gioHangEntry.getGiaKhuyenMai();


        //Setting language
        switch (language){
            case "de":
                holder.textViewTenItem.setText(tensanphamDE);
                break;
            case "vn" :
                holder.textViewTenItem.setText(tensanpham);
                break;
        }

        holder.textViewKhoiLuongItem.setText(khoiluongsanpham);
        Picasso.get().load(hinhanhsanpham).into(holder.imageViewITem);
        holder.textViewSoLuongItem.setText(soluongsanpham + "");
        holder.itemView.setTag(idsanpham);

        if (giakhuyenmai != 0){
            //TODO SALE
            holder.textViewGiaItem.setText("€"+Math.round(giasanpham * 100.0) / 100.0);
            holder.textViewGiaItem.setPaintFlags(holder.textViewGiaItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvGiaKhuyenMai.setText("€"+Math.round(giakhuyenmai * 100.0) / 100.0);
            holder.tvGiaKhuyenMai.setVisibility(View.VISIBLE);
        }
        else{
            holder.tvGiaKhuyenMai.setVisibility(View.INVISIBLE);
            holder.textViewGiaItem.setText("€"+Math.round(giasanpham * 100.0) / 100.0);
            holder.textViewGiaItem.setPaintFlags(holder.textViewGiaItem.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }


        if (soluongsanpham < 2) {
            holder.buttonGiam.setVisibility(View.INVISIBLE);
            holder.buttonTang.setVisibility(View.VISIBLE);
        } else if (soluongsanpham < 50) {
            holder.buttonGiam.setVisibility(View.VISIBLE);
            holder.buttonTang.setVisibility(View.VISIBLE);
        } else {
            holder.buttonTang.setVisibility(View.INVISIBLE);
            holder.buttonGiam.setVisibility(View.VISIBLE);
        }

    }


    class viewHolderGioHang extends RecyclerView.ViewHolder {
        TextView textViewTenItem, textViewGiaItem, tvGiaKhuyenMai,textViewKhoiLuongItem, textViewSoLuongItem;
        ImageView imageViewITem;
        ImageButton buttonGiam, buttonTang;
        ImageButton imageButton;

        public viewHolderGioHang(View view) {
            super(view);
            textViewTenItem = view.findViewById(R.id.ten_item_gio_hang);
            textViewGiaItem = view.findViewById(R.id.gia_item_gio_hang);
            tvGiaKhuyenMai = view.findViewById(R.id.giakhuyenmai);
            textViewKhoiLuongItem = view.findViewById(R.id.khoi_luong_item_gio_hang);
            textViewSoLuongItem = view.findViewById(R.id.so_luong_item);
            imageViewITem = view.findViewById(R.id.anh_item_gio_hang);
            buttonGiam = view.findViewById(R.id.button_giam);
            buttonTang = view.findViewById(R.id.button_tang);
            imageButton = view.findViewById(R.id.button_xoa_gio_hang);
            mDb = AppDatabase.getInstance(context);

            imageViewITem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int idIntent = gioHangs.get(getLayoutPosition()).getIdSanPham();
                    int idHang = gioHangs.get(getLayoutPosition()).getIdHang();
                    Intent intentChiTiet = new Intent(context, DetailActivity.class);
                    intentChiTiet.putExtra(EXTRA_SANPHAM_ID, idIntent);
                    intentChiTiet.putExtra(EXTRA_HANG_ID, idHang);

                    intentChiTiet.putExtra("GioHangAdapter",gioHangs.get(getLayoutPosition()));

                    context.startActivity(intentChiTiet);
                }
            });


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder buidlder = new AlertDialog.Builder(context);
                    buidlder.setMessage("Bạn có muốn xóa sản phẩm này không ?");
                    buidlder.setIcon(R.drawable.cancel_128);
                    buidlder.setTitle("Xác nhận xóa");
                    buidlder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    buidlder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb = AppDatabase.getInstance(context);
                                    mDb.gioHangDao().deleteGioHang(gioHangs.get(getLayoutPosition()));
                                }
                            });
                        }
                    });
                    AlertDialog alertDialog = buidlder.create();
                    alertDialog.show();

                }
            });

            buttonTang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int id = gioHangs.get(getLayoutPosition()).getId();
                    final int  idsanpham =   gioHangs.get(getLayoutPosition()).getIdSanPham();
                    final String tensanpham = gioHangs.get(getLayoutPosition()).getTenSanPham();
                    final double giasanpham = gioHangs.get(getLayoutPosition()).getGiaSanPham();
                    //TODO SALE
                    final double giakhuyenmai = gioHangs.get(getLayoutPosition()).getGiaKhuyenMai();
                    final String  hinhanhsanpham = gioHangs.get(getLayoutPosition()).getHinhAnhSanPham();
                    final String khoiluongsanpham = gioHangs.get(getLayoutPosition()).getKhoiLuong();
                    final int idhang = gioHangs.get(getLayoutPosition()).getIdHang();
                    final int soluongsanphamcu= Integer.parseInt(textViewSoLuongItem.getText().toString());
                    final String moTa = gioHangs.get(getLayoutPosition()).getMoTa();
                    final String thuongHieu = gioHangs.get(getLayoutPosition()).getThuongHieu();
                    final String xuatXu = gioHangs.get(getLayoutPosition()).getXuatXu();
                    final String tenSanPhamDE = gioHangs.get(getLayoutPosition()).getTenSanPhamDE();
                    final String moTaDE = gioHangs.get(getLayoutPosition()).getMoTaDE();
                    final int idShopBan = gioHangs.get(getLayoutPosition()).getIdShopBan();


                    final int soluongsanphammoi;
                    soluongsanphammoi = soluongsanphamcu + 1;
                    textViewSoLuongItem.setText(soluongsanphammoi+"");
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            //TODO SALE
                            mDb.gioHangDao().updateGioHang(new GioHangEntry(id , idsanpham,tensanpham, Math.round(giasanpham * soluongsanphammoi /soluongsanphamcu * 100.0) / 100.0, Math.round(giakhuyenmai * soluongsanphammoi /soluongsanphamcu * 100.0) / 100.0,  hinhanhsanpham,khoiluongsanpham,soluongsanphammoi,idhang, moTa, thuongHieu, xuatXu, tenSanPhamDE, moTaDE, idShopBan));

                        }
                    });
                    if(soluongsanphammoi>49){
                        buttonTang.setVisibility(View.INVISIBLE);
                    }
                    else{
                        buttonTang.setVisibility(View.VISIBLE);
                    }
                    buttonGiam.setVisibility(View.VISIBLE);
                    textViewSoLuongItem.setText(soluongsanphammoi+"");
                }
            });
            buttonGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int id = gioHangs.get(getLayoutPosition()).getId();
                    final int idsanpham = gioHangs.get(getLayoutPosition()).getIdSanPham();
                    final String tensanpham = gioHangs.get(getLayoutPosition()).getTenSanPham();
                    final double giasanpham = gioHangs.get(getLayoutPosition()).getGiaSanPham();
                    //TODO SALE
                    final double giakhuyenmai = gioHangs.get(getLayoutPosition()).getGiaKhuyenMai();
                    final String hinhanhsanpham = gioHangs.get(getLayoutPosition()).getHinhAnhSanPham();
                    final String khoiluongsanpham = gioHangs.get(getLayoutPosition()).getKhoiLuong();
                    final int soluongsanphamcu = Integer.parseInt(textViewSoLuongItem.getText().toString());
                    final int idhang = gioHangs.get(getLayoutPosition()).getIdHang();
                    final String moTa = gioHangs.get(getLayoutPosition()).getMoTa();
                    final String thuongHieu = gioHangs.get(getLayoutPosition()).getThuongHieu();
                    final String xuatXu = gioHangs.get(getLayoutPosition()).getXuatXu();
                    final String tenSanPhamDE = gioHangs.get(getLayoutPosition()).getTenSanPhamDE();
                    final String moTaDE = gioHangs.get(getLayoutPosition()).getMoTaDE();
                    final int idShopBan = gioHangs.get(getLayoutPosition()).getIdShopBan();

                    final int soluongsanphammoi;
                    soluongsanphammoi = soluongsanphamcu - 1;
                    textViewSoLuongItem.setText(soluongsanphammoi + "");
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            //TODO SALE
                            mDb.gioHangDao().updateGioHang(new GioHangEntry(id, idsanpham, tensanpham, Math.round(giasanpham * soluongsanphammoi /soluongsanphamcu * 100.0) / 100.0, Math.round(giakhuyenmai * soluongsanphammoi /soluongsanphamcu * 100.0) / 100.0, hinhanhsanpham, khoiluongsanpham, soluongsanphammoi, idhang, moTa, thuongHieu, xuatXu, tenSanPhamDE, moTaDE, idShopBan));
                        }
                    });
                    if (soluongsanphammoi < 2) {
                        buttonGiam.setVisibility(View.INVISIBLE);
                        buttonTang.setVisibility(View.VISIBLE);
                        textViewSoLuongItem.setText(soluongsanphammoi + "");
                    } else {
                        buttonTang.setVisibility(View.VISIBLE);
                        buttonGiam.setVisibility(View.VISIBLE);
                        textViewSoLuongItem.setText(soluongsanphammoi + "");
                    }
                }
            });


        }
    }
    @Override
    public int getItemCount() {
        if (gioHangs == null)
            return 0;

        return gioHangs.size();
    }

    public List<GioHangEntry> getGioHangs() {
        return gioHangs;
    }

    public  void setGioHangs (List<GioHangEntry> gioHangs){
        this.gioHangs = gioHangs;
        notifyDataSetChanged();
    }

}












