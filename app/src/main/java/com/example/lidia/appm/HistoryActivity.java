package com.example.lidia.appm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class HistoryActivity extends Activity {
    int[] LAYOUT;
    ImageView iv;
    int time=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


    LAYOUT = new int[]{R.drawable.sone,R.drawable.stwo, R.drawable.sthree};
        iv = (ImageView) findViewById(R.id.scene);
      for( int  i=0; i<3; i++){


          final int finalI = i;
          new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setBackgroundResource(LAYOUT[finalI]);
            }
        }, time);
          time+=3000;
        }


  new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                onBackPressed();
            }
        }, time + 3000);


}
}