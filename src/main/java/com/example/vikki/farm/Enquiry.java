package com.example.vikki.farm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by vikki on 5/6/2017.
 */

public class Enquiry extends AppCompatActivity {

    String message;
    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enquiry);
       // EditText message = (EditText) findViewById(R.id.mess);
    }

    public void callMessage(View view)

    {
        x="a";
        EditText name = (EditText) findViewById(R.id.name);
        EditText phno = (EditText) findViewById(R.id.phno);
        EditText addr = (EditText) findViewById(R.id.addr);
        EditText message = (EditText) findViewById(R.id.mess);
        StringBuilder msgtext = new StringBuilder();
      //  String messageText = "abcd11";
        String name1 = name.getText().toString();
        String phno1 = phno.getText().toString();
        String addr1 = addr.getText().toString();
        String message1 = message.getText().toString();
        msgtext.append(name1);
        msgtext.append(phno1);
        msgtext.append(addr1);
        msgtext.append(message1);

        Intent i = new Intent(Enquiry.this, SendMessage.class);
        i.putExtra("message", msgtext.toString());
        i.putExtra("medium", x);
        startActivity(i);
    }

    public void callEmail(View view)

    {
        x="b";
        EditText name = (EditText) findViewById(R.id.name);
        EditText phno = (EditText) findViewById(R.id.phno);
        EditText addr = (EditText) findViewById(R.id.addr);
        EditText message = (EditText) findViewById(R.id.mess);
        StringBuilder msgtext = new StringBuilder();
        //  String messageText = "abcd11";
        String name1 = name.getText().toString();
        String phno1 = phno.getText().toString();
        String addr1 = addr.getText().toString();
        String message1 = message.getText().toString();
        msgtext.append(name1);
        msgtext.append(phno1);
        msgtext.append(addr1);
        msgtext.append(message1);

        Intent i = new Intent(Enquiry.this, SendMessage.class);
        i.putExtra("message", msgtext.toString());
        i.putExtra("medium", x);
        startActivity(i);
    }


}