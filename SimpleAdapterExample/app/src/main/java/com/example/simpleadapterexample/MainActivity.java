package com.example.simpleadapterexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.my_list);
        List<Map<String,Object>> date = new ArrayList<>();

        HashMap<String,Object>  china = new HashMap<>();
        china.put("flag",R.drawable.flag_china);
        china.put("name","China");
        china.put("is_good",true);
        date.add(china);

        HashMap<String,Object> australia = new HashMap<>();
        australia.put("flag",R.drawable.flag_australia);
        australia.put("name","Australia");
        australia.put("is_good",true);
        date.add(australia);

        HashMap<String,Object>  brazil = new HashMap<>();
        brazil.put("flag",R.drawable.flag_brazil);
        brazil.put("name","Brazil");
        brazil.put("is_good",false);
        date.add(brazil);

        HashMap<String,Object>  uk = new HashMap<>();
        uk.put("flag",R.drawable.flag_uk);
        uk.put("name","UK");
        uk.put("is_good",true);
        date.add(uk);

        HashMap<String,Object>  france = new HashMap<>();
        france.put("flag",R.drawable.flag_france);
        france.put("name","France");
        france.put("is_good",false);
        date.add(france);

        HashMap<String,Object>  netherlands = new HashMap<>();
        netherlands.put("flag",R.drawable.flag_netherlands);
        netherlands.put("name","Netherlands");
        netherlands.put("is_good",true);
        date.add(netherlands);

        HashMap<String,Object>  israel = new HashMap<>();
        israel.put("flag",R.drawable.flag_israel);
        israel.put("name","Israel");
        israel.put("is_good",true);
        date.add(israel);

        HashMap<String,Object>  turkish = new HashMap<>();
        turkish.put("flag",R.drawable.flag_turkish);
        turkish.put("name","Turkish");
        turkish.put("is_good",false);
        date.add(turkish);

        String[] from = {"flag","name","is_good"};
        int[] to = {R.id.country_flag,R.id.country_name, R.id.country_checkBox};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,date,R.layout.country_cel,from ,to);
        listView.setAdapter(simpleAdapter);

    }
}