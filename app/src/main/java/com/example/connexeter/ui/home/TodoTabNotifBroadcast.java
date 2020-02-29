package com.example.connexeter.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.connexeter.R;

import static com.example.connexeter.ui.notifications.App.CHANNEL_1_ID;


public class TodoTabNotifBroadcast extends BroadcastReceiver {


    //creates and modifies notification characteristics
    @Override
    public void onReceive(final Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                .setContentTitle("Let's be productive")
                .setContentText("Input your tasks and let's get started on them!")
                .setSmallIcon(R.drawable.ic_1);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(199, builder.build());
    }

}
