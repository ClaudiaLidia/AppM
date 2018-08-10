package com.example.lidia.appm;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageView imageview, image;
    long animationDuration=1000;
    Integer[] LEVELS;
    String msg;
    ImageButton button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        msg = extras.getString("keyMessage");

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
        LEVELS = new Integer[]{R.id.ladrillo, R.id.ladrillo0, R.id.ladrillo1,R.id.ladrillo2,R.id.ladrillo3, R.id.ladrillo4, R.id.ladrillo5};
        for(int i=0; i<6;i++){
            button = (ImageButton) findViewById(LEVELS[i]);
            button.setOnClickListener(this);
        }
    }

    public void handleAnimation(View view, float xposition, float iposition){
        ObjectAnimator animatorX =ObjectAnimator.ofFloat(view, "x",xposition);
        ObjectAnimator animatorY =ObjectAnimator.ofFloat(view, "y",iposition);
        animatorX.setDuration(animationDuration);

        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY); //rotateAnimation
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

    @Override
    public void onClick(View view) {

            //for(int i=0; i<6;i++) {

            if (view.getId() == LEVELS[1]) {
              /*  if( msg.equals("bfive")){
                    ImageButton iv=(ImageButton) findViewById(LEVELS[i+1]);
                    handleAnimation(imageview,  iv.getLeft()+20, iv.getTop()+30);
                    handleAnimation(image,  iv.getLeft()+20, iv.getTop()+30);
            } else if (msg.equals("bfour")){
                    ImageButton iv=(ImageButton) findViewById(LEVELS[i+1]);
                    handleAnimation(imageview,  iv.getLeft()+20, iv.getTop());
                } else{
                    ImageButton iv = (ImageButton) findViewById(LEVELS[i + 1]);
                    handleAnimation(imageview, iv.getLeft() - 150, iv.getTop());
                    }*/

                Intent intent = new Intent(this, first.class);
                startActivity(intent);

       // }

            }
            }
    }

