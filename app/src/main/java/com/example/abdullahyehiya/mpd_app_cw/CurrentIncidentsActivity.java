package com.example.abdullahyehiya.mpd_app_cw;

/**
 * S1512605
 * Abdullah Yehiya
 */

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class CurrentIncidentsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshContainer;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_incidents);
        refreshContainer=(SwipeRefreshLayout)findViewById(R.id.refreshContainer);
        refreshContainer.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        RssReader readRss=new RssReader(this,recyclerView);
        readRss.execute();
    }

    public void onRefresh() {

        RssReader readRss=new RssReader(this,recyclerView);
        readRss.execute();
        refreshContainer.setRefreshing(false);


    }
}
