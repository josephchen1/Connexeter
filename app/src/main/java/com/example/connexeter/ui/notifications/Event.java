package com.example.connexeter.ui.notifications;

public class Event {

    private int id;
    private String title, description, time, date;


    public Event(int id, String title, String description, String time, String date) {
        this.id=id;
        this.title=title;
        this.description=description;
        this.time=time;
        this.date=date;
    }

    public int getId() {
        return id;
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
}
