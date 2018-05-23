package com.example.yuvraj.navigationdrawer;
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

/**
 * Created by JUNED on 6/16/2016.
 */
public class News_fav_adapter extends RecyclerView.Adapter<News_fav_adapter.ViewHolder> {
    public static final String MyPREFERENCES = "News" ;

    Context context;

    List<Actors> getDataAdapter;



    public News_fav_adapter(List<Actors> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news_fav, parent, false);

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
       // Viewholder.stdemail.setText(getDataAdapter1.getStdemail());


        String abc = getDataAdapter1.getImage();
        //   final  String url = "http://ezcommunication.somee.com/" + abc.substring(2);
        final  String url = "http://ezcommunication.somee.com/" + abc;
        Glide.with(context).load(url).into(Viewholder.image);








//        Viewholder.Image.setOnClickListener(new View.OnClickListener() {
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






        Viewholder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id = getDataAdapter1.getId();



                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://ezcommunication.somee.com/webservice.asmx/Favourite_News_Delete",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();

                                Toast.makeText(context, "Successfully Removed..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(context,News_fav.class);
                                context.startActivity(i);

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
                        map.put("id", id);
                       /* map.put("content", content);
                        map.put("image", image);
                        map.put("title", title);
                        map.put("timeval", timeval);
                        map.put("stdemail", email);*/

                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);


            }
        });









    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,content,fromnm,timeval,stdemail;
        public ImageView image;
        public Button favourite;


        public ViewHolder(View itemView) {

            super(itemView);

            favourite = (Button) itemView.findViewById(R.id.btndlt);

            title = (TextView) itemView.findViewById(R.id.newstitle);

            content = (TextView) itemView.findViewById(R.id.newsdetail);

            image = (ImageView) itemView.findViewById(R.id.imageView);

            fromnm = (TextView) itemView.findViewById(R.id.postedby);

            timeval = (TextView) itemView.findViewById(R.id.timeval);
        }
    }
}
