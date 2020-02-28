package com.example.connexeter.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    DashboardViewModel dashboardViewModel;

    RecyclerView recyclerView;//for printing ols info
    addOLSAdapter adapter;
    List<addOLS> addOLSList;

    RecyclerView inputClassRecyclerView; //for user input of ols info
    inputClassAdapter adapterOLSInput;
    List<inputClass> inputClassList;



//    private RecyclerView className;
//    private RecyclerView classLevel;
//    private RecyclerView roomNumber;
//    private RecyclerView teacherName;
//    private RecyclerView format;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//input ols thing  THIS NEEDS TO BE INITIALIZED IN A NEW WINDOW AFTER HITTING A BUTTON
        inputClassList = new ArrayList<>();
        inputClassRecyclerView = (RecyclerView) root.findViewById(R.id.inputClassRecyclerView);
        inputClassRecyclerView.setHasFixedSize(true);
        inputClassRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        inputClassList.add(new inputClass("A Format Class:"));
        inputClassList.add(new inputClass("B Format Class"));
        inputClassList.add(new inputClass("C Format Class"));
        inputClassList.add(new inputClass("D Format Class"));
        inputClassList.add(new inputClass("E Format Class"));
        inputClassList.add(new inputClass("F Format Class"));
        inputClassList.add(new inputClass("G Format Class"));
        inputClassList.add(new inputClass("H Format Class"));



//printing ols thing
        addOLSList = new ArrayList<>();

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addOLSList.add(
                new addOLS(1, "A", "Math", "MAT-420",
                        "ACB-220", "Mr.Chen")
        );
        addOLSList.add(
                new addOLS(1,"B","Biology","BIO-320",
                        "PHP-302", "Mr. Matlack")
        );
        addOLSList.add(
                new addOLS(1,"C","English","ENG-420",
                        "ACB-202", "Mr. Griffith")
        );
        addOLSList.add(
                new addOLS(1,"D","Japanese","JAP-120",
                        "ACB-402", "Ms. Tazawa")
        );
        adapter = new addOLSAdapter(getActivity(),addOLSList);
        recyclerView.setAdapter(adapter);

        //date and time
        final String currentDate = new SimpleDateFormat("MMM, dd, yyyy", Locale.getDefault()).format(new Date());
        final TextView date = root.findViewById(R.id.date);
        date.setText(currentDate);

        //dropdown menu
        Spinner setWeekNumber = (Spinner) root.findViewById(R.id.weekNumber);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.reply_entries));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setWeekNumber.setAdapter(myAdapter);


        return root;
    }

    public void confirmButton(View v){

    }
}
