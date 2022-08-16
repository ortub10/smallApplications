package com.example.inputexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameEt = findViewById(R.id.name_input);
        Button finishBtn = findViewById(R.id.finish_btn);
        TextView helloTv = findViewById(R.id.hello_output);

        finishBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();

            String hello = getString(R.string.hello);

            helloTv.setText(hello +" "+ name);

        });
    }
}