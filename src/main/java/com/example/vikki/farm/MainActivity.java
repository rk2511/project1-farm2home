package com.example.vikki.farm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        AdapterView.OnItemClickListener itemClickListener=(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position == 0)
                {

                    Intent int1 = new Intent(MainActivity.this, Product.class);
                    startActivity(int1);
                }
                if(position == 5)
                {

                    Intent myIntent = new Intent(MainActivity.this, About.class);
                    startActivity(myIntent);
                }


                if(position == 4)
                {

                    Intent myIntent = new Intent(MainActivity.this, PaymentActivity.class);
                    startActivity(myIntent);
                }

                if(position == 1)
                {

                    Intent myn =  new Intent(MainActivity.this, Enquiry.class);
                    startActivity(myn);
                }
            }
        });

        ListView listView=(ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(itemClickListener);
    }
}
