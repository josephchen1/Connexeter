package com.example.connexeter.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.connexeter.R;

import java.util.ArrayList;

import static android.content.Context.ALARM_SERVICE;

public class HomeFragment extends Fragment {

    private EditText itemET;
    private Button btn;
    private ListView tasksList;
    public TextView tasksNum;
    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //sendMessage();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //initialize attributes and objects
        btn = (Button) view.findViewById(R.id.add_btn);
        itemET = view.findViewById(R.id.task_edit_text);
        tasksList = view.findViewById(R.id.tasks_list);
        tasks = FileHelper.readData(getActivity());
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tasks);
        tasksNum = (TextView) view.findViewById(R.id.tasks_num);
        tasksNum.setText("Number of tasks: " + tasks.size());

        //set adapter
        tasksList.setAdapter(adapter);

        //set OnClickListener for button to add tasks
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_btn:
                        String itemEntered = itemET.getText().toString();
                        adapter.add(itemEntered);
                        itemET.setText("");
                        tasksNum.setText("Number of tasks: " + tasks.size());

                        //saves tasks and the data through FileHelper
                        FileHelper.writeData(tasks, getActivity());

                        Toast.makeText(getActivity(), "Task Created", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        //deletes tasks on click
        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                adapter.notifyDataSetChanged();

                //saves deletion of tasks and the data through FileHelper
                FileHelper.writeData(tasks, getActivity());

                tasksNum.setText("Number of tasks: " + tasks.size());
                Toast.makeText(getActivity(), "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    //sends notifications 
    /*public void sendMessage() {
        Intent notificationIntent = new Intent(getContext(), TodoTabNotifBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 199, notificationIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

        long futureInMillis = System.currentTimeMillis();

        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }*/


}