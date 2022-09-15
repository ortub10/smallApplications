package com.example.alertdialogexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm shutdown").setMessage("Are you sure you want to exit?")
                .setPositiveButton("yes",new MyAlertDialogListener())
                .setNegativeButton("No",new MyAlertDialogListener())
                .setNeutralButton("Don't know", new MyAlertDialogListener())
                .setCancelable(false).show();

    }

    private class MyAlertDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == DialogInterface.BUTTON_POSITIVE){
                finish();//            super.onBackPressed();
            }
            else if (i == DialogInterface.BUTTON_NEGATIVE){
                Toast.makeText(MainActivity.this, "Glad you decided to stay", Toast.LENGTH_SHORT).show();
            }

            else if(i == DialogInterface.BUTTON_NEUTRAL){
                Toast.makeText(MainActivity.this, "Please make up your mind", Toast.LENGTH_SHORT).show();
            }

        }
    }
}