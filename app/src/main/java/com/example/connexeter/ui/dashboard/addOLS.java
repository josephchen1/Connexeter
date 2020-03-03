package com.example.connexeter.ui.dashboard;

public class addOLS {
    private int weekNumber;
    private String time;
    private String format;
    private String className;
    private String classLevel;
    private String roomNumber;
    private String teacherName;

    public addOLS(String time, int weekNumber, String format, String className, String classLevel, String roomNumber, String teacherName) {
        this.time = time;
        this.weekNumber = weekNumber;
        this.format = format;
        this.className = className;
        this.classLevel = classLevel;
        this.roomNumber = roomNumber;
        this.teacherName = teacherName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}