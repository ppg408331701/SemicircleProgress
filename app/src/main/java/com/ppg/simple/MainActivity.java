package com.ppg.simple;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import simple.ppg.com.ppgsemicircle.Views.CircularProgressar;
import simple.ppg.com.ppgsemicircle.Views.SemicircleProgressView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
    private SemicircleProgressView semicircleProgressView;
    private CircularProgressar sesame_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        semicircleProgressView = (SemicircleProgressView) findViewById(R.id.semicircleProgressView);
        semicircleProgressView.setSesameValues(10, 50);


        sesame_view = (CircularProgressar) findViewById(R.id.sesame_view);
        sesame_view.setSesameValues(50, 100);
        sesame_view.setDuration(5000);
        sesame_view.setStyle(Paint.Style.FILL);
        sesame_view.setColor(ContextCompat.getColor(this, R.color.colorAccent));
        sesame_view.setInterpolator(new LinearOutSlowInInterpolator());
        sesame_view.start();



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
