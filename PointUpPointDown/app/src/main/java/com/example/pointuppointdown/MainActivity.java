package com.example.pointuppointdown;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{// implements View.OnClickListener {
    private int score = 0;
    TextView scoreTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUp = findViewById(R.id.btn_up);
        Button btnDown = findViewById(R.id.btn_down);
        scoreTv = findViewById(R.id.score_text_view);

        //first approach- the activity is implementing the OnClickListener interface
        //btnUp.setOnClickListener(this);
        //btnDown.setOnClickListener(this);

        //second approach - an inner class is implementing the OnClickListener interface
        //btnUp.setOnClickListener(new MyClickListener());
        //btnDown.setOnClickListener(new MyClickListener());

        //third approach - an anonymous class implementing the OnClickListener interface
        btnUp.setOnClickListener(new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                score++;
                scoreTv.setText(getString(R.string.score_txt) +" "+ "\u200E"+score);

            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                score--;
                scoreTv.setText(getString(R.string.score_txt) +" "+ "\u200E"+score);

            }
        });
    }

    //second approach - an inner class is implementing the OnClickListener interface
   /* private class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if( view.getId() == R.id.btn_up){
                score++;
            }
            else if (view.getId() == R.id.btn_down){
                score--;
            }
            scoreTv.setText(getString(R.string.score_txt) +" "+ "\u200E"+score);
        }
    }*/


    //first approach- the activity is implementing the OnClickListener interface
   /* @Override
    public void onClick(View view) {

       if( view.getId() == R.id.btn_up){
           score++;
       }
       else if (view.getId() == R.id.btn_down){
           score--;
       }
       scoreTv.setText(getString(R.string.score_txt) +" "+ "\u200E"+score);
    }*/
}