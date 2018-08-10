package com.example.lidia.appm;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class first extends Activity implements View.OnClickListener{
    private DBDataSource dbDataSource;
    Button right, left;
    int position=100;
    TextView bottom;
    int complete= 0;
    List<QUESTIONS> question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbDataSource = new DBDataSource(getApplicationContext());



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
            int mistake = rnd.nextInt(2);


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
            }
        }
        listener();
    }

    @Override
    public void onClick(View view) {

        if(position==0 && view.getId()==R.id.right){
           complete++;
            right.setBackgroundColor(Color.GREEN);
        }else if(position==1 && view.getId()==R.id.left){
            complete++;
            left.setBackgroundColor(Color.GREEN);
        }else if(position==1 && view.getId()==R.id.right){
            complete = 0;
            right.setBackgroundColor(Color.RED);
        }else if(position==0 && view.getId()==R.id.left){
            complete=0;
            left.setBackgroundColor(Color.RED);
        }

        bottom.setText(complete+" /5");

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               left.setBackgroundColor(Color.parseColor("#F0F0F0"));
               right.setBackgroundColor(Color.parseColor("#F0F0F0"));
               if(complete == 5) {

//saltar a otra fase
                   complete =0;
                   bottom.setText(complete+" /5");
               } else {

                   hola();
               }
           }
       },500);




    }

    @Override
    protected void onStop() {
        super.onStop();
        dbDataSource.close();
    }
}
