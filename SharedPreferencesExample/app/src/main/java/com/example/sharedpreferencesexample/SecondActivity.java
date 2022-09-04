package com.example.sharedpreferencesexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TextView usernameTv = findViewById(R.id.username_output);
        SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);
        usernameTv.setText(sp.getString("user_name",""));

    }
}
