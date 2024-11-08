package ui.viewmodel;

import data.model.Task;

import java.time.LocalDate;
import java.util.Map;

public interface TaskViewModelInterface {

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(int taskId);

    Map<Integer, Task> getTask(int taskId);

    Map<Integer, Task> getAllTask();

    Map<Integer, Task> getTaskByDate(LocalDate date);

}
