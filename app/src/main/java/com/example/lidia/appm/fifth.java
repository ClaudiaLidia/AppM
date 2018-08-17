package com.example.lidia.appm;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class fifth extends Activity implements View.OnClickListener {
    private static final long START=50000;

    private DBDataSource dbDataSource;

    private CountDownTimer mCount;
    private boolean mTimer;
    private long mTimeL = START;

    List<QUESTIONS> question;
    String r="";
    EditText editText;
    TextView bottom, text, mText;
    Random rnd;
    int complete= 0;
    Button mStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        dbDataSource = new DBDataSource(getApplicationContext());
        mText = findViewById(R.id.Cronometro);
        mStart = findViewById(R.id.button2);

        mStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startTimer();
            }
        });
        updateCount();
    }

    private void startTimer(){
      mCount = new CountDownTimer(mTimeL, 1000) {
          @Override
          public void onTick(long l) {
              mTimeL= l;
              updateCount();
              mStart.setVisibility(View.INVISIBLE);
          }

          @Override
          public void onFinish() {
              mTimer = false;
              mStart.setText("Start");
              mStart.setVisibility(View.VISIBLE);
if(complete>=8){
    //llamada
    Log.i("mas de dies", "");
}else{
    Log.i(" no mas de dies", "");
     reset();
}

          }
      }.start();
      mTimer=true;
      mStart.setText("No superaste 8, Click Start");


    }
            private void reset(){
                mTimeL = START;
                updateCount();
            }


            private void updateCount(){
                int minutes = (int) (mTimeL / 1000) / 60;
                int seconds =(int) (mTimeL/1000)%60;

                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                mText.setText(timeLeftFormatted);
            }

    public void listener(){
        editText=(EditText)findViewById(R.id.answer);
        text=(TextView)findViewById(R.id.text);
        bottom = (TextView) findViewById(R.id.bottom);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        dbDataSource.open();
        rnd = new Random();
        hola();

    }
    public  void hola() {
        TextView top=(TextView)findViewById(R.id.top);
        question = dbDataSource.getAll();
        int i =rnd.nextInt(question.size());
        top.setText(question.get(i).getQuestion());
listener();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.button){
            String a= String.valueOf(editText.getText());
            String t= String.valueOf(text.getText());
            String total = (t+ a).replaceAll("\\s","");


            for(int i=0; i<question.size(); i++) {
                if (total.equals(question.get(i).getSolution()) ||  total.equals(question.get(i).getSolution1())  ) {
                    complete++;
                }
            }


            bottom.setText("Acertadas -> "+complete);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    editText.setText(" ");


                        hola();

                } },500);
        }
    }
}