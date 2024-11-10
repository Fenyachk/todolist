//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.database;

import data.model.Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FakeDatabase implements DatabaseConnection {
    private final List<Task> database = new ArrayList<>();
    private boolean isConnected = false;

    public FakeDatabase() {
        this.database.add(new Task(1, "Закончить отчет", "22.11.2024 11:14", 2));
        this.database.add(new Task(2, "Посетить командное собрание", "21.11.2024 09:11", 1));
        this.database.add(new Task(3, "Сходить за продуктами", "23.11.2024 15:30", 3));
        this.database.add(new Task(4, "Забронировать билеты на самолет", "29.11.2024 10:05", 1));
        this.database.add(new Task(5, "Позвонить в банк", "27.10.2024 16:45", 2));
        this.database.add(new Task(6, "Позвонить на работу", "12.10.2024 16:45", 5));
        this.database.add(new Task(7, "Позвонить на работу2", "12.10.2024 16:45", 5));
    }

    public void connect() {
        if (!this.isConnected) {
            System.out.println("Подключение к базе данных...");
            this.isConnected = true;
        } else {
            System.out.println("Уже подключен к базе данных");
        }

    }

    public void disconnect() {
        if (this.isConnected) {
            System.out.println("Отключение от базы данных...");
            this.isConnected = false;
        } else {
            System.out.println("Уже отключен от базы данных");
        }

    }

    public void insert(Task task) {
        int newId = database.isEmpty() ? 1 : database.stream()
                .map(Task::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;
        task.setId(newId);
        this.database.add(task);
    }

    public void update(Task task) {
        this.database.add(task);
    }

    public void delete(int taskId) {
        database.removeIf(task -> task.getId() == taskId);
        System.out.println(database);
    }

    public List<Task> getAllTask() {
        return this.database;
    }

    public List<Task> getTaskById(int taskId) {
        return database.stream()
                .filter(task -> task.getId() == taskId)
                .collect(Collectors.toList());
    }

    public List<Task> getTaskByDay(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : database) {
            if (task.getDate().toLocalDate().equals(date)) {
                tasksOnDate.add(task);
            }
        }

        return tasksOnDate;
    }
}
