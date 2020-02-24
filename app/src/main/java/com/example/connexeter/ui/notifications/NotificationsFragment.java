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
                        1,"WinterFest", "Little Winter Festival on Weth Quad", "11:00 to 3:00", "02/21/2020"
                )
        );

        eventList.add(
                new Event (
                        2,"WinterFest", "Little Winter Festival on Weth Quad", "11:00 to 3:00", "02/22/2020"
                )
        );


        eventList.add(
                new Event (
                        3,"WinterFest", "Little Winter Festival on Weth Quad", "11:00 to 3:00", "02/23/2020"
                )
        );

        eventList.add(
                new Event (
                        4,"WinterFest", "Little Winter Festival on Weth Quad", "11:00 to 3:00", "02/24/2020"
                )
        );

        eventList.add(
                new Event (
                        5,"WinterFest", "Little Winter Festival on Weth Quad", "11:00 to 3:00", "02/25/2020"
                )
        );

        eventList.add(
                new Event (6,"WinterFest", "Little Winter Festival on Weth Quad", "11:00 to 3:00", "02/26/2020"
                )
        );

        for (int x = 0; x<eventList.size(); x++) {
            if (tsLong>(eventList.get(x).getDateMS())) {
                Toast.makeText(getActivity(), "Task Completed", Toast.LENGTH_SHORT).show();
                eventList.remove(x);
                x--;
            }
        }

        //creating recyclerview adapter
        EventAdapter adapter = new EventAdapter(getActivity(), eventList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return root;
    }
}