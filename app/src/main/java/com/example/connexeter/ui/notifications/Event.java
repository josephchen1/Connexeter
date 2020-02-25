package com.example.connexeter.ui.notifications;


import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event {

    private String title, description, time, date;
    SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");

    public Event(String title, String description, String time, String date) {
        this.title=title;
        this.description=description;
        this.time=time;
        this.date=date;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
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
