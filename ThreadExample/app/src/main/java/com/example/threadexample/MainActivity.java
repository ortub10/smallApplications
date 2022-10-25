package com.example.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    final  String link = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVmGAaolil_J-8rIqjW4uZm1tmkwBIoXVZGA&usqp=CAU";

    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView statusTv = findViewById(R.id.status_tv);
        ImageView imageView = findViewById(R.id.result_image);
        Button downloadBtn = findViewById(R.id.download_btn);
        downloadBtn.setOnClickListener(view -> new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(link);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                if (httpURLConnection.getResponseCode()!=200) return;
                    handler.post(() -> statusTv.setText(getString(R.string.download_started)));
                    Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                    handler.post(() -> {
                        imageView.setImageBitmap(bitmap);
                        statusTv.setText(getString(R.string.download_finished));
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start());


    }
}