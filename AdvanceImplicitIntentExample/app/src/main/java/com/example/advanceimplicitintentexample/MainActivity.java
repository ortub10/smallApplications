package com.example.advanceimplicitintentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText mailEt = findViewById(R.id.email_address);
        EditText siteEt = findViewById(R.id.site_address);
        EditText numberEt = findViewById(R.id.phone_number);
        EditText latEt = findViewById(R.id.lat_et);
        EditText lonEt = findViewById(R.id.lon_et);
        EditText wazeLatEt = findViewById(R.id.waze_lat_et);
        EditText wazeLonEt = findViewById(R.id.waze_lon_et);
        EditText whatsMessageEt = findViewById(R.id.message_et);

        Button mailBtn = findViewById(R.id.send_email);
        mailBtn.setOnClickListener(view->{
            String address = mailEt.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "This is the email body");
            intent.putExtra(Intent.EXTRA_SUBJECT,"This is the email subject");
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{address});
            intent.setType("text/html");

            startActivity(Intent.createChooser(intent , "Send email with..."));
        });

        Button siteBtn = findViewById(R.id.site_btn);
        siteBtn.setOnClickListener(view->{
            String site = siteEt.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+site));
            startActivity(intent);
        });
        Button dialBtn = findViewById(R.id.dial_btn);
        dialBtn.setOnClickListener(view->{
            String number = numberEt.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+number));
            startActivity(intent);

        });
        Button mapBtn = findViewById(R.id.map_btn);
        mapBtn.setOnClickListener(view->{
            String latitude = latEt.getText().toString();
            String longitude = lonEt.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("waze://?ll="+ latitude+","+longitude));
            startActivity(intent);

        });


        Button contactsBtn = findViewById(R.id.contacts_btn);
        contactsBtn.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
            startActivity(intent);
        });

        Button wazeBtn = findViewById(R.id.waze_btn);
        wazeBtn.setOnClickListener(view->{
            String lat = wazeLatEt.getText().toString();
            String lon = wazeLonEt.getText().toString();
            try {
                String url = "waze://?ll="+lat+","+lon+"&navigate=yes";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // If Waze is not installed, open it in Google Play:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
                startActivity(intent);
            }
        });

        Button whatsBtn = findViewById(R.id.whats_btn);
        whatsBtn.setOnClickListener(view->{
            String message = whatsMessageEt.getText().toString();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);

        });

    }
}