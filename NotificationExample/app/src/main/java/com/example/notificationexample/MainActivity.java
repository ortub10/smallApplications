package com.example.notificationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    NotificationManager manager;
    final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText notificationText = findViewById(R.id.notification_text);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Button showNotification = findViewById(R.id.push_notification);
        showNotification.setOnClickListener(view -> {
            String channelId = null;

            if (Build.VERSION.SDK_INT>=26){
                channelId = "some_channel_id";
                CharSequence channelName = "Some Channel";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

                manager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,channelId);
            builder.setSmallIcon(android.R.drawable.star_on)
                    .setContentTitle("Notification title")
                    .setContentText("Notification extra text body...");
            builder.setPriority(Notification.PRIORITY_MAX); // be the first
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("notification_text",notificationText.getText().toString());
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            // Adding action - note must work with NotificationCompat
            Intent actionIntent = new Intent(this,SecondActivity.class);
            actionIntent.putExtra("notification_text","Play");
            PendingIntent playPendingIntent = PendingIntent.getActivity(this,1,actionIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.addAction(new NotificationCompat.Action(android.R.drawable.ic_media_play,"Play",playPendingIntent));


            Notification notification = builder.build();
            notification.defaults = Notification.DEFAULT_VIBRATE;
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            manager.notify(NOTIFICATION_ID,notification);

        });
        Button removeNotification = findViewById(R.id.remove_notification);
        removeNotification.setOnClickListener(view -> manager.cancel(NOTIFICATION_ID));
    }
}