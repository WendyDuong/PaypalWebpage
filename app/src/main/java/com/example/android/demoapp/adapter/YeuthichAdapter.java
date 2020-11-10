package com.example.android.demoapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ViewModel.GioHangViewModel;
import com.example.android.demoapp.activity.DetailActivity;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import org.apache.commons.math3.util.Precision;
import java.text.DecimalFormat;
import java.util.List;

public class YeuthichAdapter extends RecyclerView.Adapter<YeuthichAdapter.viewHolderYeuthich> {
    private List<YeuThichEntry> yeuThichs;
    private static final String EXTRA_SANPHAM_ID = "extraSanPhamId";
    private static final String EXTRA_HANG_ID = "extraHangId";
    private AppDatabase mDb;
    Context mcontext;
    private List<GioHangEntry> mgioHangEntries;
    public YeuthichAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public YeuthichAdapter.viewHolderYeuthich onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yeu_thich_item, parent, false);
        return new viewHolderYeuthich(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolderYeuthich holder, int position) {

        YeuThichEntry yeuThichEntry = yeuThichs.get(position);
        String tensanpham = yeuThichEntry.getTenSanPham();
        String khoiluongsanpham = yeuThichEntry.getKhoiLuong();
        int hinhanhsanpham = yeuThichEntry.getHinhAnh();
        double giasanpham = yeuThichEntry.getGiaSanPham();
        giasanpham = Precision.round(giasanpham / 1000, 0) * 1000;

        holder.tvTen.setText(tensanpham);
        holder.tvKhoiluong.setText(khoiluongsanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText(decimalFormat.format(giasanpham) + " Đ");
        holder.imgSanPham.setImageResource(hinhanhsanpham);

    }

    @Override
    public int getItemCount() {
        if (yeuThichs == null) {
            return 0;
        }
        return yeuThichs.size();
    }


    public class viewHolderYeuthich extends RecyclerView.ViewHolder {
        TextView tvTen, tvKhoiluong, tvGia;
        ImageView imgSanPham;
        Button buttonThem;
        ImageButton buttonXoa;

        public viewHolderYeuthich(final View viewTong) {
            super(viewTong);
            tvTen = viewTong.findViewById(R.id.ten_item_yeu_thich);
            tvKhoiluong = viewTong.findViewById(R.id.khoi_luong_item_yeu_thich);
            tvGia = viewTong.findViewById(R.id.gia_item_yeu_thich);
            imgSanPham = viewTong.findViewById(R.id.anh_item_yeu_thich);
            buttonThem = viewTong.findViewById(R.id.button_them);
            buttonXoa = viewTong.findViewById(R.id.button_xoa_yeu_thich);
            mDb = AppDatabase.getInstance(mcontext);

            GioHangViewModel gioHangViewModel = ViewModelProviders.of((FragmentActivity) mcontext).get(GioHangViewModel.class);
            gioHangViewModel.getGioHang().observe((FragmentActivity) mcontext, new Observer<List<GioHangEntry>>() {
                @Override
                public void onChanged(@Nullable List<GioHangEntry> gioHangEntries) {
                    mgioHangEntries = gioHangEntries;
                }
            });
            imgSanPham.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int idintent = yeuThichs.get(getLayoutPosition()).getIdSanPham();
                    int idhang = yeuThichs.get(getLayoutPosition()).getIdHang();
                    Intent intentChiTiet = new Intent(mcontext, DetailActivity.class);
                    intentChiTiet.putExtra(EXTRA_HANG_ID, idhang);
                    intentChiTiet.putExtra(EXTRA_SANPHAM_ID, idintent);
                    intentChiTiet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intentChiTiet);
                }
            });

            buttonXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder buidlder = new AlertDialog.Builder(mcontext);
                    buidlder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không ?");
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
                                    mDb.yeuThichDao().deleteYeuThich(yeuThichs.get(getLayoutPosition()));
                                }
                            });
                        }
                    });
                    AlertDialog alertDialog = buidlder.create();
                    alertDialog.show();

                }
            });
            buttonThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final int idsanphamhientai = yeuThichs.get(getLayoutPosition()).getIdSanPham();
                    final String tensanpham = yeuThichs.get(getLayoutPosition()).getTenSanPham();
                    final String khoiluongsanpham = yeuThichs.get(getLayoutPosition()).getKhoiLuong();
                    final double giasanpham = yeuThichs.get(getLayoutPosition()).getGiaSanPham();
                    final int hinhanhsanpham = yeuThichs.get(getLayoutPosition()).getHinhAnh();
                    final int idHang = yeuThichs.get(getLayoutPosition()).getIdHang();

                    if (mgioHangEntries.size() > 0) {
                        boolean exit = false;
                        for (int i = 0; i < mgioHangEntries.size(); i++) {
                            if (mgioHangEntries.get(i).getIdSanPham() == idsanphamhientai) {
                                Toast.makeText(mcontext, tensanpham + " đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                                exit = true;
                            }
                        }
                        if (!exit) {
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanphamhientai, tensanpham, Precision.round(giasanpham / 1000, 0) * 1000, hinhanhsanpham, khoiluongsanpham, 1, idHang));

                                }
                            });
                            Toast.makeText(mcontext, "Đã thêm " + tensanpham + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.gioHangDao().insertGioHang(new GioHangEntry(idsanphamhientai, tensanpham, Precision.round(giasanpham / 1000, 0) * 1000, hinhanhsanpham, khoiluongsanpham, 1, idHang));
                            }
                        });
                        Toast.makeText(mcontext, "Đã thêm " + tensanpham + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }
    }

    public List<YeuThichEntry> getYeuThichs() {
        return yeuThichs;
    }

    public void setYeuThichs(List<YeuThichEntry> yeuThichs) {
        this.yeuThichs = yeuThichs;
        notifyDataSetChanged();
    }


}





