package com.example.notificationtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificaitonActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(notificationChannel);
                    notification.setChannelId("AppTestNotificationId");
                }
                notification.setContentTitle("This is content title")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build\n" +
                                "notifications, sen and sync data, and use voice actions. Get the official \n" +
                                "Android IDE and developer tools to build app for Android."))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_launcher)).build();
                manager.notify(1, notification.build());
                break;
            default:
                break;
        }
    }

}