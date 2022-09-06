package com.example.secondfileexample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    ImageView imageView;
    ActivityResultLauncher<Intent> bitmapResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result !=null && result.getResultCode() == RESULT_OK){
            if (result.getData()!=null){
                bitmap = (Bitmap)result.getData().getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.result_image);

        Button takePicBtn = findViewById(R.id.pic_btn);
        takePicBtn.setOnClickListener(view->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            bitmapResult.launch(intent);
        });

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(view->{
            try {
                FileOutputStream fos =  openFileOutput("picture",MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,fos);
                fos.close();
                imageView.setImageBitmap(null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button loadBtn = findViewById(R.id.load_btn);
        loadBtn.setOnClickListener(view->{
            try {
                FileInputStream fis = openFileInput("picture");
                bitmap = BitmapFactory.decodeStream(fis);
                imageView.setImageBitmap(bitmap);
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}