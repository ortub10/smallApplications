package com.example.advanceguiexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    ImageView catImageView;
    int initial_width, initial_height;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catImageView = findViewById(R.id.cat_image);
        initial_width = catImageView.getLayoutParams().width;
        initial_height = catImageView.getLayoutParams().height;

        SeekBar alphaSb = findViewById(R.id.alpha_seekbar);
        SeekBar sizeSb = findViewById(R.id.size_seekbar);
        alphaSb.setMax(255);
        alphaSb.setProgress(255);
        sizeSb.setProgress(100);
        alphaSb.setOnSeekBarChangeListener(this);
        sizeSb.setOnSeekBarChangeListener(this);

        SwitchCompat switchBtn = findViewById(R.id.switch_btn);
        switchBtn.setOnCheckedChangeListener((compoundButton, b) ->
                catImageView.setVisibility(b? View.VISIBLE:View.INVISIBLE));

        EditText editText =findViewById(R.id.edit_text);
        ToggleButton toggleButton = findViewById(R.id.toggle_btn);
        toggleButton.setOnCheckedChangeListener((compoundButton, b) ->{
            if (b){
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            }
            else {
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });

        ImageButton imageButton = findViewById(R.id.image_btn);
        imageButton.setOnLongClickListener(view ->{
            Toast.makeText(this, "Long click", Toast.LENGTH_SHORT).show();
            String input = editText.getText().toString();
            if (input.matches("[0-9]+")){
                int numOfBtnS = Integer.parseInt(input);
                HorizontalScrollView horizons = findViewById(R.id.horizons);
                horizons.removeAllViews();
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                for (int i = 0; i <numOfBtnS ; i++) {
                    Button btn = new Button(this);
                    btn.setText(i+1+"");
                    btn.setOnClickListener(view2->
                            Toast.makeText(this, ((Button)view2).getText(), Toast.LENGTH_SHORT).show());
                    linearLayout.addView(btn,120,120);
                }
                horizons.addView(linearLayout);
            }
            return false;
        });
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.alpha_seekbar){
            catImageView.getDrawable().setAlpha(i);
        }
        else if (seekBar.getId() == R.id.size_seekbar){
            catImageView.getLayoutParams().width  =i * initial_width/100;
            catImageView.getLayoutParams().height =i * initial_height/100;

            catImageView.requestLayout();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this, "Seekbar change started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this, "Seekbar change ended", Toast.LENGTH_SHORT).show();

    }
}