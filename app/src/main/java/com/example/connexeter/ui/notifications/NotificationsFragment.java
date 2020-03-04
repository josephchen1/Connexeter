package com.example.connexeter.ui.notifications;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;


public class NotificationsFragment extends Fragment {

    public RecyclerView recyclerView;
    List<Event> eventList;
    List<Event> futureEventList = new ArrayList<>();
    Long tsLong = System.currentTimeMillis();


    //method to create notification for events
    public void sendOnChannel1(Event event, int x) {
        Intent notificationIntent = new Intent(getContext(), MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.TITLE, event.getTitle());
        notificationIntent.putExtra(MyNotificationPublisher.DESC,event.getDate() + " " + event.getStartTime());
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, x);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), x, notificationIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

        //sets notification to send 30 minutes before event start time
        long futureInMillis = System.currentTimeMillis() + ((futureEventList.get(x).getDateMS()) + futureEventList.get(x).getStartTimeMS())-(System.currentTimeMillis()+19800000);

        //makes sure that the event that is to be notified has not already started
        if ((futureInMillis-System.currentTimeMillis())>= 0) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            //Log.d("NOTIFJO", "add notif" + event.getTitle() + (futureInMillis-System.currentTimeMillis()) + "futureInMillis");
        }


    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        eventList = new ArrayList<>();
        final Switch showPast = (Switch) root.findViewById(R.id.showSwitch);
        showPast.setChecked(pref.getBoolean("value", true));

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //adds all the events into the array eventList
        add();

        //creates a new array called futureEventList that only contains events that have not occured
        int y = 0;
        for (int x = 0; x < eventList.size(); x++) {
            if ((eventList.get(x).getDateMS() + eventList.get(x).getStartTimeMS()) > (System.currentTimeMillis() + 86400000)) {
                eventList.get(x).setId(y);
                futureEventList.add(eventList.get(x));

                //sets and schedules notifications for the events
                sendOnChannel1(futureEventList.get(y), y);
                y++;
                //Log.d("NOTIFJO", "add notif" +eventList.get(x).getTitle()+ " "+((eventList.get(x).getDateMS() + eventList.get(x).getStartTimeMS())-(System.currentTimeMillis()-86400000)));
            }
        }
        eventList.clear();

        //shows or hides past events depending on user choice
        if (showPast.isChecked()) {
            add();
            Toast.makeText(getActivity(), "Hiding past events", Toast.LENGTH_SHORT).show();

            //saves switch setting even if app or tab is closed
            SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
            edit.putBoolean("value", true);
            edit.apply();
            showPast.setChecked(true);

            for (int x = 0; x < eventList.size(); x++) {

                //deletes events that occured yesterday and before then from the RecyclerView
                if (tsLong > (eventList.get(x).getDateMS()+86400000)) {
                    //updates the layout with the new events
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    eventList.remove(x);
                    x--;
                }
            }
        } else {
            eventList.clear();

            //saves switch setting even if app or tab is closed
            SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
            edit.putBoolean("value", false);
            edit.apply();

            showPast.setChecked(false);
            Toast.makeText(getActivity(), "Showing past events", Toast.LENGTH_SHORT).show();

            add();
            //updates the layout with the new events
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        showPast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    eventList.clear();
                    add();
                    Toast.makeText(getActivity(), "Hiding past events", Toast.LENGTH_SHORT).show();

                    //saves switch setting even if app or tab is closed
                    SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                    edit.putBoolean("value", true);
                    edit.apply();
                    showPast.setChecked(true);
                    for (int x = 0; x < eventList.size(); x++) {

                        //deletes events that occured yesterday and before then from the RecyclerView
                        if (tsLong > (eventList.get(x).getDateMS()+86400000)) {

                            //updates the layout with the new events
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            eventList.remove(x);
                            x--;
                        }
                    }
                } else {
                    eventList.clear();

                    //saves switch setting even if app or tab is closed
                    SharedPreferences.Editor edit = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                    edit.putBoolean("value", false);
                    edit.apply();
                    showPast.setChecked(false);
                    Toast.makeText(getActivity(), "Showing past events", Toast.LENGTH_SHORT).show();
                    add();

                    //updates the layout with the new events
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });

        //creating recyclerview adapter
        EventAdapter adapter = new EventAdapter(getActivity(), eventList);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return root;
    }

    //adds all the inputted events
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
                        "Last Day of Winter Term",
                        "PEA",
                        "12:30 PM ",
                        "03/06/2020"
                )
        );

        eventList.add(
                new Event(16,
                        "Exeter Association of Rock Assembly",
                        "Assembly Hall",
                        "9:50 AM ",
                        "03/03/2020"
                )
        );

        eventList.add(
                new Event(17,
                        "Spring Term classes begin",
                        "PEA",
                        "8:00 AM ",
                        "03/23/2020"
                )
        );

        eventList.add(
                new Event(18,
                        "Saturday Classes",
                        "PEA",
                        "8:00 AM ",
                        "04/04/2020"
                )
        );

        eventList.add(
                new Event(19,
                        "Climate Action Day",
                        "PEA",
                        "8:00 AM ",
                        "04/24/2020"
                )
        );

        eventList.add(
                new Event(20,
                        "Saturday Classes",
                        "PEA",
                        "8:00 AM ",
                        "04/25/2020"
                )
        );

        eventList.add(
                new Event(21,
                        "Saturday Classes",
                        "PEA",
                        "8:00 AM ",
                        "05/16/2020"
                )
        );

        eventList.add(
                new Event(22,
                        "Exeter/Andover Games",
                        "PEA",
                        "8:00 AM ",
                        "05/23/2020"
                )
        );

        eventList.add(
                new Event(23,
                        "School Ends",
                        "PEA",
                        "12:30 PM ",
                        "06/04/2020"
                )
        );

        eventList.add(
                new Event(24,
                        "Graduation",
                        "PEA",
                        "11:00 AM ",
                        "06/07/2020"
                )
        );

        //sorts events in the order of start date
        for (int x = 0; x < eventList.size() - 1; x++) {
            if (eventList.get(x + 1).getDateMS() < (eventList.get(x).getDateMS())) {
                Event temp = eventList.get(x + 1);
                eventList.remove(x + 1);
                eventList.add(x, temp);
                x = 0;
            }
        }

        //sorts events in the order of start time within the same dates
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