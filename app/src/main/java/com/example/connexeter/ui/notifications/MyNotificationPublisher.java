package com.example.connexeter.ui.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.connexeter.R;

import static com.example.connexeter.ui.notifications.App.CHANNEL_1_ID;

public class MyNotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";
    public static String TITLE = "title";
    public static String DESC = "description";

    @Override
    public void onReceive(final Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                .setContentTitle(intent.getStringExtra(TITLE))
                .setContentText(intent.getStringExtra(DESC))
                .setSmallIcon(R.drawable.ic_1);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(intent.getIntExtra(NOTIFICATION_ID,0), builder.build());


//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Notification notification = intent.getParcelableExtra(NOTIFICATION);
//        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
//        notificationManager.notify(notificationId, notification);

    }

}
