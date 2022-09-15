package com.example.alertitemsdialogexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] colors = {"Green","Blue","Red","Yellow","Black","Purple" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button itemsBtn = findViewById(R.id.items_dialog);
        itemsBtn.setOnClickListener(view->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Please choose a color")
                    .setItems(colors,new MyItemsListener())
                    .setNegativeButton("Cancel", new MyItemsListener())
                    .setCancelable(false).show();
        });

        Button multiBtn = findViewById(R.id.multi_choice_dialog);
        multiBtn.setOnClickListener(view->{
            ArrayList<String> days = new ArrayList<>();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Please pick days to call")
                    .setMultiChoiceItems(R.array.days, null, (dialogInterface, i, b) -> {
                        String[] daysArray = getResources().getStringArray(R.array.days);
                        if (b){
                            days.add(daysArray[i]);
                        }
                        else {
                            days.remove(daysArray[i]);
                        }
                    }).setPositiveButton("Finished", (dialogInterface, i) -> Toast.makeText(this, days.toString(), Toast.LENGTH_SHORT).show()).show();

        });

        Button singleBtn = findViewById(R.id.single_choice_dialog);
        singleBtn.setOnClickListener(view->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pick a day")
                    .setSingleChoiceItems(R.array.days, -1, (dialogInterface, i) -> Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.days)[i], Toast.LENGTH_SHORT).show()).show();
        });
    }

    private class MyItemsListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == DialogInterface.BUTTON_NEGATIVE)
                Toast.makeText(MainActivity.this, "No color chosen", Toast.LENGTH_SHORT).show();

            else
                Toast.makeText(MainActivity.this, "Color chosen "+colors[i], Toast.LENGTH_SHORT).show();
        }
    }
}