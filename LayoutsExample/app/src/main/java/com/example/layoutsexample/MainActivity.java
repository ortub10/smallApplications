package com.example.layoutsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        Button deleteBtn = findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(view -> {

            TableLayout tableLayout = findViewById(R.id.table_layout);
            tableLayout.setColumnCollapsed(1,!tableLayout.isColumnCollapsed(1));
            ((Button)view).setText(tableLayout.isColumnCollapsed(1)?"Show":"Delete");
        });
    }
}