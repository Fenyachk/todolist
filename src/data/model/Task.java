//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String hour;
    private String minute;
    private int id;
    private String name;
    private String date;
    private int priority;
    private static final String DATE_FORMAT = "%02d";
    private static final String TIME_FORMAT = "dd.MM.yyyy HH:mm";

    public Task(String name, String date, int priority) {
        this(0, name, date, priority);
    }

    public Task(int id, String name, String date, int priority) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.priority = priority;
        setDateFormat(date);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return LocalDateTime.parse(this.date, formatter);
    }

    public int getPriority() {
        return this.priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
        setDateFormat(date);
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return this.name + " в " + this.hour + ":" + this.minute;
    }

    public void setDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        LocalDateTime dateTime = LocalDateTime.parse(this.date, formatter);
        this.hour = String.format(DATE_FORMAT, dateTime.getHour());
        this.minute = String.format(DATE_FORMAT, dateTime.getMinute());
    }
}
