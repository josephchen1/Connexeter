package com.example.connexeter.ui.dashboard.ChangeFormatValues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.connexeter.MainActivity;
import com.example.connexeter.R;

import java.util.ArrayList;
import java.util.List;

public class addFormat {
    private String formatLetter;
    private String className, classLevel, classRoom, teacher;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setFormatLetter(String formatLetter) {
        this.formatLetter = formatLetter;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public addFormat(String formatLetter) {
        this.formatLetter = formatLetter;
    }

    public String getFormatLetter() {
        return formatLetter;
    }

    public addFormat(String formatLetter, String className, String classLevel, String classRoom, String teacher) {
        this.formatLetter = formatLetter;

        this.className = className;
        this.classRoom = classRoom;
        this.classLevel = classLevel;
        this.teacher = teacher;
    }
}