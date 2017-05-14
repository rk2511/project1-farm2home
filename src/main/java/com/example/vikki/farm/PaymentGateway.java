package com.example.vikki.farm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by vikki on 5/11/2017.
 */

public class PaymentGateway extends AppCompatActivity {


    EditText name, number, emailid, cost;
    Button Paynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.phone);
        emailid = (EditText) findViewById(R.id.email);
        cost = (EditText) findViewById(R.id.cost);
        Paynow = (Button) findViewById(R.id.Paynow);
    }

    public void callGateway(View v) {

        String getFname = name.getText().toString().trim();
        String getPhone = number.getText().toString().trim();
        String getEmail = emailid.getText().toString().trim();
        String getAmt   = "10";//rechargeAmt.getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), PaymentGateway.class);
        intent.putExtra("FIRST_NAME",getFname);
        intent.putExtra("PHONE_NUMBER",getPhone);
        intent.putExtra("EMAIL_ADDRESS",getEmail);
        intent.putExtra("COST",getAmt);
        startActivity(intent);

    }

}