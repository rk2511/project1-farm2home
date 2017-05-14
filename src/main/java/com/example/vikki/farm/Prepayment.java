package com.example.vikki.farm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;




/**
 * Created by vikki on 5/12/2017.
 */

public class Prepayment extends AppCompatActivity {
    TextView tv,tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prepayment);
    }
public void callPayment(View view){
    tv=(TextView) findViewById(R.id.textView3);
    tv1=(TextView) findViewById(R.id.textView8);
    String inp = tv.getText().toString();
    Intent i1 = new Intent(Prepayment.this, PaymentActivity.class);
    i1.putExtra("message", inp);

    startActivity(i1);

    }

    }
