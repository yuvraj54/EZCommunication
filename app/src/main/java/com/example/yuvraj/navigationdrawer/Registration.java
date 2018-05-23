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

public class Registration extends AppCompatActivity {

    private static final String REGISTER_URL = "http://ezcommunication.somee.com/webservice.asmx/student";
    Button btnsubmit;
    EditText edt_enrollment;
    EditText edt_name;
    EditText edt_branch;
    EditText edt_sem;
    EditText edt_user_id;
    EditText edt_pass;
    EditText edt_phone;
    EditText edt_mac;
    ProgressDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);


        edt_enrollment = (EditText)findViewById(R.id.edt_enrollment) ;
        edt_name = (EditText)findViewById(R.id.edt_name) ;
        edt_user_id = (EditText)findViewById(R.id.edt_user_id) ;
        edt_branch = (EditText)findViewById(R.id.edt_branch) ;
        edt_sem = (EditText)findViewById(R.id.edt_sem) ;
        edt_pass = (EditText)findViewById(R.id.edt_pass) ;
        edt_phone = (EditText) findViewById(R.id.edt_phone) ;
        edt_mac = (EditText) findViewById(R.id.edt_mac) ;
        btnsubmit = (Button)findViewById(R.id.btnsubmit);



                btnsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String name = edt_name.getText().toString();
                        final String enroll = edt_enrollment.getText().toString();
                        final String uid = edt_user_id.getText().toString();
                        final String pwd = edt_pass.getText().toString();
                        final String branch = edt_branch.getText().toString();
                        final String sem = edt_sem.getText().toString();
                        final String phone = edt_phone.getText().toString();
                        final String mac = edt_mac.getText().toString();

                        //final String selectarea = SelectArea.getSelectedItem().toString();



                        if (!isValidFirstName(name)) {
                            edt_name.setError("Invalid Name");
                        }

                        else {

                            pdialog=new ProgressDialog(Registration.this);
                            pdialog.setMessage("Getting you in...");
                            pdialog.show();


                            //  Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                            StringRequest stringRequest = new StringRequest(Request.Method.POST,REGISTER_URL,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            // Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();
                                            String xyz = response.toString().trim();



                                            String abc = "success";
                                            if (response.replace("\"", "").equals(abc)) {


                                            //String abc = "Success";
                                            //  Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();
                                            //if (response.trim().equals("Success")) {

                                                pdialog.cancel();
                                                edt_name.setText("");
                                                edt_enrollment.setText("");
                                                edt_sem.setText("");
                                                edt_branch.setText("");
                                                edt_user_id.setText("");
                                                edt_pass.setText("");
                                                edt_phone.setText("");
                                                edt_mac.setText("");

                                                Toast.makeText(getApplicationContext(), "Successfully Registered ...", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(getApplicationContext(), Login.class);
                                                startActivity(i);

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Something Went Wrong..", Toast.LENGTH_LONG).show();
                                                pdialog.cancel();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(Registration.this,error.toString(),Toast.LENGTH_LONG).show();
                                        }


                                    }){

                                @Override
                                protected Map<String,String> getParams(){
                                    Map<String,String> params = new HashMap<String, String>();

                                    params.put("name",name);
                                    params.put("enroll",enroll);
                                    params.put("uid",uid);
                                    params.put("pwd",pwd);
                                    params.put("sem",sem);
                                    params.put("branch",branch);
                                    params.put("phone",phone);
                                    params.put("mac",mac);

                                    params.put("status","fail");
                                    //params.put("status","inactive");


                                    return params;
                                }

                            };
                            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                                    0,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            RequestQueue requestQueue = Volley.newRequestQueue(Registration.this);

                            //Adding request to the queue
                            requestQueue.add(stringRequest);

                        }




                    }

                    // validating User Name
                    private boolean isValidFirstName(String name) {
                        if (edt_name != null && edt_name.length() > 3) {
                            return true;
                        }
                        return false;
                    }

                    // validating Password
                    private boolean isValidPassword(String password) {
                        if (password != null && password.length() >= 7) {
                            return true;
                        }
                        return false;
                    }


                    // validating email id
                    private boolean isValidEmail(String email) {
                        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                        Matcher matcher = pattern.matcher(email);
                        return matcher.matches();
                    }


                    // validating Number
                    private boolean isValidNumber(String number) {
                        if (number != null && number.length() == 10) {
                            return true;
                        }
                        return false;
                    }


                    // validating Address
                    private boolean isValidAddress(String address) {
                        if (address != null && address.length() > 3) {
                            return true;
                        }
                        return false;
                    }

                    // validating city name
                    private boolean isValidCity(String city) {
                        if (city != null && city.length() > 3) {
                            return true;
                        }
                        return false;
                    }

                    // validating State name
                    private boolean isValidState(String state) {
                        if (state != null && state.length() > 3) {
                            return true;
                        }
                        return false;
                    }

                    // validating Country name
                    private boolean isValidCountry(String country) {
                        if (country != null && country.length() > 3) {
                            return true;
                        }
                        return false;
                    }

                    // validating postal Number
                    private boolean isValidPin_code(String pin_code) {
                        if (pin_code != null && pin_code.length() == 6) {
                            return true;
                        }
                        return false;
                    }



                });






            }








        }
