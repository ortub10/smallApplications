package com.example.semiprogramaticlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText btnsEt = findViewById(R.id.btns_input);

        Button finishBtn = findViewById(R.id.finish_btn);

        finishBtn.setOnClickListener(view ->{
            String numOfBtnsStr =  btnsEt.getText().toString();
            if (numOfBtnsStr.matches("[0-9]+")){
                int numOfBtns  = Integer.parseInt(numOfBtnsStr);
                LinearLayout btnsLayout = findViewById(R.id.btns_layout);
                btnsLayout.removeAllViews();
                for (int i = 0; i<numOfBtns; i++){
                    Button button = new Button(MainActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120,120);
                    button.setLayoutParams(layoutParams);
                    button.setText(i+1+"");
                    button.setOnClickListener(view1 ->
                            Toast.makeText(this, ((Button)view1).getText(), Toast.LENGTH_SHORT).show()
                    );
                    btnsLayout.addView(button);
                }
            }
        });
    }
}