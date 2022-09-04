package com.example.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText usernameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("details",MODE_PRIVATE);
        usernameEt = findViewById(R.id.username_input);
        if (sp.getBoolean("not_first_run",false)){
            usernameEt.setText(sp.getString("user_name",""));
        }

        Button secondBtn = findViewById(R.id.second_btn);
        secondBtn.setOnClickListener(view->{
            Intent intent = new Intent(this,SecondActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user_name",usernameEt.getText().toString());
        editor.putBoolean("not_first_run",true);

        editor.commit();
    }
}