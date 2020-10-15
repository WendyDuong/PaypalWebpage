package com.example.android.demoapp.View.DangNhap.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.demoapp.R;
import com.example.android.demoapp.ultil.Server;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo S410p on 6/29/2016.
 */
public class FragmentDangKy extends Fragment  implements View.OnClickListener,View.OnFocusChangeListener {

    Button btnDangKy;
    EditText edHoTen,edMatKhau,edNhapLaiMatKhau,edDiaChiEmail;
    TextInputLayout input_edHoTen;
    TextInputLayout input_edMatKhau;
    TextInputLayout input_edNhapLaiMatKhau;
    TextInputLayout input_edDiaChiEmail;
    Boolean kiemtrathongtin = false;
     String email, hoten, matkhau;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky,container,false);

        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
        edHoTen = (EditText) view.findViewById(R.id.edHoTenDK);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = (EditText) view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmail = (EditText) view.findViewById(R.id.edDiaChiEmailDK);
        input_edHoTen = (TextInputLayout) view.findViewById(R.id.input_edHoTenDK);
        input_edMatKhau = (TextInputLayout) view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = (TextInputLayout)view.findViewById(R.id.input_edDiaChiEmailDK);


        btnDangKy.setOnClickListener(this);
        edHoTen.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);

        return view;
    }





    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.btnDangKy:
                btnDangKy();
                break;
        }
    }

    private void btnDangKy(){
        hoten = edHoTen.getText().toString();
        email = edDiaChiEmail.getText().toString();
        matkhau = edMatKhau.getText().toString();
        //String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();


        if(kiemtrathongtin) {

            if (hoten.length() > 0  && email.length() >0 && matkhau.length() > 0){
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandangki, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String idkhachhang) {
                        Log.d("idkhachhang",idkhachhang);
                        if(Integer.parseInt(idkhachhang)>0){

                            Toast.makeText(getActivity(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                        }
                    }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("tenkhachhang",hoten);
                            hashMap.put("diachiemail",email);
                            hashMap.put("matkhau",matkhau);
                            return hashMap;
                        }
                    };
                 requestQueue.add(stringRequest);


                }else{
                    Toast.makeText(getActivity(), "Thông tin cá nhân còn thiếu", Toast.LENGTH_SHORT).show();

                }


        }


    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edHoTenDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Họ và tên còn trống !");
                        kiemtrathongtin = false;
                    }else{
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;

            case R.id.edDiaChiEmailDK:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Địa chỉ email còn trống !");
                        kiemtrathongtin = false;
                    }else{

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail){
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        }else{
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                break;

            case R.id.edMatKhauDK:
                break;

            case R.id.edNhapLaiMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    }else{
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                        input_edNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                break;

        }
    }


}




