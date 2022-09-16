package com.example.progressdialogexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean abort = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button spinnerBtn = findViewById(R.id.spinner_btn);
        spinnerBtn.setOnClickListener(view -> {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Download files it may take a few minutes...");
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", (dialogInterface, i) ->
                    Toast.makeText(this, "Process canceled", Toast.LENGTH_SHORT).show());
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            progressDialog.setIndeterminate(true);
            progressDialog.show();

            new Handler().postDelayed((Runnable) progressDialog::dismiss,5000);

        });

        Button progressBtn = findViewById(R.id.progress_btn);
        progressBtn.setOnClickListener(view -> {
            abort = false;
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Download files, please be patient");
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",(dialogInterface, i)->
                abort =true
            );

            progressDialog.show();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (!abort){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        progressDialog.incrementProgressBy(1);
                        if (progressDialog.getProgress() == progressDialog.getMax())
                            abort = true;
                    }
                    progressDialog.dismiss();
                }
            }.start();

        });


    }
}