package com.example.runtimepermissionexample;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button settingsBtn;

    final int CALL_PERMISSION_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.phone_input);
        settingsBtn = findViewById(R.id.settings_btn);
        Button callBtn = findViewById(R.id.call_btn);
        callBtn.setOnClickListener(view->{
            if (Build.VERSION.SDK_INT>=23){
                int hasCallPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                if (hasCallPermission == PackageManager.PERMISSION_GRANTED){
                    callPhone();
                }
                else{//PERMISSION_DENIED
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_REQUEST);
                }
            }
            else {
                callPhone();
            }
        });

        settingsBtn.setOnClickListener(view->{
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:"+getPackageName()));
            startActivity(intent);

        });
    }

    private void callPhone(){
        String number = editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==CALL_PERMISSION_REQUEST ){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callPhone();
            }
            else {
                Toast.makeText(this, getString(R.string.no_permission), Toast.LENGTH_LONG).show();
                settingsBtn.setVisibility(View.VISIBLE);
            }
        }
    }
}