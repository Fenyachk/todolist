package data.database;

import java.time.LocalDate;
import java.util.List;

import data.model.Task;

public interface DatabaseConnection {
    void connect();

    void disconnect();

    void insert(Task task);

    void update(Task task);

    void delete(int taskId);

    List<Task> getAllTask();

    List<Task> getTaskById(int taskId);

    List<Task> getTaskByDay(LocalDate date);
}
