package com.example.customalertdialogexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button regBtn = findViewById(R.id.register_btn);
        regBtn.setOnClickListener(view->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            View dialogView = getLayoutInflater().inflate(R.layout.register_dialog,null);
            EditText usernameEt = dialogView.findViewById(R.id.use_name_input);
            EditText passwordEt = dialogView.findViewById(R.id.password_input);
            builder.setView(dialogView).setPositiveButton("Register", (dialogInterface, i) -> Toast.makeText(this, "User "+usernameEt.getText().toString()+
                    " has registered with password "+passwordEt.getText().toString(), Toast.LENGTH_SHORT).show()).show();
        });
    }
}