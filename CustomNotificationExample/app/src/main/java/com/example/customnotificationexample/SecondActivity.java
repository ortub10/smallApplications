package com.example.customnotificationexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TextView textView = findViewById(R.id.notification_txt);
        textView.setText(getIntent().getStringExtra("notification_txt"));
    }
}
