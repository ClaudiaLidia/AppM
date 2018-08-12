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

public class MapActivity extends Activity implements View.OnClickListener {
    ImageView imageview;
    long animationDuration=1500;
    Integer[] BUTTONS;
    ImageButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        imageview=(ImageView)findViewById(R.id.plevel1);
        BUTTONS = new Integer[]{ R.id.bone, R.id.bthree, R.id.bzero, R.id.bfour, R.id.btwo, R.id.bfive};

        for(int i=0; i<6;i++){
            button = (ImageButton) findViewById(BUTTONS[i]);
            button.setOnClickListener(this);
        }
    }

    public void rotationAnimation(View view,  float xposition, float iposition){
        ObjectAnimator animatorX =ObjectAnimator.ofFloat(imageview, "x",xposition);
        ObjectAnimator animatorY =ObjectAnimator.ofFloat(imageview, "y",iposition);
        animatorY.setDuration(animationDuration);
        animatorX.setDuration(1000);

        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imageview, "rotation", 0f, -90f);
        rotateAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(animatorY,animatorX, rotateAnimation); //rotateAnimation
        animatorSet.start();
    }


    @Override
    public void onClick(View view) {
            for(int i=0; i<6;i++) {
                if (view.getId() == BUTTONS[i]) {

                    ImageButton iv=(ImageButton) findViewById(BUTTONS[i]);
                    rotationAnimation(view, iv.getLeft(), iv.getTop());
                    }


            }
                if(view.getId()==R.id.bzero||view.getId()== R.id.bone||view.getId()== R.id.btwo||view.getId()==R.id.bthree||view.getId()==R.id.bfour||view.getId()==R.id.bfive) {
                    final Intent intent = new Intent(this, MainActivity.class);
                    String msg = getResources().getResourceEntryName(view.getId());
                    intent.putExtra("keyMessage", msg);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    }, 1500);

                }
    }
}
