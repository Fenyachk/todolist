package data.repository;

import data.model.Task;

import java.time.LocalDate;
import java.util.Map;

public interface IRepo {

    void insertTask(Task task);

    void updateTask(Task task);

    void deleteTask(int taskId);

    Map<Integer, Task> getAllTask();

    Map<Integer, Task> getTaskById(int taskId);

    Map<Integer, Task> getTaskByDay(LocalDate date);


}
