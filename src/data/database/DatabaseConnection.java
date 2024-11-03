package data.database;

import java.util.Map; // Импортируем класс Map

import data.model.Task;

public interface DatabaseConnection {
    void connect();

    void disconnect();

    void insert(Task task);

    void update(Task task);

    void delete(Task task);

    Map<Integer, Task> getTask();
}
