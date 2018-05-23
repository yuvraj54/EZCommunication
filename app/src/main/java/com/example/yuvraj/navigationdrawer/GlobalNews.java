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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalNews extends AppCompatActivity {



    private static final String GNEWS_URL = "http://ezcommunication.somee.com/webservice.asmx/GlobalNews";



    ProgressDialog pdialog;
    RequestQueue requestQueue;
    List<Actors> GetDataAdapter1;
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView3;

    RecyclerView.Adapter recyclerViewadapter;
    ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.globalnews
        );

        //Toast.makeText(News.this, studentcollage, Toast.LENGTH_LONG).show();


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        recyclerView3 = (RecyclerView)findViewById(R.id.relativeLayout3);



        GetDataAdapter1 = new ArrayList<>();


        pdialog=new ProgressDialog(GlobalNews.this);
        pdialog.setMessage("Global News....");
        pdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GNEWS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(News.this, response, Toast.LENGTH_LONG).show();

                        try {

                            JSONArray user1 = new JSONArray(response);
                            for (int i = 0; i < user1.length(); i++) {

                                Actors GetDataAdapter2 = new Actors();
                                JSONObject c = user1.getJSONObject(i);

                                GetDataAdapter2.setId(c.getString("Id"));
                                GetDataAdapter2.setFromnm(c.getString("fromnm"));
                                GetDataAdapter2.setContent(c.getString("content"));
                                GetDataAdapter2.setImage(c.getString("image"));
                                GetDataAdapter2.setTitle(c.getString("title"));
                                GetDataAdapter2.setTimeval(c.getString("timeval"));





                                // Toast.makeText(CityHotelList.this, "imsge"+c.getString("img1"), Toast.LENGTH_LONG).show();
                                GetDataAdapter1.add(GetDataAdapter2);


                                recyclerViewadapter = new News_adapter(GetDataAdapter1, getApplicationContext());
                                recyclerView3.setAdapter(recyclerViewadapter);

//                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                int numberOfColumns = 1;
                                recyclerView3.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumns));


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();

                        }


                        pdialog.cancel();



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();

                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(GlobalNews.this);
        requestQueue.add(stringRequest);

    }


}
