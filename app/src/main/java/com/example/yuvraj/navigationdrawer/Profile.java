package com.example.yuvraj.navigationdrawer;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {



    private static final String Profile_URL = "http://ezcommunication.somee.com/webservice.asmx/View_Profile";

    RequestQueue requestQueue;
    List<Actors> GetDataAdapter1;
    //SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        final TextView tvuniversity = (TextView)findViewById(R.id.tvuniversity) ;
        final TextView tvcollage = (TextView)findViewById(R.id.tvcollage);
        final TextView tvbranch = (TextView)findViewById(R.id.tvbranch);
        final TextView tvenrollment = (TextView)findViewById(R.id.tvenrollment);
        final TextView tvname = (TextView)findViewById(R.id.tvname);

        final EditText edt_email = (EditText)findViewById(R.id.edt_email) ;
        final EditText edt_phone = (EditText)findViewById(R.id.edt_phone) ;
        final EditText edt_mac = (EditText)findViewById(R.id.edt_mac) ;
        final EditText edt_pass = (EditText)findViewById(R.id.edt_pass) ;

        final  Button btnsubmit = (Button)findViewById(R.id.btnsubmit);
        final ImageView profileimg = (ImageView)findViewById(R.id.profileimg);

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        final String email = (preferences.getString("username", ""));

       //Toast.makeText(Profile.this, email, Toast.LENGTH_LONG).show();


      requestQueue = Volley.newRequestQueue(getApplicationContext());



      final ProgressDialog pdialog = new ProgressDialog(Profile.this);
        pdialog.setMessage("Loading...");
        pdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://ezcommunication.somee.com/webservice.asmx/View_Profile",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();
                        try {

                            JSONArray user1 = new JSONArray(response);
                            for (int i = 0; i < user1.length(); i++) {

                                // Actors GetDataAdapter1 = new Actors();
                                JSONObject c = user1.getJSONObject(i);

                                String university =(c.getString("stduniversity"));
                                tvuniversity.setText(university);

                                 String collage =(c.getString("stdcollage"));
                                tvcollage.setText(collage);

                                String branch =(c.getString("stdbranch"));
                                tvbranch.setText(branch);

                                String enroll =(c.getString("stdenroll"));
                                tvenrollment.setText(enroll);

                                String name =(c.getString("stdname"));
                                tvname.setText(name);

                                String email =(c.getString("stdemail"));
                                edt_email.setText(email);

                                String phone =(c.getString("stdphone"));
                                edt_phone.setText(phone);

                                String mac =(c.getString("stdmac"));
                                edt_mac.setText(mac);

                                String password =(c.getString("stdpwd"));
                                edt_pass.setText(password);

                                String img =(c.getString("stdimage"));
                                String url = "http://ezcommunication.somee.com/"+img;
                                Glide.with(Profile.this).load(url).into(profileimg);



                                        pdialog.cancel();

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
        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
        requestQueue.add(stringRequest);



        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String newemail = edt_email.getText().toString();
                final String newpass = edt_pass.getText().toString();
                final String newphone = edt_phone.getText().toString();
                final String newmac = edt_mac.getText().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://ezcommunication.somee.com/webservice.asmx/Student_Update",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();

                                Toast.makeText(getApplicationContext(), "Successfully Updated..Please Login With Updated Data", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Profile.this, Login.class);
                                startActivity(i);


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
                        map.put("oldemail", email);
                        map.put("newemail", newemail);
                        map.put("password", newpass);
                        map.put("phone", newphone);
                        map.put("mac", newmac);


                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }

        });
    }





}
