package com.example.activityforresultexample;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView resultTv;
    ImageView resultImg;
    TextView speechTv;
    TextToSpeech tts;
    StringBuilder sb;

    ActivityResultLauncher<Intent>  speechResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result!=null && result.getResultCode() == RESULT_OK){
                if (result.getData()!=null){
                    ArrayList<String> results = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    sb = new StringBuilder();
                    for(String res: results){
                        sb.append(res).append(", ");
                        speechTv.setText(res);
                    }
                    speechTv.setText(sb.toString());
                    tts = new TextToSpeech(MainActivity.this,MainActivity.this);
                }
            }
        }
    });

    ActivityResultLauncher<Intent>  imgResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result!=null && result.getResultCode() == RESULT_OK){
                if (result.getData()!=null){
                    Bitmap bitmap = (Bitmap)result.getData().getExtras().get("data");
                    resultImg.setImageBitmap(bitmap);
                }
            }
        }
    });

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK){
                if (result.getData()!=null){
                    int grade = result.getData().getIntExtra("grade",0);
                    resultTv.setText(MessageFormat.format("{0}{1}", getResources().getString(R.string.show_grade), grade));
                }

            }
            else if (result != null && result.getResultCode() == RESULT_CANCELED){
                resultTv.setText(getResources().getString(R.string.quitter));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.text_result);
        resultImg = findViewById(R.id.result_img);
        speechTv = findViewById(R.id.speech_result);
        EditText nameEt = findViewById(R.id.name_inout);
        Button takeTextBtn = findViewById(R.id.test_btn);
        takeTextBtn.setOnClickListener(view->{
            Intent intent = new Intent(this, TestActivity.class);
            String name  =  nameEt.getText().toString();
            intent.putExtra("name",name);
            startForResult.launch(intent);

        });

        Button cameraBtn= findViewById(R.id.pic_btn);
        cameraBtn.setOnClickListener(view->{
            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imgResult.launch(intent);
        });

        Button speechBrn = findViewById(R.id.speech_btn);
        speechBrn.setOnClickListener(view -> {
            Intent intent  = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Please speck slowly");
            speechResult.launch(intent);
        });

    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS){
            if (tts.isLanguageAvailable(Locale.forLanguageTag(Locale.getDefault().getLanguage())) == TextToSpeech.LANG_AVAILABLE){
                tts.speak(sb.toString(),TextToSpeech.QUEUE_FLUSH,null,null);
            }
            else {
                Toast.makeText(this, Locale.forLanguageTag(Locale.getDefault().getLanguage())+ " not support", Toast.LENGTH_SHORT).show();
            }
        }
    }
}