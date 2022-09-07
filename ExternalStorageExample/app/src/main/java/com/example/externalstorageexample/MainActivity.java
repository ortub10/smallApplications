package com.example.externalstorageexample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import androidx.exifinterface.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button takePicBtn;
    final int WRITE_PERMISSION_REQUEST = 1;
    File file;
    ActivityResultLauncher<Intent> imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
        if (result !=null && result.getResultCode() == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ExifInterface exifInterface = null;
            try {
                exifInterface = new ExifInterface(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (exifInterface!=null){
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
                Matrix matrix = new Matrix();
                switch (orientation){
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        matrix.setRotate(90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        matrix.setRotate(180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        matrix.setRotate(270);
                        break;
                }

                Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                imageView.setImageBitmap(rotatedBitmap);
            }


        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.result_image);
        takePicBtn = findViewById(R.id.pic_btn);
        takePicBtn.setOnClickListener(view->{
            file = new File(Environment.getExternalStorageDirectory(),"pic.jpg");
            Uri imageUri = FileProvider.getUriForFile(
                    MainActivity.this,
                    "com.example.externalstorageexample.provider", file);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            imageResult.launch(intent);
        });

        if (Build.VERSION.SDK_INT >=23){
            int hasWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWritePermission!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_PERMISSION_REQUEST);
            }
            else {
                takePicBtn.setVisibility(View.VISIBLE);
            }
        }
        else {
            takePicBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_PERMISSION_REQUEST){
            if (grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Can't take picture", Toast.LENGTH_SHORT).show();
            }
            else {
                takePicBtn.setVisibility(View.VISIBLE);
            }
        }
    }
}