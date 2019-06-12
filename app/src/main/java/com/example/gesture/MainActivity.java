package com.example.gesture;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView min,hrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hrs = (TextView) findViewById(R.id.hrs);
        min = (TextView)findViewById(R.id.min);

        LinearLayout hrs_ =(LinearLayout) findViewById(R.id.img);
        LinearLayout min_ =(LinearLayout) findViewById(R.id.img2);
        
        final GestureDetector gdt = new GestureDetector(new GestureListener());
        final GestureDetector gdt2 = new GestureDetector(new GestureListener2());


        hrs_.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            }
        });

        min_.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt2.onTouchEvent(event);
                return true;
            }
        });


    }
    private static final int SWIPE_MIN_DISTANCE = 80;
    private static final int SWIPE_THRESHOLD_VELOCITY = 1;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Right to left
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Left to right
            }

            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.e("LOG","UP");

                addhrs(Math.abs(Math.round(e1.getY() - e2.getY())));
               //hrs.setText( String.valueOf(e1.getY() - e2.getY()) );
                return false; // Bottom to top
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.e("LOG","DOWN");

                minushrs(Math.abs(Math.round(e2.getY() - e1.getY())));
              //  hrs.setText(String.valueOf(e2.getY() - e1.getY()));
                return false; // Top to bottom
            }
            return false;
        }
    }

    private class GestureListener2 extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Right to left
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Left to right
            }

            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.e("LOG","UP");

                addmin(Math.abs(Math.round(e1.getY() - e2.getY())));
                //hrs.setText( String.valueOf(e1.getY() - e2.getY()) );
                return false; // Bottom to top
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.e("LOG","DOWN");

                minmin(Math.abs(Math.round(e2.getY() - e1.getY())));
                //  hrs.setText(String.valueOf(e2.getY() - e1.getY()));
                return false; // Top to bottom
            }
            return false;
        }
    }







    void addhrs(int ed){
        int org= Integer.valueOf(String.valueOf(hrs.getText()));
        int newvalue;
        newvalue = (ed/80);
        org = org + newvalue;

        if(org<=12){
        hrs.setText(String.valueOf(Math.round(org)));
        }
        else{
            hrs.setText(String.valueOf(Math.round(12)));
        }
    }



    void addmin(int ed){
        int org= Integer.valueOf(String.valueOf(min.getText()));
        int newvalue;
        newvalue = Math.abs(ed/80);
        org = org + newvalue;

        if(org<=59){
            min.setText(String.valueOf(Math.round(org)));
        }
        else{
            min.setText(String.valueOf(Math.round(59)));
        }

    }

    void minmin(int ed){
        int org= Integer.valueOf(String.valueOf(min.getText()));
        int newvalue;
        newvalue = (ed/80);
        org = org - newvalue;

        if(org>=0){
            min.setText(String.valueOf(Math.abs(Math.round(org))));
        }
        else{
            min.setText(String.valueOf(Math.round(0)));
        }

    }

    void minushrs(int ed){
        int org= Integer.valueOf(String.valueOf(hrs.getText()));
        int newvalue;
        newvalue = (ed/80);
        org = org - newvalue;

        if(org>=0){
            hrs.setText(String.valueOf(Math.abs(Math.round(org))));
        }
        else{
            hrs.setText(String.valueOf(Math.round(0)));
        }
    }

}

