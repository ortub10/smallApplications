package com.example.explicitintentexample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        String userName = getIntent().getStringExtra("user_name");
        TextView textView = findViewById(R.id.username_output);
        textView.setText(userName);

    }
}
