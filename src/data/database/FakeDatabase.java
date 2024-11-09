//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.database;

import data.model.Task;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FakeDatabase implements DatabaseConnection {
    private final Map<Integer, Task> database = new HashMap();
    private boolean isConnected = false;

    public FakeDatabase() {
        this.database.put(1, new Task(1, "Закончить отчет", "22.11.2024 11:14", 2));
        this.database.put(2, new Task(2, "Посетить командное собрание", "21.11.2024 09:11", 1));
        this.database.put(3, new Task(3, "Сходить за продуктами", "23.11.2024 15:30", 3));
        this.database.put(4, new Task(4, "Забронировать билеты на самолет", "29.11.2024 10:05", 1));
        this.database.put(5, new Task(5, "Позвонить в банк", "27.10.2024 16:45", 2));
        this.database.put(6, new Task(6, "Позвонить на работу", "12.10.2024 16:45", 5));
        this.database.put(7, new Task(7, "Позвонить на работу2", "12.10.2024 16:45", 5));
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
        int newId = this.database.isEmpty() ? 1 : (Integer) Collections.max(this.database.keySet()) + 1;
        task.setId(newId);
        System.out.println(this.database);
        this.database.put(newId, task);
    }

    public void update(Task task) {
        int taskId = task.getId();
        this.database.put(taskId, task);
    }

    public void delete(int taskId) {
        this.database.remove(taskId);
    }

    public Map<Integer, Task> getAllTask() {
        return this.database;
    }

    public Map<Integer, Task> getTaskById(int taskId) {
        return Map.of();
    }

    public Map<Integer, Task> getTaskByDay(LocalDate date) {
        Map<Integer, Task> tasksOnDate = new HashMap();
        Iterator var3 = this.database.entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<Integer, Task> entry = (Map.Entry) var3.next();
            Task task = (Task) entry.getValue();
            if (task.getDate().toLocalDate().equals(date)) {
                tasksOnDate.put((Integer) entry.getKey(), task);
            }
        }

        return tasksOnDate;
    }
}
