package data.database;

import java.util.Map; // Импортируем класс Map

import data.model.Task;

public interface DatabaseConnection {
    void connect();

    void disconnect();

    void insert(Task task);

    void update(Task task);

    void delete(int taskId);

    Map<Integer, Task> getAllTask();

    Map<Integer, Task> getTaskById(int taskId);
}
