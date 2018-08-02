package com.example.lidia.appm;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageView imageview;
    long animationDuration=1000;
    Integer[] LEVELS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview=(ImageView)findViewById(R.id.plevel1);
        LEVELS = new Integer[]{R.id.ladrillo0, R.id.ladrillo1,R.id.ladrillo2,R.id.ladrillo3, R.id.ladrillo4};
        for(int i=0; i<=4;i++){
            ImageButton button = (ImageButton) findViewById(LEVELS[i]);
            button.setOnClickListener(this);
        }
    }
    public void handleAnimation(View view, float xposition, float iposition){
        ObjectAnimator animatorX =ObjectAnimator.ofFloat(imageview, "x",xposition); //225f
        ObjectAnimator animatorY =ObjectAnimator.ofFloat(imageview, "y",iposition); //1300f
        animatorX.setDuration(animationDuration);
/*
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imageview, "rotation", 0f, 360f);
        rotateAnimation.setDuration(animationDuration);
*/


        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY); //rotateAnimation
        animatorSet.start();
    }

    @Override
    public void onClick(View view) {
        for(int i=0; i<=4;i++) {

                if (view.getId() == LEVELS[i]) {
                    if (i < 3) {
                    handleAnimation(view, 250f + i * 192f, 1300f - i * 185f);

            } else if(i==3){
                handleAnimation(view, 250f + i * 185f, 1300f - i * 175f);
            }else{
                 handleAnimation(view, 250f + i * 175f, 1300f - i * 170);

                    }
                }
        }}
}
