package com.example.connexeter.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;
import com.example.connexeter.ui.dashboard.ChangeFormatValues.EditClasses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    public static DashboardViewModel dashboardViewModel;
    RecyclerView recyclerView;//for printing ols info
    addOLSAdapter adapter;
    List<addOLS> addOLSList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

//printing ols thing
        addOLSList = new ArrayList<>();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //setting textView to the current date and time
        final String currentDate = new SimpleDateFormat("MMM, dd, yyyy", Locale.getDefault()).format(new Date());
        final TextView date = root.findViewById(R.id.date);
        date.setText(currentDate);

        //creating spinner (dropdown menu)
        Spinner setWeekNumber = (Spinner) root.findViewById(R.id.weekNumber);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.reply_entries));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setWeekNumber.setAdapter(myAdapter);

        setWeekNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i==0){//Change values
                    startActivity(new Intent(getActivity(), EditClasses.class));
                }//Change values
                if (i==1){ //Week 1 Monday
                    addOLSList.clear();

//                    EditClasses.addFormatList.get(0);
                    addOLSList.add(
                            new addOLS("8:00-8:50",1, "T", "Your Class", "XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("8:55-9:45",1,"B","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("10:45-11:35",1,"C","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("11:40-12:50",1,"D Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("1:40-2:50",1,"E Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("2:55-3:45",1,"F","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("4:15-5:05",1,"G","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("5:10-6:00",1,"Q","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    adapter = new addOLSAdapter(getActivity(),addOLSList);
                    recyclerView.setAdapter(adapter);
                }//week 1 Monday
                if (i==2){//week 1 Tuesday
                    addOLSList.clear();
                    addOLSList.add(
                            new addOLS("8:00-8:50",1, "U", "Your Class", "XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("8:55-9:45",1,"A","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("10:45-11:35",1,"D","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("11:40-12:50",1,"C Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("1:40-2:30",1,"E","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("2:35-3:45",1,"F Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("4:15-5:05",1,"Z","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("5:10-6:00",1,"H","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    adapter = new addOLSAdapter(getActivity(),addOLSList);
                    recyclerView.setAdapter(adapter);
                }//week 1 Tuesday
                if (i==3){
                    addOLSList.clear();
                    addOLSList.add(
                            new addOLS("8:00-8:50",1, "C", "Your Class", "XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("8:55-9:45",1,"B","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("11:05-11:55",1,"E","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("12:00-12:50",1,"G","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    adapter = new addOLSAdapter(getActivity(),addOLSList);
                    recyclerView.setAdapter(adapter);
                }//week 1 Wednesday
                if (i==4){
                    addOLSList.clear();
                    addOLSList.add(
                            new addOLS("8:00-8:50",1, "V", "Your Class", "XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("8:55-9:45",1,"D","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("10:45-11:35",1,"A","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("11:40-12:50",1,"B Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("1:40-2:30",1,"X","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("2:35-3:25",1,"F","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("3:55-4:45",1,"G","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("4:50-6:00",1,"H Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    adapter = new addOLSAdapter(getActivity(),addOLSList);
                    recyclerView.setAdapter(adapter);
                }//week 1 Thursday
                if (i==6){ //week 2 Monday
                    addOLSList.clear();
                    addOLSList.add(
                            new addOLS("8:00-8:50",1, "U", "Your Class", "XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("8:55-9:45",1,"A","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("10:45-11:35",1,"D","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    addOLSList.add(
                            new addOLS("11:40-12:50",1,"C Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("1:40-2:30",1,"E","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("2:35-3:45",1,"F Long","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("4:15-5:05",1,"Z","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );

                    addOLSList.add(
                            new addOLS("5:10-6:00",1,"Q","Your Class","XYZ-###",
                                    "XYZ-###", "Your teacher")
                    );
                    adapter = new addOLSAdapter(getActivity(),addOLSList);
                    recyclerView.setAdapter(adapter);
                } //week 2 Monday
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return root;
    }
}