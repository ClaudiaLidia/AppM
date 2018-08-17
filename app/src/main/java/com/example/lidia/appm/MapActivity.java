package com.example.lidia.appm;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.Set;

public class MapActivity extends Activity implements View.OnClickListener {
    ImageView imageview;
    long animationDuration=1500;
    Integer[] BUTTONS;
    ImageButton button;
    int count=2;
    private SoundPool soundPool;
    private Set<Integer> soundLoaded;
    int []bells={0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        soundLoaded = new HashSet<Integer>();
        imageview=(ImageView)findViewById(R.id.plevel1);
        BUTTONS = new Integer[]{ R.id.bone, R.id.bthree, R.id.bzero, R.id.bfour, R.id.btwo, R.id.bfive};

        for(int i=0; i<6;i++){
            button = (ImageButton) findViewById(BUTTONS[i]);
            button.setOnClickListener(this);
        }

    }


    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();

        AudioAttributes.Builder attrBuilder= new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder= new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(2);
        soundPool = spBuilder.build();



        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if(status==0){
                    soundLoaded.add(sampleId);
                    Log.i("SOUND", "Sound loaded"+ sampleId);
                }else {
                    Log.i("SOUND", "Error cannot load sound status"+ status);

                }
            }
        });
        int bell= soundPool.load(this, R.raw.nave,1);
        bells=new int[]{bell};

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
                    playSound(bells[0]);
                    }

            }

        if(view.getId()==R.id.bzero||view.getId()== R.id.bone||view.getId()== R.id.btwo||view.getId()==R.id.bthree||view.getId()==R.id.bfour||view.getId()==R.id.bfive) {
                    final Intent intent = new Intent(this, MainActivity.class);
                    String msg = getResources().getResourceEntryName(view.getId());
                    intent.putExtra("keyMessage", msg);
                    intent.putExtra("counter", count);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    }, 1500);


                }
    }
    private void playSound(int soundId){
        if(soundLoaded.contains(soundId)){
            soundPool.play(soundId, 1.0f, 1.0f, 0,0,1.0f);
        }
    }
}
