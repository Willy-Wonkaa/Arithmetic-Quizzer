package com.example.mathisfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> list_rando = new ArrayList<>();
    char operations[]={'+','-','x','/'};
    String temp="";
    GridLayout grid_1,grid_2,grid_3,grid_4,grid_5;
    int score,lives;
    ArrayList<Integer> disable=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score=0;
        lives=3;
        create_puzzle();
    }
    public void create_puzzle() {
        if(lives!=0){
            grid_1 = (GridLayout) findViewById(R.id.grid6);
            grid_2 = (GridLayout) findViewById(R.id.grid2);
            grid_3=(GridLayout)findViewById((R.id.grid5));
            grid_4=(GridLayout)findViewById((R.id.grid1));
            grid_5=(GridLayout)findViewById((R.id.grid3));
            list_create();
            operations();
            assign();
        }
    }

    public void highlight(View v) {
        v.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        temp=(((Button)v).getText().toString());
        ((Button)v).setEnabled(false);
        for(int i=0;i<10;i++){
            ((Button)grid_1.getChildAt(i)).setClickable(false);
        }

    }

    public int random(int max) {
        int randomNum = (int) (Math.random() * max);
        return randomNum;
    }

    public void list_create() {
        int flag = 0;
        while (flag < 10) {
            int c = random(100);
            if ((c != 0) && (!list_rando.contains(c))) {
                list_rando.add(c);
                flag++;
            }
        }
        String s = String.valueOf(list_rando);
        Log.d("Cool", s);
        //Collections.shuffle(list_rando);
        for (int j = 0; j < grid_1.getChildCount(); j++) {
            Button b = (Button) grid_1.getChildAt(j);
            b.setText(String.valueOf(list_rando.get(j)));
            b.setEnabled(true);
            b.setBackgroundColor(Color.rgb(160,32,240));
        }
        for(int i=0;i<5;i++){
            ((Button) grid_4.getChildAt(i)).setText("");
            ((Button) grid_5.getChildAt(i)).setText("");
        }
    }
    public void operations(){
        for(int i=0;i<grid_2.getChildCount();i++){
            TextView b=(TextView)grid_2.getChildAt(i);
            b.setText(String.valueOf(operations[random(4)]));
        }
    }
    /*public void click1(View v){
        temp=((Button)v).getText();
    }*/
    public void click1(View v){
        String s=((Button)v).getText().toString();
        if(s.equals("")) {
            ((Button) v).setText(temp);
            temp="";
            for(int i=0;i<10;i++){
                ((Button)grid_1.getChildAt(i)).setClickable(true);
            }
        }
        else{
            for(int i=0;i<10;i++){
                if(s.equals(((Button)grid_1.getChildAt(i)).getText())){
                    ((Button)grid_1.getChildAt(i)).setBackgroundColor((Color.rgb(160,32,240)));
                    ((Button)v).setText("");
                    ((Button)grid_1.getChildAt(i)).setEnabled(true);
                }
            }
        }

    }
    public void assign(){
        Collections.shuffle(list_rando);
        String s1 = String.valueOf(list_rando);
        Log.d("Cools", s1);
        int c=0;
        String ch;
        for(int i=0;i<10;i+=2){
            ch=((TextView)grid_2.getChildAt(c)).getText().toString();
            if(ch.equals("+"))
                ((TextView)grid_3.getChildAt(c)).setText(String.valueOf(list_rando.get(i)+list_rando.get(i+1)));
            else if(ch.equals("-"))
                ((TextView)grid_3.getChildAt(c)).setText(String.valueOf(list_rando.get(i)-list_rando.get(i+1)));
            else if(ch.equals("x"))
                ((TextView)grid_3.getChildAt(c)).setText(String.valueOf(list_rando.get(i)*list_rando.get(i+1)));
            else if(ch.equals("/"))
                ((TextView)grid_3.getChildAt(c)).setText(String.valueOf(String.format("%.2f",(list_rando.get(i)/(float)(list_rando.get(i+1))))));
            c++;
        }
    }
    public void check(View v) {
        try {
            int a = 0, b = 0, c = 0;
            float d = 0;
            int sum = 0;
            String ch;
            for (int i = 0; i < 5; i++) {
                ch = ((TextView) grid_2.getChildAt(i)).getText().toString();

                if (ch.equals("+")) {
                    a = (Integer.parseInt((((Button) grid_4.getChildAt(i)).getText()).toString()) + Integer.parseInt((((Button) grid_5.getChildAt(i)).getText()).toString()));
                    if (a == Integer.parseInt(((TextView) grid_3.getChildAt(i)).getText().toString()))
                        sum += 1;
                } else if (ch.equals("-")) {
                    b = (Integer.parseInt((((Button) grid_4.getChildAt(i)).getText()).toString()) - Integer.parseInt((((Button) grid_5.getChildAt(i)).getText()).toString()));
                    if (b == Integer.parseInt(((TextView) grid_3.getChildAt(i)).getText().toString()))
                        sum += 1;
                } else if (ch.equals("x")) {
                    c = (Integer.parseInt((((Button) grid_4.getChildAt(i)).getText()).toString()) * Integer.parseInt((((Button) grid_5.getChildAt(i)).getText()).toString()));
                    if (c == Integer.parseInt(((TextView) grid_3.getChildAt(i)).getText().toString()))
                        sum += 1;
                } else if (ch.equals("/")) {
                    d = (Integer.parseInt((((Button) grid_4.getChildAt(i)).getText()).toString()) / (float) Integer.parseInt((((Button) grid_5.getChildAt(i)).getText()).toString()));
                    if (String.format("%.2f", d).equals(((TextView) grid_3.getChildAt(i)).getText().toString()))
                        sum += 1;
                }
            }
            score += sum;
            Log.d("Coolscore", Integer.toString(score));
            if (sum != 5) {
                lives--;
                if (lives != 0)
                    Toast.makeText(this, "Life lost!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Game over", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this,"Congrats! You got all 5!",Toast.LENGTH_LONG).show();
            Log.d("Coollife", Integer.toString(lives));
            if (lives == 0) {
                Intent i = new Intent(this, scoreAct.class);
                i.putExtra("COOL", Integer.toString(score));
                lives = 3;
                score = 0;
                startActivity(i);
            }
            update();
            list_rando.removeAll(list_rando);
            create_puzzle();
        }catch(Exception e){
            Toast.makeText(this,"Fill all empty buttons!", Toast.LENGTH_LONG).show();
        }
        }
        void update(){
            ((TextView)findViewById(R.id.score)).setText("Score: "+score);
            ((TextView)findViewById(R.id.lives)).setText("Lives: "+lives);
        }

}


