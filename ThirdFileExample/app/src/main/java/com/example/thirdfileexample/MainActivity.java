package com.example.thirdfileexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstEd = findViewById(R.id.first_name);
        EditText lastEd = findViewById(R.id.last_name);
        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(view->{
            String firstName = firstEd.getText().toString();
            String lastName = lastEd.getText().toString();

            Person person = new Person(firstName,lastName);

            try {
                FileOutputStream fos = openFileOutput("person",MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(person);
                oos.close();

                firstEd.setText("");
                lastEd.setText("");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button loadBtn = findViewById(R.id.load_btn);
        loadBtn.setOnClickListener(view->{

            try {
                FileInputStream fis = openFileInput("person");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Person person =  (Person) ois.readObject();
                ois.close();

                firstEd.setText(person.getFirstName());
                lastEd.setText(person.getLastName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }
}