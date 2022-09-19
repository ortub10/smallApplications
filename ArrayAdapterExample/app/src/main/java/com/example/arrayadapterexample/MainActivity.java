package com.example.arrayadapterexample;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView  listView = findViewById(R.id.my_list);

        String[] colors = {"Purple","Red","Yellow","Green","Blue","Black","Purple","Red","Yellow","Green","Blue","Black","Purple","Red","Yellow","Green","Blue","Black"};

        ArrayList<String> colorsList = new ArrayList<>(Arrays.asList(colors));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,colorsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(((adapterView, view, i, l) ->
                Toast.makeText(this, "color chosen "+colorsList.get(i), Toast.LENGTH_SHORT).show()
        ));

        EditText colorEt = findViewById(R.id.color_input);
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            String color = colorEt.getText().toString();
            if (!color.equals("")){
                colorsList.add(color);
                colorEt.setText("");
            }

            adapter.notifyDataSetChanged();
        });
    }

}