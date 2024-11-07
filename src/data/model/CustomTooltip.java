package data.model;

import ui.viewmodel.TaskViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTooltip {

    private int year;
    private int month;
    private int day;
    private String hour;
    private String minute;
    private String title;
    private static final Map<LocalDate, CustomTooltip> tooltips = new HashMap<>();
    private int priority;
    private static final String TIME_FORMAT = "dd.MM.yyyy HH:mm";
    private static final String DATE_FORMAT = "%02d";

    public CustomTooltip() {
    }

    public CustomTooltip(Task task) {
        String date = task.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        this.year = dateTime.getYear();
        this.month = dateTime.getMonthValue();
        this.day = dateTime.getDayOfMonth();
        this.hour = String.format(DATE_FORMAT, dateTime.getHour());
        this.minute = String.format(DATE_FORMAT, dateTime.getMinute());
        this.priority = task.getPriority();
        this.title = task.getName() + " в " + this.hour + ":" + this.minute;

    }

    public CustomTooltip(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public Map<LocalDate, CustomTooltip> getAllTooltips() {
        TaskViewModel taskModel = new TaskViewModel();
        Map<Integer, Task> tasks = taskModel.getAllTask();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task task = entry.getValue();
            String name = task.getName();
            if (name != null) {
                LocalDateTime dateTime = LocalDateTime.parse(task.getDate(), formatter);
                LocalDate date = LocalDate.parse(task.getDate(), formatter);
                CustomTooltip customTooltip = new CustomTooltip(task);
                tooltips.put(date, customTooltip);
            }
        }
        return tooltips;
    }

    public List<CustomTooltip> getTaskByDate() {
        LocalDate date = LocalDate.of(this.year, this.month + 1, this.day);
        return parseTasksByDate(date);
    }

    private List<CustomTooltip> parseTasksByDate(LocalDate targetDate) {
        Map<LocalDate, CustomTooltip> allTooltips = getAllTooltips();
        List<CustomTooltip> tasksForDate = new ArrayList<>();

        for (Map.Entry<LocalDate, CustomTooltip> entry : allTooltips.entrySet()) {
            if (entry.getKey().equals(targetDate)) {
                CustomTooltip tooltipValue = entry.getValue();
                if (tooltipValue != null) {
                    tasksForDate.add(tooltipValue);
                }
            }
        }
        return tasksForDate;
    }
}
