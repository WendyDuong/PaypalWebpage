package com.example.android.demoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.demoapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class PaymentDetails extends AppCompatActivity {
//TODO PAYMENT
    TextView txtId, txtAmount, txtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        //txtId = findViewById(R.id.txtId);
        txtAmount = findViewById(R.id.txtAmount);
        //txtStatus = findViewById(R.id.txtStatus);

        //Get Intent
        Intent intent =  getIntent();
        String tongTien = intent.getStringExtra("tongTien");
        txtAmount.setText(tongTien);



    }

    public void BackToHome(View view) {
        Intent IntentBackToHomeSite = new Intent(PaymentDetails.this, MainActivity.class);
        startActivity(IntentBackToHomeSite);

    }


}