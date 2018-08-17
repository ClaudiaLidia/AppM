package com.example.lidia.appm;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioTrack;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends Activity {
    ImageView imageview, image;
    long animationDuration=1000;
    Integer[] LEVELS;
    String msg;
    int count=2;
    int initial, c;
    private SoundPool soundPool;
    private Set<Integer> soundLoaded;
    int []bells={0};




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
        soundLoaded = new HashSet<Integer>();

        imageview=(ImageView)findViewById(R.id.plevel1);

        image=(ImageView)findViewById(R.id.plevel);
        LEVELS= new Integer[]{R.id.ladrillo,R.id.ladrillo0, R.id.ladrillo1, R.id.ladrillo2, R.id.ladrillo3, R.id.ladrillo4, R.id.ladrillo5};
        Class[] classes= new Class[]{first.class, second.class, first.class, second.class};
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initial= count-1;
                ImageButton iv1 = (ImageButton) findViewById(LEVELS[initial]);
                if(msg.equals("bfive")){
                    imageview.setY(iv1.getTop()+30);
                    imageview.setX(iv1.getLeft()+20);
                    image.setY(iv1.getTop()+30);
                    image.setX(iv1.getLeft()+20);

                }else if (msg.equals("bfour")) {
                imageview.setY(iv1.getTop());
                 imageview.setX(iv1.getLeft()+20);

        }else{
                    Log.i("ddd","dddddddddddddd");
                    imageview.setY(iv1.getTop());
                    imageview.setX(iv1.getLeft()-150);
                }
            }
        }, 10);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              final  ImageButton iv = (ImageButton) findViewById(LEVELS[count]);
                if (msg.equals("bfive")) {
                    handleAnimation(imageview, iv.getLeft() + 20, iv.getTop() + 30);
                    handleAnimation(image, iv.getLeft() + 20, iv.getTop() + 30);
                } else if (msg.equals("bfour")) {
                    handleAnimation(imageview, iv.getLeft() + 20, iv.getTop());
                } else {
                    Log.i("ddd","dddddddddddddd");
                    handleAnimation(imageview, iv.getLeft() - 150, iv.getTop());

                }
                playSound(bells[0]);
            }
        }, 1000);


c=count-2;

            final Intent intent = new Intent(this, classes[c]);
            intent.putExtra("keyMessage", msg);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            }, 2000);

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
        int bell= soundPool.load(this, R.raw.jump,1);
bells=new int[]{bell};

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
    private void playSound(int soundId){
        if(soundLoaded.contains(soundId)){
            soundPool.play(soundId, 1.0f, 1.0f, 0,0,1.0f);
        }
    }

            }












