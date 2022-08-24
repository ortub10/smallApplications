package com.example.explicitintentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.username_input);
        Button regBtn = findViewById(R.id.register_btn);
        regBtn.setOnClickListener(view -> {
            String userName = editText.getText().toString();
            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra("user_name",userName);
            startActivity(intent);

        });
    }
}