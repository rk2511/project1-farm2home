package com.example.vikki.farm;

/**
 * Created by vikki on 5/6/2017.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vikki on 5/6/2017.
 */

public class SendMessage extends AppCompatActivity{
    public static final String EXTRA_MESSAGE= "message";
    public static final String EXTRA_MESSAGE1= "medium";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    EditText name;
    String phoneNo;
    String messageText;
    String y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks);
        Intent intent=getIntent();
        messageText=intent.getStringExtra(EXTRA_MESSAGE);
        y=intent.getStringExtra(EXTRA_MESSAGE1);
        TextView messageView1= (TextView)findViewById(R.id.textView7);
        messageView1.setText(y);
        TextView messageView= (TextView)findViewById(R.id.textView6);
        messageView.setText(messageText);
        if (messageText==null)
        {
            Toast.makeText(SendMessage.this,
                    "There is no data.", Toast.LENGTH_SHORT).show();
        }
        if (y.equals("a"))
        {
            sendSMSMessage();
        }
        if (y.equals("b"))
        {
            sendEmail();

        }

    }

    protected void sendSMSMessage()
     {
        phoneNo = "9790748636";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, messageText, null, null);
        TextView messageView1= (TextView)findViewById(R.id.textView7);
        messageView1.setText("sms sent");

     }

    public void sendEmail()
    {
        Log.i("Send email", "");

        String[] TO = {"maverickct@gmail.com"};
        String[] CC = {"viji.vm29@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Product Enquiry");
        emailIntent.putExtra(Intent.EXTRA_TEXT, messageText);

        try
        {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Email Sent...", "");
            TextView messageView1= (TextView)findViewById(R.id.textView7);
            messageView1.setText("email sent");
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            TextView messageView1= (TextView)findViewById(R.id.textView7);
            messageView1.setText("email not sent");
            Toast.makeText(SendMessage.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}



