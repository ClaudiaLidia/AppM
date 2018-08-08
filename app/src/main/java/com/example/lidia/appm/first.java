package com.example.lidia.appm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class first extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        TextView fnumber =(TextView)findViewById(R.id.textView);
        int fnum = Integer.valueOf(fnumber.getText().toString());
        TextView snumber =(TextView)findViewById(R.id.textView2);
        int snum = Integer.valueOf(snumber.getText().toString());
        int result = 0;
        result= fnum + snum;
        Log.i("numero", "sum " + result);
        Log.i("numero", "fi " + fnum);
        Log.i("numero", "se " + snum);
        TextView rnumber =(TextView)findViewById(R.id.textView3);
        rnumber.setText(" " + result);


    }
}
