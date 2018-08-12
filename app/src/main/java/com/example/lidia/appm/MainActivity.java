package com.example.lidia.appm;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ImageView imageview, image;
    long animationDuration=1000;
    Integer[] LEVELS;
    String [] CLASSES;
    String msg;
    int count=2;
    int initial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        msg = extras.getString("keyMessage");
        count= extras.getInt("counter");
        if(msg.equals("bzero")){
            setContentView(R.layout.activity_zero);
        } else if(msg.equals("bone")){
        setContentView(R.layout.activity_main);
        }else if(msg.equals("btwo")){
        setContentView(R.layout.activity_two);
        }else if(msg.equals("bthree")) {
            setContentView(R.layout.activity_three);
        }else if(msg.equals("bfour")) {
            setContentView(R.layout.activity_four);
        }else if(msg.equals("bfive")) {
            setContentView(R.layout.activity_five);
        }
        imageview=(ImageView)findViewById(R.id.plevel1);

        image=(ImageView)findViewById(R.id.plevel);
        LEVELS= new Integer[]{R.id.ladrillo,R.id.ladrillo0, R.id.ladrillo1, R.id.ladrillo2, R.id.ladrillo3, R.id.ladrillo4, R.id.ladrillo5};
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("fff", "fff"+initial +"  "+count);
                initial= count-1;
                Log.i("fff", "after"+initial +"  "+count);

                ImageButton iv1 = (ImageButton) findViewById(LEVELS[initial]);
        imageview.setY(iv1.getTop());
        imageview.setX(iv1.getLeft()-150);

        Log.i("ddd","kkkk"+iv1.getTop());
        Log.i("ddd","dddd"+iv1.getLeft());
        }
        }, 10);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ImageButton iv = (ImageButton) findViewById( LEVELS[count]);
                handleAnimation(imageview, iv.getLeft() - 150, iv.getTop());
                Log.i("ddd","kkkk"+iv.getTop());
                Log.i("ddd","dddd"+iv.getLeft());

            }
        }, 1000);

            final Intent intent = new Intent(this, first.class);
            intent.putExtra("keyMessage", msg);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            }, 2000);

    }

    public void handleAnimation(View view, float xposition, float iposition){
        ObjectAnimator animatorX =ObjectAnimator.ofFloat(view, "x",xposition);
        ObjectAnimator animatorY =ObjectAnimator.ofFloat(view, "y",iposition);
        animatorX.setDuration(animationDuration);

        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();
    }

    public void rotationAnimation(View view,  float xposition, float iposition){
        ObjectAnimator animatorX =ObjectAnimator.ofFloat(imageview, "x",xposition);
        ObjectAnimator animatorY =ObjectAnimator.ofFloat(imageview, "y",iposition);
        animatorY.setDuration(animationDuration);

        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imageview, "rotation", 0f, -360f);
        rotateAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(animatorY,animatorX, rotateAnimation); //rotateAnimation
        animatorSet.start();
    }

            }












