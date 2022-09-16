package com.example.datetimepickersdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timeTv = findViewById(R.id.time_output);
        TextView dateTv = findViewById(R.id.date_output);

        Button dateBtn = findViewById(R.id.date_btn);
        dateBtn.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(this, (datePicker, i, i1, i2) ->
                    dateTv.setText(MessageFormat.format("{0}/{1}/{2}", i2,i1+1,i)),year,month, day);

            dpd.show();
        });

        Button timeBtn = findViewById(R.id.time_btn);
        timeBtn.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(this, (timePicker, i, i1) ->
                    timeTv.setText(MessageFormat.format("{0}:{1}",i,i1)),hour,minute,true);

            tpd.show();
        });
    }
}