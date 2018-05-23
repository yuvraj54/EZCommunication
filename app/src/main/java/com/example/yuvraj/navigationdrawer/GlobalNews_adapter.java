package com.example.yuvraj.navigationdrawer;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JUNED on 6/16/2016.
 */
public class GlobalNews_adapter extends RecyclerView.Adapter<GlobalNews_adapter.ViewHolder> {
    public static final String MyPREFERENCES = "News" ;

    Context context;

    List<Actors> getDataAdapter;



    public GlobalNews_adapter(List<Actors> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        final Actors getDataAdapter1 =  getDataAdapter.get(position);


        Viewholder.title.setText(getDataAdapter1.getTitle());
        Viewholder.content.setText(getDataAdapter1.getContent());
        Viewholder.fromnm.setText(getDataAdapter1.getFromnm());
        Viewholder.timeval.setText(getDataAdapter1.getTimeval());
        // Viewholder.imageView.image(getDataAdapter1.getImage());
        //  Viewholder.price.setText("$"+getDataAdapter1.getPrice());
//        Viewholder.Discount.setText("â‚¹"+getDataAdapter1.getDiscount());
//        Viewholder.Description.setText(getDataAdapter1.getDescrip());
//


//        Float stepSize = Float.parseFloat(getDataAdapter1.getRatings());
////        Float d= (Float) (stepSize*100 /100 * 5);
//        Viewholder.Rating.setRating(stepSize);
        String abc = getDataAdapter1.getImage();
        //   final  String url = "http://ezcommunication.somee.com/" + abc.substring(2);
        final  String url = "http://ezcommunication.somee.com/" + abc;
        Glide.with(context).load(url).into(Viewholder.image);


        Viewholder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =context. getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = getDataAdapter1.getId();
                final String frommm = getDataAdapter1.getFromnm();
                final String content = getDataAdapter1.getContent();
                final String title = getDataAdapter1.getTitle();
                final String timeval = getDataAdapter1.getTimeval();
                final String stdemail = getDataAdapter1.getStdemail();
                final String image = getDataAdapter1.getImage();


                final String email = (preferences.getString("username", ""));
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://ezcommunication.somee.com/webservice.asmx/Favourite_News",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();

                                Toast.makeText(context, "Added to My Favourite..", Toast.LENGTH_LONG).show();



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("fromnm", frommm);
                        map.put("content", content);
                        map.put("image", image);
                        map.put("title", title);
                        map.put("timeval", timeval);
                        map.put("stdemail", email);

                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);


            }
        });



// fav news add starts
//
//      Viewholder.Image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//
//                Intent i = new Intent(context.getApplicationContext(),Buypage.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("book_id",getDataAdapter1.getId());
//                i.putExtra("book_title",getDataAdapter1.getTitle());
//                i.putExtra("material_type",getDataAdapter1.getMaterialtype());
//                i.putExtra("Book_author",getDataAdapter1.getName());
//                i.putExtra("Price",getDataAdapter1.getPrice());
//                i.putExtra("Description",getDataAdapter1.getDescrip());
//                i.putExtra("image",url);
//
//                context.startActivity(i);
//
//
//
//
//
//////                Toast.makeText(context.getApplicationContext(),
//////                        "cat_id:"+getDataAdapter1.getId(), Toast.LENGTH_LONG).show();
////                Intent i = new Intent(context,Menu_four_sub_catagory.class);
////                i.putExtra("catagory_id",getDataAdapter1.getId());
////                i.putExtra("catagory_title",getDataAdapter1.getTitle());
////                context.startActivity(i);
//            }
//        });


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,content,fromnm,timeval;
        public  Button favourite;
        public ImageView image;


        public ViewHolder(View itemView) {

            super(itemView);

            favourite = (Button) itemView.findViewById(R.id.btnfavourite);

            title = (TextView) itemView.findViewById(R.id.newstitle);

            content = (TextView) itemView.findViewById(R.id.newsdetail);

            image = (ImageView) itemView.findViewById(R.id.imageView);

            fromnm = (TextView) itemView.findViewById(R.id.postedby);

            timeval = (TextView) itemView.findViewById(R.id.timeval);
        }
    }
}
