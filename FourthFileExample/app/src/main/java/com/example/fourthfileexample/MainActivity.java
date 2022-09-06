package com.example.fourthfileexample;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;


    ActivityResultLauncher<Intent> imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{

        if (result != null && result.getResultCode() == RESULT_OK){
            if (result.getData() !=null){
                bitmap = (Bitmap) result.getData().getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstEd = findViewById(R.id.first_name);
        EditText lastEd = findViewById(R.id.last_name);
        imageView = findViewById(R.id.person_image);

        Button takePicBtn = findViewById(R.id.pic_btn);
        takePicBtn.setOnClickListener(view->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageResult.launch(intent);
        });

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(view->{

            String firstName = firstEd.getText().toString();
            String lastName = lastEd.getText().toString();

            if (firstName.equals("") || lastName.equals("") || bitmap == null){

                Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
            }
            else {


                Person person = new Person(firstName,lastName, bitmap);


                try {
                    FileOutputStream fos = openFileOutput("person",MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(person);
                    oos.close();

                    firstEd.setText("");
                    lastEd.setText("");
                    imageView.setImageBitmap(null);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                imageView.setImageBitmap(person.getPhoto());
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