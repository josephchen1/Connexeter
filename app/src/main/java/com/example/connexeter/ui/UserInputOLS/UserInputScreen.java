package com.example.connexeter.ui.UserInputOLS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.connexeter.R;

import java.util.ArrayList;
import java.util.List;

public class UserInputScreen extends Fragment {

    UserInputScreenViewModel userInputScreenViewModel;
    RecyclerView inputClassRecyclerView; //for user input of ols info
    inputClassAdapter adapterOLSInput;
    List<inputClass> inputClassList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        userInputScreenViewModel = ViewModelProviders.of(this).get(UserInputScreenViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

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

        adapterOLSInput = new inputClassAdapter(getActivity(),inputClassList);
        inputClassRecyclerView.setAdapter(adapterOLSInput);
        return root;
    }
}
