package com.example.customnotificationexample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int NOTIFICATION_ID = 1;

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        String channelId = null;
        if(Build.VERSION.SDK_INT>=26) {
            channelId =  "some_channel_id" ;
            CharSequence channelName =  "Some Channel" ;
            int  importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel =  new  NotificationChannel(channelId, channelName, importance);
            manager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,channelId);
        builder.setSmallIcon(android.R.drawable.ic_media_play);

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_layout);

        Intent playIntent = new Intent(this,SecondActivity.class);
        playIntent.putExtra("notification_txt","play");
        PendingIntent playPendingIntent = PendingIntent.getActivity(this,0,playIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.play_btn,playPendingIntent);

        Intent nextIntent = new Intent(this,SecondActivity.class);
        nextIntent.putExtra("notification_txt","next");
        PendingIntent nextPendingIntent = PendingIntent.getActivity(this,1,nextIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.next_btn,nextPendingIntent);

        Intent prevIntent = new Intent(this,SecondActivity.class);
        prevIntent.putExtra("notification_txt","prev");
        PendingIntent prevPendingIntent = PendingIntent.getActivity(this,2,prevIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.prev_btn,prevPendingIntent);

        Intent pauseIntent = new Intent(this,SecondActivity.class);
        pauseIntent.putExtra("notification_txt","pause");
        PendingIntent pausePendingIntent = PendingIntent.getActivity(this,3,pauseIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.pause_btn,pausePendingIntent);

        builder.setContent(remoteViews);

        manager.notify(NOTIFICATION_ID,builder.build());
    }
}
