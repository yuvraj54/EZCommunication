package com.example.yuvraj.navigationdrawer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yuvraj.navigationdrawer.Home;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 7/31/2017.
 */

public class Feedback extends AppCompatActivity {
    TextView email;
    EditText feedback;
    Button submit;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    public static final String REQUESTED_URL="http://www.studymaterial.somee.com/WebService.asmx/feedback";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        email=(TextView) findViewById(R.id.textView10);
        feedback=(EditText)findViewById(R.id.editText10);
        submit=(Button)findViewById(R.id.button9);
        requestQueue= Volley.newRequestQueue(Feedback.this);
        SharedPreferences sharedPreferences=getSharedPreferences("User", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("email")){
            String abc=sharedPreferences.getString("email","");
            email.setText(abc);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    call(REQUESTED_URL);
                }
            });

        }
    }
    private void call(String a){
        final String user=email.getText().toString();
        final String feed=feedback.getText().toString();
        progressDialog=new ProgressDialog(Feedback.this);
        progressDialog.setMessage("LOading");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, a, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Feedback.this,response,Toast.LENGTH_LONG).show();
                progressDialog.cancel();
                Intent intent=new Intent(Feedback.this,Home.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Feedback.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        })  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map=new HashMap();
                map.put("email",user);
                map.put("descr",feed);
                return map;
            }

        };
        requestQueue.add(stringRequest);
    }
}
