package com.example.connexeter.ui.notifications;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event {

    private String title, description, startTime, endTime, date;
    private int id;
    SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat t = new SimpleDateFormat("h:mm a");

    //Event constructor for events that have no specified or known ending time
    public Event(int id, String title, String description, String startTime, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.date = date;
        this.endTime = "";
    }

    //Event constructor for events that have a specified or known ending time
    public Event(int id, String title, String description, String startTime, String endTime, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    //setters and gettings for event attributes
    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
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

    //gets start time in Unix time, or Epoch Time
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

    //gets start date in Unix time, or Epoch Time
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
