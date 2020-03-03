package com.example.connexeter.ui.dashboard.ChangeFormatValues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.connexeter.R;

import java.util.ArrayList;
import java.util.List;

public class EditClasses extends AppCompatActivity {

    RecyclerView recyclerView;
    addFormatAdapter adapter;

    public String[] classLevel = new String[8];
    public String[] classRoom =new String[8];
    public String[] teacher =new String[8];
    public String[] className = new String[8];

    //    public String classNameA, classLevelA, classRoomA, teacherA;
    EditText classNameAInput, classLevelAInput, classRoomAInput, teacherAInput;
    Button submitButton;

    public static List<addFormat> addFormatList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_format);

        addFormatList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.inputClassRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addFormatList.add(new addFormat("A Format"));
        addFormatList.add(new addFormat("B Format"));
        addFormatList.add(new addFormat("C Format"));
        addFormatList.add(new addFormat("D Format"));
        addFormatList.add(new addFormat("E Format"));
        addFormatList.add(new addFormat("F Format"));
        addFormatList.add(new addFormat("G Format"));
        addFormatList.add(new addFormat("H Format"));

        recyclerView = (RecyclerView) findViewById(R.id.inputClassRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classNameAInput = (EditText) findViewById(R.id.aFormatInput);
        classLevelAInput = (EditText) findViewById(R.id.inputClassLevel);
        classRoomAInput = (EditText) findViewById(R.id.inputRoomNumber);
        teacherAInput = (EditText) findViewById(R.id.inputTeacherName);

        for (int i = 0; i < addFormatList.size(); i++) {
            addFormatList.get(i).setClassName("Sample text");
            addFormatList.get(i).setClassLevel("sample text");
            addFormatList.get(i).setClassRoom("sample text");
            addFormatList.get(i).setTeacher("sample text");
        }

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < addFormatList.size(); i++) {
                    if (addFormatList.get(i).getFormatLetter().equals("A")) {
                        className[0] = classNameAInput.getText().toString();
                        classLevel[0] = classLevelAInput.getText().toString();
                        classRoom[0] = classRoomAInput.getText().toString();
                        teacher[0] = teacherAInput.getText().toString();
                    }
                }
            }
        });
        adapter = new addFormatAdapter(this, addFormatList);
        recyclerView.setAdapter(adapter);
    }
}