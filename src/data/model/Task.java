package data.model;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String date;
    private int priority;

    public Task(int id, String name, String date, int priority) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
