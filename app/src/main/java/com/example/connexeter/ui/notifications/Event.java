package com.example.connexeter.ui.notifications;


import android.widget.ToggleButton;


import com.example.connexeter.R;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event {

    private String title, description, startTime, endTime, date;
    private int id;
    private boolean toggle;
    ToggleButton toggleNotif;
    SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat t = new SimpleDateFormat("h:mm a");



    public Event(int id, String title, String description, String startTime, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.date = date;
        this.endTime = "";
        this.toggle = false;
    }

    public Event(int id, String title, String description, String startTime, String endTime, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.toggle = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getToggle () {
        return toggle;
    }

    public void setToggle(boolean f) {
        this.toggle = f;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public long getStartTimeMS() {
        try {
            Date y = t.parse(startTime);
            return y.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public long getDateMS() {
        try {
            Date d = f.parse(date);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
