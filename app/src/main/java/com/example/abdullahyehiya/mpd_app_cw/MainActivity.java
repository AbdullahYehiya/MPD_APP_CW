package com.example.abdullahyehiya.mpd_app_cw;

/**
 * S1512605
 * Abdullah Yehiya
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Button current_btn;
    private Button plannedrw_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current_btn = (Button) findViewById(R.id.current_btn);
        current_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCurrentIncidentsActivity();
            }
        });
        plannedrw_btn = (Button) findViewById(R.id.plannedrw_btn);
        plannedrw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlannedRoadWorksActivity();
            }
        });
    }
    //when the current incidents button is pressed, it will open another activity
    public void openCurrentIncidentsActivity() {
        Intent intent = new Intent(this, CurrentIncidentsActivity.class);
        startActivity(intent);
    }

    //when the Planned RoadWorks button is pressed, it will open another activity
    public void openPlannedRoadWorksActivity() {
        Intent intent = new Intent(this, PlannedRoadWorksActivity.class);
        startActivity(intent);
    }


}
