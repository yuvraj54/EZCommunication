package com.example.yuvraj.navigationdrawer;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {



    Button btnsignup;
    EditText uname, pwd;
    Button btnlogin;
    ProgressDialog pdialog;
//fetch profile
    RequestQueue requestQueue;
    List<Actors> GetDataAdapter1;


    private static final String LOGIN_URL = "http://ezcommunication.somee.com/webservice.asmx/User_Login";


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String KEY_EMAIL = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnsignup = (Button)findViewById(R.id.btnsignup);
        uname = (EditText) findViewById(R.id.uname);
        pwd = (EditText) findViewById(R.id.pwd);





        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String username = uname.getText().toString();
                final String password = pwd.getText().toString();

                pdialog = new ProgressDialog(Login.this);
                pdialog.setMessage("Login...");
                pdialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();
                                String abc = "fail";
                                if (response.replace("\"", "").equals(abc)) {


                                    pdialog.cancel();
                                    Toast.makeText(getApplicationContext(), "username and Password was wrong..", Toast.LENGTH_LONG).show();


                                } else {
                                    pdialog.cancel();

                                    SharedPreferences  sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("username" , username);
                                    editor.commit();


                                    //fetch profile
                                    //SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                    final String email = (sharedpreferences.getString("username", ""));


                                    requestQueue = Volley.newRequestQueue(getApplicationContext());




                                    StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "http://ezcommunication.somee.com/webservice.asmx/Fetch_SomeDetail_Student",
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                  //  Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();
                                                    try {

                                                        JSONArray user1 = new JSONArray(response);
                                                        for (int i = 0; i < user1.length(); i++) {

                                                            // Actors GetDataAdapter1 = new Actors();
                                                            JSONObject c = user1.getJSONObject(i);



                                                            String branch =(c.getString("stdbranch"));

                                                            SharedPreferences  sharedpreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                                            editor.putString("studentbranch" , branch);



                                                            String collage =(c.getString("stdcollage"));

                                                            editor.putString("studentcollage" , collage);
                                                            editor.commit();


                                                        }

                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        Toast.makeText(getApplicationContext(),
                                                                "Error: " + e.getMessage(),
                                                                Toast.LENGTH_LONG).show();

                                                    }


                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                                }
                                            }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> map = new HashMap<String, String>();
                                            map.put("User_Email", email);
                                            return map;
                                        }

                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                                    requestQueue.add(stringRequest2);

                                    //done fetch profile


                                    Intent i = new Intent(Login.this, MainActivity.class);

                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();



                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("uid", username);
                        map.put("pwd", password);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);

            }
        });

        //registration intent
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Login.this, MyIdPwd.class);

                startActivity(i);

            }
        });

    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(getApplicationContext(), "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }




}
