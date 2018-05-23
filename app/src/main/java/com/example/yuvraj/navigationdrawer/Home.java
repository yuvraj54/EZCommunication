package com.example.yuvraj.navigationdrawer;

/**
 * Created by swapnil on 12/24/2016.
 */


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


/**
 * Created by yuvraj on 1/29/2018.
 */
public class Home extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.home, null);



        Button btn_news = (Button)root.findViewById(R.id.btn_news);
        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),News.class);
                startActivity(i);
            }
        });
        //return root;



        Button btn_announcement = (Button)root.findViewById(R.id.btn_announcement);
        btn_announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Announcement.class);
                startActivity(i);
            }
        });


        Button btn_profile = (Button)root.findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Profile.class);
                startActivity(i);
            }
        });


        Button btn_globalnews = (Button)root.findViewById(R.id.btn_globalnews);
        btn_globalnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),GlobalNews.class);
                startActivity(i);
            }
        });


        Button btnfav = (Button)root.findViewById(R.id.btnfav);
        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),News_fav.class);
                startActivity(i);
            }
        });




        return root;
    }
}