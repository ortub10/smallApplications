package com.example.firstfileexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText inputEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEt = findViewById(R.id.input_et);

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(view->{
            String textToSave = inputEt.getText().toString();
            try {
                FileOutputStream fos = openFileOutput("details",MODE_APPEND);
                // fos.write(textToSave.getBytes());
                // fos.close();
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(textToSave);
                osw.close();
                inputEt.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button loadBtn = findViewById(R.id.load_btn);
        loadBtn.setOnClickListener(view->{
//            int BUFFER_SIZE = 128;
//            byte[] bytes = new byte[BUFFER_SIZE];
            try {
                FileInputStream fis =  openFileInput("details");
                /*String total = "";
                int bytesRead;
                while ((bytesRead = fis.read(bytes))!=-1){
                    total+=new String(bytes);
                    Log.d("bytesRead",bytesRead+"");
                }
                inputEt.setText(total);*/

                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String current;
                StringBuilder total = new StringBuilder();
                while ( (current = br.readLine())!=null){
                    total.append(current);
                }
                inputEt.setText(total.toString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}