package com.example.connexeter.ui.notifications;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();

        //creates the Notification Channels
        createNotificationChannels();

        //creates a Notification using the InAppReminderBroadcast class
        Intent intent = new Intent(getApplicationContext(), InAppReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 200, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //sets delay of notification and schedules it appropriately
        long delay = System.currentTimeMillis() + 20*1000;

        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent);

    }

    //creates two notification channels with different settings
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Event Alert",
                    NotificationManager.IMPORTANCE_HIGH

            );

            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Event Alert2",
                    NotificationManager.IMPORTANCE_LOW

            );

            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
    }
}
