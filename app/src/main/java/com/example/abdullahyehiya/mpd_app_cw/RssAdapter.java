package com.example.abdullahyehiya.mpd_app_cw;

/**
 * Created by abdullahyehiya on 3/03/2018.
 * S1512605
 * Abdullah Yehiya
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class RssAdapter extends RecyclerView.Adapter<RssAdapter.MyViewHolder> {

    ArrayList<RssItems> rssItems;
    Context mContext;

    public RssAdapter(Context context,ArrayList<RssItems>rssItems){
        this.rssItems=rssItems;
        this.mContext=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.activity_current_incidents_rss_items,parent,false);
        View view1= LayoutInflater.from(mContext).inflate(R.layout.activity_planned_roadwork_rss_items,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final RssItems current=rssItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Date.setText(current.getPubDate());
        holder.Description.setText(current.getDescription());
        holder.Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(context,current.getCordinates(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,MapsActivity.class);
                intent.putExtra("coordinates",current.getCoordinates());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rssItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView Title,Description,Date;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.textTitle);
            Date= (TextView) itemView.findViewById(R.id.textDate);
            Description= (TextView) itemView.findViewById(R.id.textDesc);
        }
    }
}
