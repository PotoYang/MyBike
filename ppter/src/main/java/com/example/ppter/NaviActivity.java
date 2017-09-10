package com.example.ppter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NaviActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setStatusBarColor(Color.rgb(235, 101, 30));
        getSupportActionBar().hide();

        NaviView myView = new NaviView(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(myView);


    }
}
