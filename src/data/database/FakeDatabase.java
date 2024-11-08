package data.database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import data.model.Task;

public class FakeDatabase implements DatabaseConnection {

    private final Map<Integer, Task> database;
    private boolean isConnected;

    public FakeDatabase() {
        database = new HashMap<>();
        isConnected = false;
        database.put(1, new Task(1, "Закончить отчет", "22.11.2024 11:14", 2));
        database.put(2, new Task(2, "Посетить командное собрание", "21.11.2024 09:11", 1));
        database.put(3, new Task(3, "Сходить за продуктами", "23.11.2024 15:30", 3));
        database.put(4, new Task(4, "Забронировать билеты на самолет", "29.11.2024 10:05", 1));
        database.put(5, new Task(5, "Позвонить в банк", "27.11.2024 16:45", 2));
        database.put(6, new Task(5, "Позвонить на работу", "12.11.2024 16:45", 5));
    }

    @Override
    public void connect() {
        if (!isConnected) {
            System.out.println("Подключение к базе данных...");
            isConnected = true;
        } else {
            System.out.println("Уже подключен к базе данных");
        }
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            System.out.println("Отключение от базы данных...");
            isConnected = false;
        } else {
            System.out.println("Уже отключен от базы данных");
        }
    }

    @Override
    public void insert(Task task) {
        int newId = database.isEmpty() ? 1 : Collections.max(database.keySet()) + 1;
        database.put(newId, task);
    }

    @Override
    public void update(Task task) {
        int taskId = task.getId();
        database.put(taskId, task);
    }

    @Override
    public void delete(int taskId) {
        database.remove(taskId);
    }

    @Override
    public Map<Integer, Task> getAllTask() {
        return database;
    }

    @Override
    public Map<Integer, Task> getTaskById(int taskId) {
        return Map.of();
    }

    @Override
    public Map<Integer, Task> getTaskByDay(LocalDate date) {
        Map<Integer, Task> tasksOnDate = new HashMap<>();
        for (Map.Entry<Integer, Task> entry : database.entrySet()) {
            Task task = entry.getValue();
            if (task.getDate().toLocalDate().equals(date)) {
                tasksOnDate.put(entry.getKey(), task);
            }
        }
        return tasksOnDate;
    }
}
