package com.example.advanceimplicitintentexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_activity);

        EditText addressEt =  findViewById(R.id.address_et);
        EditText subjectEt = findViewById(R.id.subject_et);
        EditText bodyEt = findViewById(R.id.body_et);

        addressEt.setText(getIntent().getStringArrayExtra(Intent.EXTRA_EMAIL)[0]);
        subjectEt.setText(getIntent().getStringExtra(Intent.EXTRA_SUBJECT));
        bodyEt.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }
}
