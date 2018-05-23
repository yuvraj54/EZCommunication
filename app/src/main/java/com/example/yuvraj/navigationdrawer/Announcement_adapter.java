package com.example.yuvraj.navigationdrawer;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by yuvraj on 1/29/2018.
 */
public class Announcement_adapter extends RecyclerView.Adapter<Announcement_adapter.ViewHolder> {
    public static final String MyPREFERENCES = "Announcement" ;

    Context context;

    List<Actors> getDataAdapter;



    public Announcement_adapter(List<Actors> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_announcement, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        final Actors getDataAdapter1 =  getDataAdapter.get(position);



        Viewholder.description.setText(getDataAdapter1.getDescription());
        Viewholder.fromnm.setText(getDataAdapter1.getFromnm());
        Viewholder.timeval.setText(getDataAdapter1.getTimeval());



    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView description,fromnm,timeval;



        public ViewHolder(View itemView) {

            super(itemView);



            description = (TextView) itemView.findViewById(R.id.announcement_desc);

            fromnm = (TextView) itemView.findViewById(R.id.Announcementby);

            timeval = (TextView) itemView.findViewById(R.id.announcementtime);
        }
    }
}
