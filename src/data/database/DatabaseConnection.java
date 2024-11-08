package data.database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import data.model.Task;

public interface DatabaseConnection {
    void connect();

    void disconnect();

    void insert(Task task);

    void update(Task task);

    void delete(int taskId);

    Map<Integer, Task> getAllTask();

    Map<Integer, Task> getTaskById(int taskId);

    Map<Integer, Task> getTaskByDay(LocalDate date);
}
