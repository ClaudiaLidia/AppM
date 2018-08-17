package com.example.lidia.appm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class first extends Activity implements View.OnClickListener{
    private DBDataSource dbDataSource;
    Button right, left;
    int position=100;
    TextView bottom;
    int complete= 0;
    String msg;
    private SoundPool soundPool;
    private Set<Integer> soundLoaded;
    int []bells={0,0};

    List<QUESTIONS> question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        soundLoaded = new HashSet<Integer>();
        dbDataSource = new DBDataSource(getApplicationContext());

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
        int bell1= soundPool.load(this, R.raw.wrong,1);
        int bell= soundPool.load(this, R.raw.right,1);

        bells=new int[]{bell,bell1};

    }
    public void listener(){
        right=(Button) findViewById(R.id.right);
        left=(Button) findViewById(R.id.left);
        right.setOnClickListener(this);
        left.setOnClickListener(this);
        bottom = (TextView) findViewById(R.id.bottom);
    }
    @Override
    protected void onStart() {
        super.onStart();
          dbDataSource.open();
          hola();


    }
    public  void hola(){
        Random rnd = new Random();
        TextView top=(TextView)findViewById(R.id.top);
        right=(Button) findViewById(R.id.right);
        left=(Button) findViewById(R.id.left);


        question = dbDataSource.getAll();

        int i =rnd.nextInt(question.size());

            top.setText(question.get(i).getQuestion());

            position = rnd.nextInt(2);
            int answer = rnd.nextInt(2);
            int mistake = rnd.nextInt(3);


            write(position, answer, mistake, i);


    }


    public void write(int position, int answer, int mistake, int i){
        if(position == 0) {
            if(answer == 0) {
                right.setText(question.get(i).getAnswer1());
            }else if(answer==1) {
                right.setText(question.get(i).getSolution());
            }

            if(mistake==0){
                left.setText(question.get(i).getMistake1());
            }else if(mistake==1){
                left.setText(question.get(i).getMistake2());
            }else if(mistake==2){
                left.setText(question.get(i).getWrong_Solution());
            }
        }else if (position == 1) {
            if(answer == 0) {
                left.setText(question.get(i).getAnswer1());
            }else if(answer == 1){
                left.setText(question.get(i).getSolution());
            }

            if(mistake==0){
                right.setText(question.get(i).getMistake1());
            }else if(mistake==1){
                right.setText(question.get(i).getMistake2());
            }else if(mistake==2){
                right.setText(question.get(i).getWrong_Solution());
            }
        }
        listener();
    }


    @Override
    public void onClick(View view) {

        if (position == 0 && view.getId() == R.id.right) {
            complete++;
            right.setBackgroundColor(Color.GREEN);
            playSound(bells[0]);
        } else if (position == 1 && view.getId() == R.id.left) {
            complete++;
            left.setBackgroundColor(Color.GREEN);
            playSound(bells[0]);
        } else if (position == 1 && view.getId() == R.id.right) {
            complete = 0;
            right.setBackgroundColor(Color.RED);
            playSound(bells[1]);
        } else if (position == 0 && view.getId() == R.id.left) {
            complete = 0;
            left.setBackgroundColor(Color.RED);
            playSound(bells[1]);

        }

        bottom.setText(complete + " /5");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                left.setBackgroundColor(Color.parseColor("#F0F0F0"));
                right.setBackgroundColor(Color.parseColor("#F0F0F0"));
            }
        }, 500);

        //saltar a otra fase
        final Intent intent = new Intent(this, MainActivity.class);
        Bundle extras = getIntent().getExtras();
        msg = extras.getString("keyMessage");
        intent.putExtra("keyMessage", msg);
        int count=3;
        intent.putExtra("counter", count);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (complete == 5){
                    // if you are redirecting from a fragment then use getActivity() as the context.
                    startActivity(intent);

                }else{
                    hola();
                }
            }
        }, 1500);

            }


    @Override
    protected void onStop() {
        super.onStop();
        dbDataSource.close();
    }
    private void playSound(int soundId){
        if(soundLoaded.contains(soundId)){
            soundPool.play(soundId, 1.0f, 1.0f, 0,0,1.0f);
        }
    }
}
