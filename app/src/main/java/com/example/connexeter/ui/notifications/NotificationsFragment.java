package com.example.connexeter.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
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

import static android.content.Context.MODE_PRIVATE;

//TODO: use SimpleDateFormat to compare startTimes; separate Time to startTime and endTime
//TODO: so you can compare startTime and organize it like so, make sure when comparing
//TODO: that they share the same date!!!!


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    public RecyclerView recyclerView;
    List<Event> eventList;
    Long tsLong = System.currentTimeMillis();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        eventList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final Switch showPast = (Switch) root.findViewById(R.id.showSwitch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        showPast.setChecked(pref.getBoolean("value", true));


        if(showPast.isChecked()) {
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

        //creating recyclerview adapter
        EventAdapter adapter = new EventAdapter(getActivity(), eventList);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void add() {
        eventList.add(
                new Event (

                        "A Cappella Showcase",
                        "The Bowld",
                        "8:00 PM",
                        "02/07/2020"
                )
        );
        eventList.add(
                new Event (

                        "Abbot Casino",
                        "Grainger Auditorium",
                        "8:00 PM",
                        "02/08/2020"
                )
        );
        eventList.add(
                new Event (

                        "Boston Standup Comedy",
                        "Assembly Hall",
                        "9:00 PM",
                        "02/15/2020"
                )
        );
        eventList.add(
                new Event (

                        "Asian Cultural Showcase",
                        "Assembly Hall",
                        "8:00 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event (

                        "Pep Rally",
                        "Love Gym",
                        "8:30 PM",
                        "02/28/2020"
                )
        );
        eventList.add(
                new Event (

                        "The Secret Garden",
                        "Goel Theater",
                        "7:00 PM",
                        "02/21/2020"
                )
        );
        eventList.add(
                new Event (

                        "The Secret Garden",
                        "Goel Theater",
                        "7:00 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event (

                        "The Secret Garden",
                        "Goel Theater",
                        "1:00 PM",
                        "02/23/2020"
                )
        );
        eventList.add(
                new Event (

                        "Stand Up Comedy 2 Electric Boogaloo",
                        "Phelps Commons",
                        "9:00 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event (

                        "Winter Festival",
                        "Wetherell Quad",
                        "12:00 PM",
                        "3:00 PM",
                        "02/23/2020"
                )
        );
        eventList.add(
                new Event (

                        "Shrove Tuesday Pancake DInner",
                        "Phillips Church",
                        "6:00 PM",
                        "02/25/2020"
                )
        );
        eventList.add(
                new Event (

                        "Bus to Mall & Movies",
                        "Tan Lane",
                        "5:30 PM",
                        "02/22/2020"
                )
        );
        eventList.add(
                new Event (

                        "Bus to Mall & Movies",
                        "Tan Lane",
                        "5:30 PM",
                        "02/29/2020"
                )
        );
        eventList.add(
                new Event (

                        "Exeter Association of Rock Concert",
                        "Phelps Commons",
                        "8:00 PM",
                        "02/28/2020"
                )
        );

        for (int x = 0; x<eventList.size()-1; x++) {
            if (eventList.get(x+1).getDateMS()<(eventList.get(x).getDateMS())) {
                Event temp = eventList.get(x+1);
                eventList.remove(x+1);
                eventList.add(x, temp);
                x=0;
            }
        }

        for (int x = 0; x<eventList.size()-1; x++) {
            if (eventList.get(x+1).getDateMS()==(eventList.get(x).getDateMS())) {
                if (eventList.get(x+1).getStartTimeMS()<(eventList.get(x).getStartTimeMS())) {
                    Event temp = eventList.get(x+1);
                    eventList.remove(x+1);
                    eventList.add(x, temp);
                    x=0;
                }
            }
        }

    }

}