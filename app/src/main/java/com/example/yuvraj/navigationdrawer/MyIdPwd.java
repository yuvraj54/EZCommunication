package com.example.yuvraj.navigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.lang.String;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyIdPwd extends AppCompatActivity {

    private static final String REGISTER_URL = "http://ezcommunication.somee.com/webservice.asmx/Student_IdPassword_Mail";
    Button btnsubmit;
    EditText edt_email;

    ProgressDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_id_pwd);


        edt_email = (EditText)findViewById(R.id.edt_email) ;

        btnsubmit = (Button)findViewById(R.id.btnsubmit);



        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = edt_email.getText().toString();


                //final String selectarea = SelectArea.getSelectedItem().toString();



                if (!isValidEmail(email)) {
                    edt_email.setError("Invalid Email");
                }

                else {

                    pdialog=new ProgressDialog(MyIdPwd.this);
                    pdialog.setMessage("Veryfyng your email...");
                    pdialog.show();


                    //  Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,REGISTER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();
                                    String xyz = response.toString().trim();



                                    String abc = "fail";
                                    if (response.replace("\"", "").equals(abc)) {

                                        Toast.makeText(getApplicationContext(), "You are not Registered yet..please contact faculty for registration", Toast.LENGTH_LONG).show();
                                        pdialog.cancel();
                                        //String abc = "Success";
                                        //  Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();
                                        //if (response.trim().equals("Success")) {


                                    }
                                    else {
                                        pdialog.cancel();
                                        edt_email.setText("");


                                        Toast.makeText(getApplicationContext(), "Check your mail box now ...", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(getApplicationContext(), Login.class);
                                        startActivity(i);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MyIdPwd.this,error.toString(),Toast.LENGTH_LONG).show();
                                }


                            }){

                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();

                            params.put("email",email);


                            params.put("status","fail");
                            //params.put("status","inactive");


                            return params;
                        }

                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            0,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue requestQueue = Volley.newRequestQueue(MyIdPwd.this);

                    //Adding request to the queue
                    requestQueue.add(stringRequest);

                }




            }


            // validating email id
            private boolean isValidEmail(String email) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }




        });






    }








}
