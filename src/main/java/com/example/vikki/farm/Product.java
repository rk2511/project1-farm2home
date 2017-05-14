package com.example.vikki.farm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by vikki on 5/7/2017.
 */

public class Product extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);}

        public void aboutMilk(View view)
                    {
                        Intent i1 = new Intent(Product.this, AboutMilk.class);
                        startActivity(i1);

    }
    }
