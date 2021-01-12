package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

public class NotificaitonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificaiton_layout);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
    }
}