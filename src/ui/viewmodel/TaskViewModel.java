package ui.viewmodel;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import data.model.Task;
import data.database.FakeDatabaseConnection;

public class TaskViewModel {

    private final FakeDatabaseConnection dbConnection;

    public TaskViewModel() {
        this.dbConnection = new FakeDatabaseConnection();
    }

    private void displayTasksOnCalendar(JCalendar calendar) {
        Map<Integer, Task> tasks = dbConnection.getAllTasks();
        for (Task task : tasks.values()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            try {
                Date taskDate = formatter.parse(task.getDate());
                System.out.println("Задача: " + task.getName() + " на " + taskDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Integer, Task> getAllTasks() {
        //return dbConnection;
    }
}
