package com.example.vikki.farm;

/**
 * Created by vikki on 5/14/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {


    private TextView textViewUserName,textViewUserMobile, textViewUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        textViewUserName = (TextView) findViewById(R.id.textViewUsername);
        textViewUserMobile = (TextView) findViewById(R.id.textViewUsermobile);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUseremail);

        textViewUserName.setText(SharedPrefManager.getInstance(this).getUserName());
        textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textViewUserMobile.setText(SharedPrefManager.getInstance(this).getUserMobile());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }


        return true;
    }

}
