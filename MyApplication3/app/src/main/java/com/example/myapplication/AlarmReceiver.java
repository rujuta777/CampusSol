package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "AlarmReceiver class ..", Toast.LENGTH_SHORT).show();
        String topic, venue;
        topic = intent.getStringExtra("Topic");
        venue = intent.getStringExtra("Venue");
        NotificationHelper notificationHelper = new NotificationHelper(context, topic, venue);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());

    }
}


