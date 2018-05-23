package com.example.yuvraj.navigationdrawer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class Splash_Screen extends AppCompatActivity {

    private static final int INTERNET = 1;

    public static final String MyPREFERENCES = "MyPrefs" ;
    private int STORAGE_PERMISSION_CODE = 23;
    public static final String KEY_EMAIL = "username";

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        Thread myThread = new Thread(){
            @Override
            public void run(){
               try {
                   sleep(2500);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }

               finally{
                   sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

                   if (sharedpreferences.contains("username")) {
                       Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                       startActivity(intent);
                   }
                   else{
                       Intent intent1 = new Intent(getApplicationContext(),Login.class);

                       startActivity(intent1);
                   }
               }
            }

        };


        if (ContextCompat.checkSelfPermission(Splash_Screen.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Splash_Screen.this, new String[]{Manifest.permission.INTERNET},INTERNET);
        }
        else
        {
            myThread.start();
        }

    }
}
