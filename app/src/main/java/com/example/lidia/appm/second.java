package com.example.lidia.appm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class second extends Activity implements View.OnClickListener{
    private DBDataSource dbDataSource;
    RadioButton radio1, radio2,radio3,radio4;
    RadioGroup radioGroup;
    List<QUESTIONS> question;
    int position = 100;
    ImageView imageView1;
    Random rnd;
    TextView bottom;
    int complete= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        dbDataSource = new DBDataSource(getApplicationContext());




    }
    public void listener(){
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        bottom = (TextView) findViewById(R.id.bottom);
        Button check = (Button) findViewById(R.id.button);
        check.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        dbDataSource.open();
        rnd = new Random();
        hola();
    }
    public  void hola(){
            question = dbDataSource.getAll();
            int i =rnd.nextInt(question.size());
            int answer = rnd.nextInt(2);

            radio1= (RadioButton) findViewById(R.id.radioButton1);
            radio2= (RadioButton) findViewById(R.id.radioButton2);
            radio3= (RadioButton) findViewById(R.id.radioButton3);
            radio4= (RadioButton) findViewById(R.id.radioButton4);
            radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        TextView top=(TextView)findViewById(R.id.top);
        top.setText(question.get(i).getQuestion());
        position=rnd.nextInt(4);
        write(position, i, answer);

    }

    public void write(int position, int i, int answer){
        if(position==0){
            if(answer==0) {
                radio1.setText(question.get(i).getAnswer1());
            }else{
                radio1.setText(question.get(i).getSolution());
            }
            radio2.setText(question.get(i).getMistake1());
            radio3.setText(question.get(i).getMistake2());
            radio4.setText(question.get(i).getWrong_Solution());

        }else if(position==1){
            radio1.setText(question.get(i).getMistake2());
            if(answer==0) {
                radio2.setText(question.get(i).getAnswer1());
            }else{
                radio2.setText(question.get(i).getSolution());
            }
            radio3.setText(question.get(i).getWrong_Solution());
            radio4.setText(question.get(i).getMistake1());
        }else if(position==2){
            radio1.setText(question.get(i).getMistake1());
            radio2.setText(question.get(i).getWrong_Solution());
            if(answer==0) {
                radio3.setText(question.get(i).getAnswer1());
            }else{
                radio3.setText(question.get(i).getSolution());
            }
            radio4.setText(question.get(i).getMistake2());
        }else if(position==3){
            radio1.setText(question.get(i).getWrong_Solution());
            radio2.setText(question.get(i).getMistake2());
            radio3.setText(question.get(i).getMistake1());
            if(answer==0) {
                radio4.setText(question.get(i).getAnswer1());
            }else{
                radio4.setText(question.get(i).getSolution());
            }
        }
listener();
    }



    @Override
    public void onClick(View view) {
                imageView1.setVisibility(View.VISIBLE);
                if (radio1.isChecked() && position==0 ) {
                    imageView1.setBackgroundResource(R.drawable.check);
                    complete++;
                }else if (radio2.isChecked() && position==1) {
                    imageView1.setBackgroundResource(R.drawable.check);
                    complete++;
                } else if (radio3.isChecked() && position==2) {
                    imageView1.setBackgroundResource(R.drawable.check);
                    complete++;
                } else if (radio4.isChecked() && position==3) {
                    imageView1.setBackgroundResource(R.drawable.check);
                    complete++;
                }else {
                    imageView1.setBackgroundResource(R.drawable.cross);
                    complete=0;
             }
        bottom.setText(complete+" /5");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView1.setVisibility(View.GONE);
                radioGroup.clearCheck();
                if (complete == 5) {
//saltar a otra fase
                    complete = 0;
                    bottom.setText(complete + " /5");
                } else {

                    hola();
                }

            } },500);
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbDataSource.close();
    }
}
