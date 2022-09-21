package com.example.customadapterexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView  = findViewById(R.id.country_list);

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Israel",R.drawable.flag_israel,true));
        countries.add(new Country("France",R.drawable.flag_france,false, 600000));
        countries.add(new Country("Australia",R.drawable.flag_australia,true));
        countries.add(new Country("Brazil",R.drawable.flag_brazil,false));
        countries.add(new Country("China",R.drawable.flag_china,false,800000));
        countries.add(new Country("Netherlands",R.drawable.flag_netherlands,true));
        countries.add(new Country("Spain",R.drawable.flag_spain,true,700000));
        countries.add(new Country("Turkish",R.drawable.flag_turkish,false,1400000));
        countries.add(new Country("UK",R.drawable.flag_uk,true));
        countries.add(new Country("Israel",R.drawable.flag_israel,true));
        countries.add(new Country("France",R.drawable.flag_france,false, 600000));
        countries.add(new Country("Australia",R.drawable.flag_australia,true));
        countries.add(new Country("Brazil",R.drawable.flag_brazil,false));
        countries.add(new Country("China",R.drawable.flag_china,false,800000));
        countries.add(new Country("Netherlands",R.drawable.flag_netherlands,true));
        countries.add(new Country("Spain",R.drawable.flag_spain,true,700000));
        countries.add(new Country("Turkish",R.drawable.flag_turkish,false,1400000));
        countries.add(new Country("UK",R.drawable.flag_uk,true));
        countries.add(new Country("Israel",R.drawable.flag_israel,true));
        countries.add(new Country("France",R.drawable.flag_france,false, 600000));
        countries.add(new Country("Australia",R.drawable.flag_australia,true));
        countries.add(new Country("Brazil",R.drawable.flag_brazil,false));
        countries.add(new Country("China",R.drawable.flag_china,false,800000));
        countries.add(new Country("Netherlands",R.drawable.flag_netherlands,true));
        countries.add(new Country("Spain",R.drawable.flag_spain,true,700000));
        countries.add(new Country("Turkish",R.drawable.flag_turkish,false,1400000));
        countries.add(new Country("UK",R.drawable.flag_uk,true));


        CountryAdapter countryAdapter = new CountryAdapter(countries,this);

        listView.setAdapter(countryAdapter);

    }
}