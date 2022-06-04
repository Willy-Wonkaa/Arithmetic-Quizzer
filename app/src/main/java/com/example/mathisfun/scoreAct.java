package com.example.mathisfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class scoreAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent i=getIntent();
        String s=i.getStringExtra("COOL");
        ((TextView)findViewById(R.id.TextF)).setText("Your Final Score: "+s);
    }
    public void back(View v){
        Intent j=new Intent(this,MainActivity.class);
        startActivity(j);
    }
}