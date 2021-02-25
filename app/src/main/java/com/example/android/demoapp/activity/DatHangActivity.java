package com.example.android.demoapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.Config.Config;
import com.example.android.demoapp.R;
import com.example.android.demoapp.Retrofit.IAppChauAAPI;
import com.example.android.demoapp.ViewModel.DatHangViewModel;
import com.example.android.demoapp.adapter.DatHangAdapter;
import com.example.android.demoapp.database.AppDatabase;
import com.example.android.demoapp.database.GioHangEntry;
import com.example.android.demoapp.utils.CheckConnection;
import com.example.android.demoapp.utils.Common;
import com.example.android.demoapp.utils.Server;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class DatHangActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    DatHangAdapter datHangAdapter;
    List<GioHangEntry> mDatHangs;
    TextView tongTien;
    double tongtien;
    RecyclerView datHangRecyclerView;
    EditText editTextTen, editTextSoDt, editTextDiaChi, editTextEmail;
    Button buttonDatHang, buttonMuaTiep;
    private double tongTienDonHang = 0;
    private AppDatabase mDb;
    IAppChauAAPI mService;
    IAppChauAAPI mServiceScalars;
    private final static int PAYMENT_REQUEST_CODE = 9797;
    String ten, sdt, email, diachi = "", language, summe, inputError, noInternet;

    //Global string
    String token, amount, orderAddress, orderComment;
    HashMap<String, String> hashMap;

    //To check whether email is right email format
    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    //registerOnSharedPreferenceChangeListener
    @Override
    public void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    //unregisterOnSharedPreferenceChangeListener
    @Override
    public void onStop() {
        super.onStop();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.settings_language_key))){

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

            //reset language after setting was changed
            language = sharedPrefs.getString(
                    getString(R.string.settings_language_key),
                    getString(R.string.settings_language_default)
            );

        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat_hang_activity);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        language = sharedPrefs.getString(
                getString(R.string.settings_language_key),
                getString(R.string.settings_language_default)
        );

        mService = Common.getAPI();
        mServiceScalars = Common.getScalarsAPI();
        mDb = AppDatabase.getInstance(this);

        datHangRecyclerView = findViewById(R.id.recycler_view_dat_hang);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 720)
            datHangRecyclerView.setLayoutManager(new GridLayoutManager(DatHangActivity.this, 2));
        else
            datHangRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        datHangAdapter = new DatHangAdapter(this, language);
        datHangRecyclerView.setAdapter(datHangAdapter);

        tongTien = findViewById(R.id.tong_so_tien_hang_dat);
        buttonDatHang = findViewById(R.id.dat_hang_button);
        buttonMuaTiep = findViewById(R.id.mua_tiep_button);
        editTextTen = findViewById(R.id.editText_ten);
        editTextSoDt = findViewById(R.id.editText_so_dt);
        editTextDiaChi = findViewById(R.id.editText_dia_chi);
        editTextEmail = findViewById(R.id.editTextText_email);


        //Setting language
        switch (language) {
            case "de":
                buttonDatHang.setText(R.string.buy_now_de);
                buttonMuaTiep.setText(R.string.buy_more_de);
                summe = getResources().getString(R.string.summe_de);
                inputError = "Eingabe ungültig! Überprüfen Sie Ihre Email-Eingabe und Füllen Sie alle Filed aus!";
                noInternet = "Überprüfen Sie Ihre Internetverbindung!";

                break;
            case "vn":
                buttonDatHang.setText(R.string.buy_now_vn);
                buttonMuaTiep.setText(R.string.buy_more_vn);
                summe = getResources().getString(R.string.summe_vn);
                inputError = "Thông tin cá nhân còn thiếu hoặc địa chỉ email chưa đúng!";
                noInternet = "Kiểm tra kết nối internet!";
                break;
        }


        DatHangViewModel datHangViewModel = ViewModelProviders.of(this).get(DatHangViewModel.class);
        datHangViewModel.getDatHang().observe(this, new Observer<List<GioHangEntry>>() {
            @Override
            public void onChanged(@Nullable List<GioHangEntry> datHangs) {
                mDatHangs = datHangs;
                assert mDatHangs != null;

                //TODO SALE
                for (int i = 0; i < mDatHangs.size(); i++) {
                    if (mDatHangs.get(i).getGiaKhuyenMai() != 0) {
                        tongtien = mDatHangs.get(i).getGiaKhuyenMai();
                    } else {
                        tongtien = mDatHangs.get(i).getGiaSanPham();
                    }
                    tongTienDonHang += tongtien;
                }
                double gialamtron = Math.round(tongTienDonHang * 100.0) / 100.0;

                tongTien.setText(summe + ": €" + gialamtron);
                datHangAdapter.setDatHang(mDatHangs);
            }
        });


        buttonMuaTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMuaTiep = new Intent(DatHangActivity.this, MainActivity.class);
                startActivity(intentMuaTiep);
            }
        });

        loadToken();

        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten = editTextTen.getText().toString().trim();
                sdt = editTextSoDt.getText().toString().trim();
                email = editTextEmail.getText().toString().trim();
                diachi = editTextDiaChi.getText().toString().trim();

                if (CheckConnection.haveNetworkConnection(DatHangActivity.this)) {

                    if (ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0 && isValidEmail(email)) {

                        //Step 3. The customer submits payment information, the client SDK communicates that information to Braintree and
                        // returns a payment method nonce
                        DropInRequest dropInRequest = new DropInRequest().clientToken(token);
                        startActivityForResult(dropInRequest.getIntent(DatHangActivity.this), PAYMENT_REQUEST_CODE);
                    } else {
                        Toast.makeText(DatHangActivity.this, inputError, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DatHangActivity.this, noInternet, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // upload invoice infos in Webservice
    private void UploadInvoice() {
        final RequestQueue requestQueue = Volley.newRequestQueue(DatHangActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
            @Override
            public void onResponse(final String madonhang) {
                if (Integer.parseInt(madonhang) > 0) {
                    RequestQueue requestQueue1 = Volley.newRequestQueue(DatHangActivity.this);
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("1")) {
                                for (int i = 0; i < mDatHangs.size(); i++) {

                                    final int finalI = i;
                                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            mDb.gioHangDao().deleteGioHang(mDatHangs.get(finalI));
                                        }

                                    });
                                }
                            } else {
                                Toast.makeText(DatHangActivity.this, "Lỗi không tiến hành đặt hàng được!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            JSONArray jsonArray = new JSONArray();
                            for (int i = 0; i < mDatHangs.size(); i++) {
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    //put order details into table chitietdonhang
                                    jsonObject.put("madonhang", madonhang);
                                    jsonObject.put("masanpham", mDatHangs.get(i).getId());
                                    jsonObject.put("tensanpham", mDatHangs.get(i).getTenSanPham());
                                    jsonObject.put("giasanpham", mDatHangs.get(i).getGiaSanPham());
                                    jsonObject.put("giakhuyenmai", mDatHangs.get(i).getGiaKhuyenMai());
                                    jsonObject.put("soluongsanpham", mDatHangs.get(i).getSoLuong());
                                    jsonObject.put("idShopBan", mDatHangs.get(i).getIdShopBan());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                jsonArray.put(jsonObject);
                            }
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("json", jsonArray.toString());
                            return hashMap;
                        }
                    };
                    requestQueue1.add(stringRequest1);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //put user input into table donhang
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("tenkhachhang", ten);
                hashMap.put("sodienthoai", sdt);
                hashMap.put("email", email);
                hashMap.put("diachi", diachi);

                return hashMap;
            }
        };
        requestQueue.add(stringRequest);


    }


    //Step 1. requests a client token from server
    //Step2. server generates and sends a client token back to client using the server SDK.
    private void loadToken() {
        final AlertDialog waitingDialog = new SpotsDialog(DatHangActivity.this);
        waitingDialog.show();
        waitingDialog.setMessage("Please wait...");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Common.API_TOKEN_URL, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                waitingDialog.dismiss();
                buttonDatHang.setEnabled(false);

                Toast.makeText(DatHangActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                waitingDialog.dismiss();
                token = responseString.trim();
                Toast.makeText(DatHangActivity.this, "enable", Toast.LENGTH_SHORT).show();

                buttonDatHang.setEnabled(true);


            }
        });
    }


    //Show Drop-in
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PAYMENT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                String strNonce = nonce.getNonce();

                amount = String.valueOf(tongTienDonHang);
                hashMap = new HashMap<>();
                hashMap.put("amount", amount);
                hashMap.put("nonce", strNonce);
                sendPayment();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(DatHangActivity.this, "Payment cancelled!", Toast.LENGTH_SHORT).show();

            } else {
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.e("APP_ERROR", error.getMessage());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void sendPayment() {
        RequestQueue queue = Volley.newRequestQueue(DatHangActivity.this);

        //Step 4. front-end sends the payment method nonce to server.
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Common.API_SEND_PAYMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Step 5. server code receives the payment method nonce and then uses the server SDK to create a transaction.
                        if (response.contains("Successful")) {
                            Toast.makeText(DatHangActivity.this, "Transaction successful", Toast.LENGTH_SHORT).show();
                            UploadInvoice();
                            Intent intent = new Intent(DatHangActivity.this, PaymentDetails.class);
                            intent.putExtra("tongTien", String.valueOf(tongTienDonHang));
                            startActivity(intent);
                        } else
                            Toast.makeText(DatHangActivity.this, "Transaction failed", Toast.LENGTH_LONG).show();
                        Log.d("mylog", "Final Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mylog", "Volley error : " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                if (hashMap == null)
                    return null;
                Map<String, String> params = new HashMap<>();
                for (String key : hashMap.keySet()) {
                    params.put(key, hashMap.get(key));
                    Log.d("mylog", "Key : " + key + " Value : " + hashMap.get(key));
                }

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(stringRequest);
    }
/*
    private void sendPayment() {
        mServiceScalars.payment(hashMap.get("nonce"), hashMap.get("amount"))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                        assert response.body() != null;
                        Log.d("APP_DEBUG", "respond is ready!!");
                        String c = response.body().toString();
                        if (response.body().toString().contains("Successful")) {
                            Toast.makeText(DatHangActivity.this, "Transaction successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DatHangActivity.this, PaymentDetails.class);
                            startActivity(intent);
                            Log.d("APP_DEBUG", "Transaction successful!");


                            //Submit order
                        } else {
                            Toast.makeText(DatHangActivity.this, "Transaction failed!", Toast.LENGTH_SHORT).show();
                            Log.d("APP_DEBUG", "Transaction failed!");

                        }

                        Log.d("APP_ERROR1", response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("APP_ERROR2", t.getMessage());
                    }
                });
    }
*/
}

