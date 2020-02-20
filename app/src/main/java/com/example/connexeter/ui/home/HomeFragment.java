package com.example.connexeter.ui.home;

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


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.connexeter.MainActivity;
import com.example.connexeter.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private EditText itemET;
    private Button btn;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


    @Override
    public View onCreateView (@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn = (Button) view.findViewById(R.id.add_btn);
        itemET = view.findViewById(R.id.item_edit_text);
        itemsList = view.findViewById(R.id.items_list);

        items = FileHelper.readData(getActivity());

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);

        itemsList.setAdapter(adapter);

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.add_btn:
                        String itemEntered = itemET.getText().toString();
                        adapter.add(itemEntered);
                        itemET.setText("");

                        FileHelper.writeData(items, getActivity());

                        Toast.makeText(getActivity(), "Item Added", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }



}