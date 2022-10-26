package com.example.picassoexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    final  String link = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVmGAaolil_J-8rIqjW4uZm1tmkwBIoXVZGA&usqp=CAU";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.result_image);
        Button downloadBtn = findViewById(R.id.download_btn);
        downloadBtn.setOnClickListener(view -> Picasso.get().load(link).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Download successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, "error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }));
    }
}