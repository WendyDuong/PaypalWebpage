package com.example.android.demoapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.demoapp.ConnectInternet.DownloadJSON;
import com.example.android.demoapp.ultil.Server;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;


    public String LayCachedDangNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tenkhachhang","");

        return tennv;
    }

    public void CapNhatCachedDangNhap(Context context,String tenv){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tenkhachhang",tenv);

        editor.commit();
    }

    public boolean KiemTraDangNhap(Context context,String diachiemail, String matkhau){
        boolean kiemtra = false;
        String duongdan = Server.duongdankiemtradangnhap;
        List<HashMap<String,String>> attrs = new ArrayList<>();


        HashMap<String,String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("diachiemail",diachiemail);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);

        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String jsonKetQua = jsonObject.getString("ketqua");
            if(jsonKetQua.equals("true")){
                kiemtra = true;
                String tenkhachhang = jsonObject.getString("tenkhachhang");
                CapNhatCachedDangNhap(context,tenkhachhang);
            }else{
                kiemtra = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

    public AccessToken LayTokenFacebookHienTai() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;

    }

    public GoogleSignInClient LayGoogleSignInClient(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
       GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(context, gso);

         return mGoogleSignInClient;
    }
}
