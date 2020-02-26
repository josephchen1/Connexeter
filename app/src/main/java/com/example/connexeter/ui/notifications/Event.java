package com.example.connexeter.ui.notifications;


import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event {

    private String title, description, startTime, endTime, date;
    SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat t = new SimpleDateFormat("h:mm a");

    public Event(String title, String description, String startTime, String date) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.date = date;
        this.endTime = "";
    }

    public Event(String title, String description, String startTime, String endTime, String date) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
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
