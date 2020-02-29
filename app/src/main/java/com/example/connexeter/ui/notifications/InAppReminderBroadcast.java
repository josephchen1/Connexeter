package com.example.connexeter.ui.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.connexeter.R;

import static com.example.connexeter.ui.notifications.App.CHANNEL_1_ID;

public class InAppReminderBroadcast extends BroadcastReceiver {

    //creates and modifies notification characteristics
    @Override
    public void onReceive(final Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                .setContentTitle("Attend an event!")
                .setContentText("Support your friends and have some fun!")
                .setSmallIcon(R.drawable.ic_1);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());
    }

}
