package com.example.lidia.appm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Button button = (Button) findViewById(R.id.bhistory);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.bgame);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.bhistory){
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        }else if(view.getId()==R.id.bgame){
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
    }
}
