package com.example.recyclerviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Israel",8000000,R.drawable.flag_israel,true));
        countries.add(new Country("Australia",10000000,R.drawable.flag_australia,true));
        countries.add(new Country("Brazil",6000000,R.drawable.flag_brazil,false));
        countries.add(new Country("UK",18000000,R.drawable.flag_uk,true));
        countries.add(new Country("France",15000000,R.drawable.flag_france,false));
        countries.add(new Country("Nederland",16000000,R.drawable.flag_nederland,true));
        countries.add(new Country("Spain",10000000,R.drawable.flag_spain,true));
        countries.add(new Country("Turkish",20000000,R.drawable.flag_turkish,false));
        countries.add(new Country("China",1400000000,R.drawable.flag_china,false));
        countries.add(new Country("Israel",8000000,R.drawable.flag_israel,true));
        countries.add(new Country("Australia",10000000,R.drawable.flag_australia,true));
        countries.add(new Country("Brazil",6000000,R.drawable.flag_brazil,false));
        countries.add(new Country("UK",18000000,R.drawable.flag_uk,true));
        countries.add(new Country("France",15000000,R.drawable.flag_france,false));
        countries.add(new Country("Nederland",16000000,R.drawable.flag_nederland,true));
        countries.add(new Country("Spain",10000000,R.drawable.flag_spain,true));
        countries.add(new Country("Turkish",20000000,R.drawable.flag_turkish,false));
        countries.add(new Country("China",1400000000,R.drawable.flag_china,false));

        CountryAdapter countryAdapter = new CountryAdapter(countries);
        countryAdapter.serListener(new CountryAdapter.MyCountryListener() {
            @Override
            public void onCountryClicked(int position, View view) {
                Toast.makeText(MainActivity.this, countries.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCountryLongClicked(int position, View view) {
                countries.remove(position);
                countryAdapter.notifyItemRemoved(position);
            }
        });

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT)
                    Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                else if(direction == ItemTouchHelper.LEFT)
                    Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();

                countries.remove(viewHolder.getAdapterPosition());
                countryAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(countryAdapter);


    }
}