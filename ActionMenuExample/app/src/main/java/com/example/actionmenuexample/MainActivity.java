package com.example.actionmenuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("details",MODE_PRIVATE);
        editText = findViewById(R.id.username_input);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save){

            sp.edit().putString("user_name", editText.getText().toString()).commit();
            editText.setText("");
            return true;
        }

        else if (item.getItemId() == R.id.action_load){
            editText.setText(sp.getString("user_name",""));
        }
        return super.onOptionsItemSelected(item);
    }
}