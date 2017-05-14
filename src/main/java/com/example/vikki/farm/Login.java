package com.example.vikki.farm;

/**
 * Created by vikki on 5/14/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText mobile,pass;
    private Button buttonLogin;
    private ProgressDialog progressDialog;
    private TextView textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }

        mobile = (EditText) findViewById(R.id.mobile);
        pass = (EditText) findViewById(R.id.pass);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        textViewRegister = (TextView) findViewById(R.id.textViewRegister);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);

    }

    private void userLogin(){
        final String mobile1 = mobile.getText().toString().trim();
        if(TextUtils.isEmpty(mobile1)) {
            mobile.setError("Mobile number Cannot be empty!!");
            return;
        }

     //      if(!android.util.Patterns.PHONE.matcher(mobile1).matches()) {
      //          mobile.setError("Invalid mobile format!!");
       //         return;
      //      }

        final String pass1 = pass.getText().toString().trim();
        if(TextUtils.isEmpty(pass1)) {
            pass.setError("Password Cannot be empty!!");
            return;
        }
        progressDialog.setMessage("Trying to Login...wait");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            //progressDialog.setMessage("Fetching..");
                            //progressDialog.show();
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getString("name"),
                                                obj.getString("mobile"),
                                                obj.getString("email")
                                        );
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                      //  "bad response",
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(
                                    getApplicationContext(),
                                      "bad response",
                                    //obj.getString("message"),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                      //  Toast.makeText(getApplicationContext(),"some error",Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobile1);
                params.put("pass", pass1);
                return params;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                0,
               DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogin)
            userLogin();
        if(view == textViewRegister)
            startActivity(new Intent(this, SignUp.class));
    }
}
