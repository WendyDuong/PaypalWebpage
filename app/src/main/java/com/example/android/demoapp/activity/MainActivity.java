package com.example.android.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android.demoapp.Presenter.PresenterLogicXuLyMenu;
import com.example.android.demoapp.R;
import com.example.android.demoapp.View.DangNhap.DangNhapActivity;
import com.example.android.demoapp.ViewModel.MainViewModel;
import com.example.android.demoapp.adapter.CategoryAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.database.YeuThichEntry;
import com.example.android.demoapp.model.ModelDangNhap;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GoogleSignInAccount account ;
    ListView listView;
    Toolbar toolbar;
    public static final int RC_SIGN_IN = 1;
    DrawerLayout drawerLayout;
    ImageView imageView;
    CategoryAdapter viewPageAdapter;
    TabLayout.Tab tabGioHang;
    TabLayout.Tab tabYeuThich;
    public static BadgeDrawable badgeDrawableYeuthich;
    public static BadgeDrawable badgeDrawableGioHang;


    private AppDatabase mDb;
    List<GioHangEntry> gioHangEntries;
    List<YeuThichEntry> yeuThichEntries;


    public  static boolean mainActivityOnCreat = false;
    AccessToken accessToken;
    PresenterLogicXuLyMenu logicXuLyMenu;
    ViewPager2 viewPager2;
    public static TabLayout tabLayout;
    Button buttonDangNhap;
    Button buttonDangXuat;
    GoogleSignInClient mGoogleSignInClient ;
    ModelDangNhap modelDangNhap = new ModelDangNhap();

    TextView textViewTenKhachHang;
    String tennguoidung = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mainActivityOnCreat = true;
        ArrayList<String> caNhan = new ArrayList<String>();
        caNhan.add(getString(R.string.thong_tin_ca_nhan));
        caNhan.add(getString(R.string.hinh_thuc_thanh_toan));
        caNhan.add(getString(R.string.cac_don_hang));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);

            }
        });

        ArrayAdapter<String> caNhanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, caNhan);
        listView.setAdapter(caNhanAdapter);


      buttonDangNhap.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intentDangNhap = new Intent(MainActivity.this, DangNhapActivity.class);
               startActivity(intentDangNhap);
          }
      });



        logicXuLyMenu = new PresenterLogicXuLyMenu();


   buttonDangXuat.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           if (accessToken != null) {
               LoginManager.getInstance().logOut();
               Toast.makeText(MainActivity.this, "Đăng Xuất Thành Công!", Toast.LENGTH_SHORT).show();
               buttonDangNhap.setVisibility(View.VISIBLE);
               buttonDangXuat.setVisibility(View.GONE);
               textViewTenKhachHang.setText("Chào Mừng Quý Khách");
           }
           if(account!= null)
           {
               mGoogleSignInClient = modelDangNhap.LayGoogleSignInClient(MainActivity.this);
               mGoogleSignInClient.signOut()
                       .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               Toast.makeText(MainActivity.this, "Đăng Xuất Thành Công!", Toast.LENGTH_SHORT).show();
                               buttonDangNhap.setVisibility(View.VISIBLE);
                               buttonDangXuat.setVisibility(View.GONE);
                               textViewTenKhachHang.setText("Chào Mừng Quý Khách");
                           }
                       });

           }

           if(!modelDangNhap.LayCachedDangNhap(MainActivity.this).equals("")){
               Toast.makeText(MainActivity.this, "Đăng Xuất Thành Công!", Toast.LENGTH_SHORT).show();
               modelDangNhap.CapNhatCachedDangNhap(MainActivity.this,"");
               buttonDangNhap.setVisibility(View.VISIBLE);
               buttonDangXuat.setVisibility(View.GONE);
               textViewTenKhachHang.setText("Chào Mừng Quý Khách");

       }
   }});

    }


    @Override
    public void onBackPressed() {
       if(drawerLayout.isDrawerOpen(GravityCompat.END)){
           drawerLayout.closeDrawer(GravityCompat.END);
       }else{
        super.onBackPressed();}
    }






        private void init () {
            textViewTenKhachHang = findViewById(R.id.ten_khach_hang);
            buttonDangNhap = findViewById(R.id.button_dang_nhap);
            buttonDangXuat = findViewById(R.id.button_dang_xuat);
            toolbar = findViewById(R.id.toolbar);
            drawerLayout = findViewById(R.id.drawer_layout);
            imageView = findViewById(R.id.anhcanhan);
            listView = findViewById(R.id.list_view);
            viewPager2 = (ViewPager2) findViewById(R.id.viewpager);
            viewPageAdapter = new CategoryAdapter(this);
            viewPager2.setAdapter(viewPageAdapter);

            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    switch (position){
                        case 0 : {
                            tab.setIcon(R.drawable.logokhongnen);
                            break;
                        }
                        case 1 : {
                                tab.setIcon(R.drawable.kinh_lup_icon);
                                break;
                            }
                        case 2 :
                        { tab.setIcon(R.drawable.timdo_bar);
                            break;
                        }
                        case 3:
                        { tab.setIcon(R.drawable.xe_hang);
                           break;
                    }
                }
            } });

            tabLayoutMediator.attach();

            tabYeuThich = tabLayout.getTabAt(2);
            tabGioHang = tabLayout.getTabAt(3);

            badgeDrawableGioHang = tabGioHang.getOrCreateBadge();
            badgeDrawableYeuthich = tabYeuThich.getOrCreateBadge();

            mDb = AppDatabase.getInstance(getApplicationContext());
            MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
            mainViewModel.getGioHang().observe(this, new Observer<List<GioHangEntry>>() {
                @Override
                public void onChanged(@Nullable List<GioHangEntry> gioHang) {
                    gioHangEntries = gioHang;
                    int sosanphammua = 0;

                    if (gioHangEntries.size() > 0){
                        for (int i = 0; i < gioHangEntries.size(); i++) {
                            sosanphammua += gioHangEntries.get(i).getSoLuong();
                        }
                        badgeDrawableGioHang.setVisible(true);

                        badgeDrawableGioHang.setNumber(sosanphammua);
                    }
                    else
                        badgeDrawableGioHang.setVisible(false);
                }
            });

            mainViewModel.getYeuThich().observe(this, new Observer<List<YeuThichEntry>>() {
                @Override
                public void onChanged(@Nullable List<YeuThichEntry> yeuThich) {
                    yeuThichEntries = yeuThich;
                    if (yeuThichEntries.size()>0){
                        badgeDrawableYeuthich.setVisible(true);
                        badgeDrawableYeuthich.setNumber(yeuThichEntries.size());

                    }
                    else
                        badgeDrawableYeuthich.setVisible(false);

                }
            });






        }

/*

    @Override
    protected void onRestart() {
        setupbadgeDrawable();
        viewPageAdapter.notifyDataSetChanged();


        super.onRestart();
    }

    @Override
    protected void onResume() {
        accessToken = logicXuLyMenu.LayTokenDungFacebook();
        account = GoogleSignIn.getLastSignedInAccount(this);
        if(accessToken != null ){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        textViewTenKhachHang.setText(tennguoidung);
                        buttonDangNhap.setVisibility(View.GONE);
                        buttonDangXuat.setVisibility(View.VISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields","name");

            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }

        if (account != null)
        {
            textViewTenKhachHang.setText(account.getDisplayName());
            buttonDangNhap.setVisibility(View.GONE);
            buttonDangXuat.setVisibility(View.VISIBLE);

        }

        String tenkhachhang = modelDangNhap.LayCachedDangNhap(this);
        if(!tenkhachhang.equals("")){
            textViewTenKhachHang.setText(tenkhachhang);
            buttonDangNhap.setVisibility(View.GONE);
            buttonDangXuat.setVisibility(View.VISIBLE);
        }




        super.onResume();
    }*/
}




