package com.example.connexeter.ui.notifications;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.sax.RootElement;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Application.getProcessName;
import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static com.example.connexeter.ui.notifications.App.CHANNEL_1_ID;
import static com.example.connexeter.ui.notifications.App.CHANNEL_2_ID;


public class NotificationsFragment extends Fragment {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    private NotificationsViewModel notificationsViewModel;
    public RecyclerView recyclerView;
    List<Event> eventList;
    List<Event> futureEventList = new ArrayList<>();
    Long tsLong = System.currentTimeMillis();

    public void sendOnChannel1(Event event, int x) {

        Intent notificationIntent = new Intent(getContext(), MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.TITLE, event.getTitle());
        notificationIntent.putExtra(MyNotificationPublisher.DESC,event.getDate() + " " + event.getStartTime());
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, x);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), x, notificationIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);


        long futureInMillis = System.currentTimeMillis() + ((futureEventList.get(x).getDateMS()) + futureEventList.get(x).getStartTimeMS())-(System.currentTimeMillis()+19800000);
        Log.d("NOTJO",  event.getTitle()+(futureEventList.get(x).getDateMS()) + "");
        Log.d("NOTJO", futureEventList.get(x).getId() + " " + x);
        Log.d("NOTIFJO", "time set " + futureInMillis + "for event" +event.getTitle());
        if ((futureInMillis-System.currentTimeMillis())>= 0) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            Log.d("NOTIFJO", "add notif" + event.getTitle() + (futureInMillis-System.currentTimeMillis()) + "futureInMillis");
        }

    }

    public void sendOnChannel2() {
        String title = "Get to work!";
        String message = "Let's strive for productivity today!";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_2_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_1);


        Intent intent = new Intent((getContext()), getActivity().getClass());
        PendingIntent activity = PendingIntent.getActivity(getContext(), 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        Notification notification = builder.build();

        notificationManagerCompat.notify(1, builder.build());
        Intent notificationIntent = new Intent(getContext(), App.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

//        long futureInMillis = System.currentTimeMillis() + 15*1000;
//        Log.d("NOTIFCH2", "set futureInMillis" + futureInMillis);

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis()+15*1000, pendingIntent);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationManager = NotificationManagerCompat.from(getContext());
        Toast.makeText(getActivity(), "onCreateView", Toast.LENGTH_SHORT).show();
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        eventList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final Switch showPast = (Switch) root.findViewById(R.id.showSwitch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        showPast.setChecked(pref.getBoolean("value", true));

        add();
        int y = 0;
        for (int x = 0; x < eventList.size(); x++) {
            if ((eventList.get(x).getDateMS() + eventList.get(x).getStartTimeMS()) > (System.currentTimeMillis() - 86400000)) {
                eventList.get(x).setId(y);
                futureEventList.add(eventList.get(x));
                sendOnChannel1(futureEventList.get(y), y);
                y++;
                //Log.d("NOTIFJO", "add notif" +eventList.get(x).getTitle()+ " "+((eventList.get(x).getDateMS() + eventList.get(x).getStartTimeMS())-(System.currentTimeMillis()-86400000)));
            }
        }
        eventList.clear();

        if (showPast.isChecked()) {
            add();
            Toast.makeText(getActivity(), "Hiding past events", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
            edit.putBoolean("value", true);
            edit.apply();
            showPast.setChecked(true);
            for (int x = 0; x < eventList.size(); x++) {
                if (tsLong > (eventList.get(x).getDateMS())) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    eventList.remove(x);
                    x--;
                }
            }
        } else {
            eventList.clear();
            SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
            edit.putBoolean("value", false);
            edit.apply();
            showPast.setChecked(false);
            Toast.makeText(getActivity(), "Showing past events", Toast.LENGTH_SHORT).show();
            add();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        showPast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    eventList.clear();
                    add();
                    Toast.makeText(getActivity(), "Hiding past events", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                    edit.putBoolean("value", true);
                    edit.apply();
                    showPast.setChecked(true);
                    for (int x = 0; x < eventList.size(); x++) {
                        if (tsLong > (eventList.get(x).getDateMS())) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            eventList.remove(x);
                            x--;
                        }
                    }
                } else {
                    eventList.clear();
                    SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                    edit.putBoolean("value", false);
                    edit.apply();
                    showPast.setChecked(false);
                    Toast.makeText(getActivity(), "Showing past events", Toast.LENGTH_SHORT).show();
                    add();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });


        for (int x = 0; x < eventList.size(); x++) {
                    if ((eventList.get(x).getDateMS() + eventList.get(x).getStartTimeMS()) > (System.currentTimeMillis() - 86400000)) {
                        eventList.get(x).setId(x);
                        sendOnChannel1(eventList.get(x), x);
                        Log.d("NOTIFJO", ""+((eventList.get(x).getDateMS() + eventList.get(x).getStartTimeMS())-(System.currentTimeMillis()-86400000)));
                    }
                }
        //creating recyclerview adapter
        EventAdapter adapter = new EventAdapter(getActivity(), eventList);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void add() {
        eventList.add(
                new Event(1,
                        "A Cappella Showcase",
                        "The Bowld",
                        "8:00 PM",
                        "02/07/2020"
                )
        );
        eventList.add(
                new Event(2,
                        "Abbot Casino",
                        "Grainger Auditorium",
                        "8:00 PM",
                        "02/08/2020"

                )
        );
        eventList.add(
                new Event(3,
                        "Boston Standup Comedy",
                        "Assembly Hall",
                        "9:00 PM",
                        "02/15/2020"
                )
        );
        eventList.add(
                new Event(4,
                        "Asian Cultural Showcase",
                        "Assembly Hall",
                        "8:00 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event(5,
                        "Pep Rally",
                        "Love Gym",
                        "8:30 PM",
                        "02/28/2020"
                )
        );
        eventList.add(
                new Event(6,
                        "The Secret Garden",
                        "Goel Theater",
                        "7:00 PM",
                        "02/21/2020"
                )
        );
        eventList.add(
                new Event(7,
                        "The Secret Garden",
                        "Goel Theater",
                        "7:00 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event(8,
                        "The Secret Garden",
                        "Goel Theater",
                        "1:00 PM",
                        "02/23/2020"
                )
        );
        eventList.add(
                new Event(9,
                        "Stand Up Comedy 2 Electric Boogaloo",
                        "Phelps Commons",
                        "9:00 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event(10,
                        "Winter Festival",
                        "Wetherell Quad",
                        "12:00 PM",
                        "3:00 PM",
                        "02/23/2020"
                )
        );
        eventList.add(
                new Event(11,
                        "Shrove Tuesday Pancake DInner",
                        "Phillips Church",
                        "6:00 PM",
                        "02/25/2020"
                )
        );
        eventList.add(
                new Event(12,
                        "Bus to Mall & Movies",
                        "Tan Lane",
                        "5:30 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event(13,
                        "Bus to Mall & Movies",
                        "Tan Lane",
                        "5:30 PM",
                        "02/29/2020"
                )
        );
        eventList.add(
                new Event(14,
                        "Exeter Association of Rock Concert",
                        "Phelps Commons",
                        "8:00 PM",
                        "02/28/2020"
                )
        );

        eventList.add(
                new Event(15,
                        "test",
                        "Phelps Commons",
                        "9:11 PM ",
                        "02/28/2020"
                )
        );

        for (int x = 0; x < eventList.size() - 1; x++) {
            if (eventList.get(x + 1).getDateMS() < (eventList.get(x).getDateMS())) {
                Event temp = eventList.get(x + 1);
                eventList.remove(x + 1);
                eventList.add(x, temp);
                x = 0;
            }
        }

        for (int x = 0; x < eventList.size() - 1; x++) {
            if (eventList.get(x + 1).getDateMS() == (eventList.get(x).getDateMS())) {
                if (eventList.get(x + 1).getStartTimeMS() < (eventList.get(x).getStartTimeMS())) {
                    Event temp = eventList.get(x + 1);
                    eventList.remove(x + 1);
                    eventList.add(x, temp);
                    x = 0;

                }
            }
        }

    }

}