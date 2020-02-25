package com.example.connexeter.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { textView.setText(s);
            }
        });
        eventList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//int id, String title, String description, String time, String date

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
                        "12:00 PM to 3:00 PM",
                        "02/29/2020"
                )
        );





        for (int x = 0; x<eventList.size()-1; x++) {
            if (eventList.get(x+1).getDateMS()<(eventList.get(x).getDateMS())) {
                Toast.makeText(getActivity(), "Reorganized by Date", Toast.LENGTH_SHORT).show();
                Event temp = eventList.get(x+1);
                eventList.remove(x+1);
                eventList.add(x, temp);
                x=0;
            }
        }



//        for (int x = 0; x<eventList.size(); x++) {
//            if (tsLong>(eventList.get(x).getDateMS())) {
//                Toast.makeText(getActivity(), "Task Completed", Toast.LENGTH_SHORT).show();
//                eventList.remove(x);
//                x--;
//            }
//        }

        //creating recyclerview adapter
        EventAdapter adapter = new EventAdapter(getActivity(), eventList);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return root;
    }
}