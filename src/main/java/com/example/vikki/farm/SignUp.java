package com.example.vikki.farm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vikki on 5/12/2017.
 */

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText name, mobile, email, pass;
    private Button buttonRegister;
    private ProgressDialog progressDialog;
    private Spinner spinner;
    //private String city,area;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }

        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        spinner = (Spinner)findViewById(R.id.area);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        progressDialog = new ProgressDialog(this);
        buttonRegister.setOnClickListener(this);
    }

    private void registerUser()
    {
        final String name1 = name.getText().toString().trim();
        if(TextUtils.isEmpty(name1)) {
            name.setError("Name Cannot be empty!!");
            return;
        }
        final String mobile1 = mobile.getText().toString().trim();
        if(TextUtils.isEmpty(mobile1)) {
            mobile.setError("Mobile number Cannot be empty!!");
            return;
        }
        final String email1 = email.getText().toString().trim();
        if(TextUtils.isEmpty(email1)) {
            email.setError("Email Cannot be empty!!");
            return;
        }
        final String pass1 = pass.getText().toString().trim();
        if(TextUtils.isEmpty(pass1)) {
            pass.setError("Password Cannot be empty!!");
            return;
        }
        final String area = spinner.getSelectedItem().toString().trim();
        final String city = "Chennai";

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            //progressDialog.setMessage("Proceeding to Login...");
                            //progressDialog.show();
                            Intent i2= new Intent(SignUp.this,Login.class) ;
                            startActivity(i2);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.setMessage("Some Error...");
                        progressDialog.hide();
                        error.printStackTrace();

                        if (error instanceof TimeoutError)
                        {
                            Toast.makeText(getApplicationContext(), "error111", Toast.LENGTH_LONG).show();


                        }
                        else if (error instanceof AuthFailureError)
                        {
                            Toast.makeText(getApplicationContext(), "error2", Toast.LENGTH_LONG).show();
                        }
                        else if (error instanceof NoConnectionError)
                        {
                            Toast.makeText(getApplicationContext(), "error33", Toast.LENGTH_LONG).show();
                        }
                        else if (error instanceof ServerError)
                        {
                            Toast.makeText(getApplicationContext(), "error4", Toast.LENGTH_LONG).show();
                        }
                        else if (error instanceof NetworkError)
                        {
                            Toast.makeText(getApplicationContext(), "error5", Toast.LENGTH_LONG).show();
                        }
                        else if (error instanceof ParseError)
                        {
                            Toast.makeText(getApplicationContext(), "error6", Toast.LENGTH_LONG).show();
                        }

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name1);
                params.put("mobile", mobile1);
                params.put("email", email1);
                params.put("pass", pass1);
                params.put("city", city);
                params.put("area", area);
                return params;
            }
        };

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    public void onClick(View view) {
        if (view == buttonRegister)
            registerUser();

    }

}
