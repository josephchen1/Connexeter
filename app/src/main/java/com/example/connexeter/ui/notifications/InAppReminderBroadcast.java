package com.example.connexeter.ui.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.connexeter.R;

import static com.example.connexeter.ui.notifications.App.CHANNEL_2_ID;

public class InAppReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_2_ID)
                .setContentTitle("Attend an event!")
                .setContentText("Support your friends and have some fun!")
                .setSmallIcon(R.drawable.ic_1);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());
    }

}
