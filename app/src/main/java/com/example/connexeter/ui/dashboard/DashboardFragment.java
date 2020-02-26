package com.example.connexeter.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    DashboardViewModel dashboardViewModel;
    ArrayList<addOLS> formatList;
    private RecyclerView className;
    private RecyclerView classLevel;
    private RecyclerView roomNumber;
    private RecyclerView teacherName;
    private RecyclerView format;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView allFormats = root.findViewById(R.id.allFormats);
        allFormats.setLayoutManager(new LinearLayoutManager(getActivity()));

        OlsAdapter adapter = new OlsAdapter(OlsAdapter.allFormats);
        allFormats.setAdapter(adapter);

        final String currentDate = new SimpleDateFormat("MMM, dd, yyyy", Locale.getDefault()).format(new Date());
        final TextView date = root.findViewById(R.id.date);
        date.setText(currentDate);

        Spinner setWeekNumber = (Spinner) root.findViewById(R.id.weekNumber);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.reply_entries));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setWeekNumber.setAdapter(myAdapter);

//        setWeekNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()) {
//            @Override
//                    public void onItemSelectedListener(AdapterView<?> adapterView, View view, int i, long l);
//        }
        return root;
    }

    private ArrayList<addOLS> allClasses() {
        ArrayList<addOLS> list = new ArrayList<>();
        list.add(new addOLS(1, "A", "Math", "MAT-202",
                "ACA323", "Dylan"));
        list.add(new addOLS(1, "B", "English", "ENG-520",
                "ACB-204", "Shen"));

        return list;
    }


}