package data.repository;

import data.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface IRepo {

    void insertTask(Task task);

    void updateTask(Task task);

    void deleteTask(int taskId);

    List<Task> getAllTask();

    List<Task> getTaskById(int taskId);

    List<Task> getTaskByDay(LocalDate date);


}
