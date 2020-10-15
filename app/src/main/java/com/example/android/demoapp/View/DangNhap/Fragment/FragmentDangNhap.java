package com.example.android.demoapp.View.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.MainActivity;
import com.example.android.demoapp.model.ModelDangNhap;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;

/**
 * Created by Lenovo S410p on 6/29/2016.
 */
public class FragmentDangNhap extends Fragment implements  View.OnFocusChangeListener {
    private static final int RC_SIGN_IN =  1;
    Button btnDangNhapGoogle;
    Button btnDangNhapFacebook;
    CallbackManager callbackManager;
    EditText edTenDangNhap,edMatKhau;
    Button btnDangNhap;
    ModelDangNhap modelDangNhap;
    Boolean kiemtrathongtin = false;
    TextInputLayout textInputEmail;
    TextInputLayout textInputMatKhau;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        modelDangNhap = new ModelDangNhap();
        btnDangNhapGoogle = view.findViewById(R.id.btnDangNhapGoogle);
        btnDangNhapFacebook = view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        edTenDangNhap = (EditText) view.findViewById(R.id.edDiaChiEmailDangNhap);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDangNhap);
        textInputEmail = view.findViewById(R.id.text_input_dia_chi_email_dang_nhap);
        textInputMatKhau = view.findViewById(R.id.text_input_layout_mat_khau_dang_nhap);

        edTenDangNhap.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);





        callbackManager = CallbackManager.Factory.create();





        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), MainActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), ""+ error, Toast.LENGTH_SHORT).show();

            }
        });


        btnDangNhapFacebook.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
          }
      });

      btnDangNhapGoogle.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent signInIntent = mGoogleSignInClient.getSignInIntent();
              startActivityForResult(signInIntent, RC_SIGN_IN);
          }
      });

    btnDangNhap.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String tendangnhap = edTenDangNhap.getText().toString();
            String matkhau = edMatKhau.getText().toString();
            if ( tendangnhap.equals("") || matkhau.equals(""))
                Toast.makeText(getActivity(), "Thông tin đăng nhập còn thiếu", Toast.LENGTH_SHORT).show();


            if(kiemtrathongtin){
            boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);
            if(kiemtra){
                Toast.makeText(getActivity()," đăng nhập thành công !",Toast.LENGTH_SHORT).show();
              Intent iTrangChu = new Intent(getActivity(), MainActivity.class);
               startActivity(iTrangChu);


            }else{
                Toast.makeText(getActivity(),"Tên đăng nhập và mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
            }

        }}
    });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                Intent iTrangChu = new Intent(getActivity(), MainActivity.class);
                startActivity(iTrangChu);
            }

    }

}


    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edDiaChiEmailDangNhap:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        textInputEmail.setErrorEnabled(true);
                        textInputEmail.setError("Địa chỉ email còn trống !");
                        kiemtrathongtin = false;
                    }else{
                        textInputEmail.setErrorEnabled(false);
                        textInputEmail.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;

            case R.id.edMatKhauDangNhap:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        textInputMatKhau.setErrorEnabled(true);
                        textInputMatKhau.setError("Mật khẩu còn trống !");
                        kiemtrathongtin = false;
                    }else{
                        textInputMatKhau.setErrorEnabled(false);
                        textInputMatKhau.setError("");
                        kiemtrathongtin = true;
                        }}
                break;


        }
    }


}


