package com.example.listactivityexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends ListActivity {

    ArrayList<Dog> dogs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogs.add(new Dog(10,"Rexi"));
        dogs.add(new Dog(12,"Humi"));
        dogs.add(new Dog(8,"Zoe"));
        dogs.add(new Dog(6,"Rex"));
        dogs.add(new Dog(3,"Puppy"));
        dogs.add(new Dog(7,"Zelig"));

        ArrayAdapter<Dog> adapter = new ArrayAdapter<>(this, R.layout.dog_cel,R.id.dog_text,dogs);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, dogs.get(position).toString(), Toast.LENGTH_SHORT).show();
    }
}