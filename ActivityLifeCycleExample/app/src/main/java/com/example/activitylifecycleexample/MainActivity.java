package com.example.activitylifecycleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final  String TAG = "ActivityLifeCycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "on create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "on destroy");
    }
}