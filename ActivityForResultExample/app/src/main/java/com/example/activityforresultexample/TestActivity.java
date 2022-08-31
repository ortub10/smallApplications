package com.example.activityforresultexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    boolean q1,q2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        TextView userTv = findViewById(R.id.name_output);
        userTv.setText(getIntent().getStringExtra("name"));
        RadioGroup rgq1  = findViewById(R.id.reg1);
        RadioGroup rgq2  = findViewById(R.id.reg2);
        rgq1.setOnCheckedChangeListener(this);
        rgq2.setOnCheckedChangeListener(this);


        //To user has 5 seconds to answer
        new Handler().postDelayed(this::finish, 5000);

        Button finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(view->{
            int grade = 0;
            if (q1) grade+=50;
            if (q2) grade+=50;

            //returning the grade
            Intent intent = new Intent();
            intent.putExtra("grade", grade);
            setResult(RESULT_OK, intent);
            finish();
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup.getId() == R.id.reg1){
            q1 = i == R.id.rb3q1;
        }
        if (radioGroup.getId() == R.id.reg2){
            q2 = i == R.id.rb2q2;
        }
    }
}
