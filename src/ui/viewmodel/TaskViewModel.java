package ui.viewmodel;

import data.model.Task;
import data.repository.RepoImpl;

import java.time.LocalDate;
import java.util.Map;

public class TaskViewModel {
    private final RepoImpl repoImpl;

    public TaskViewModel() {
        this.repoImpl = new RepoImpl();
    }

    public void addTask(Task task) {
        repoImpl.insertTask(task);
    }

    public void updateTask(Task task) {
        repoImpl.updateTask(task);
    }

    public void deleteTask(int taskId) {
        repoImpl.deleteTask(taskId);
    }

    public Map<Integer, Task> getTask(int taskId) {
        return repoImpl.getTaskById(taskId);
    }

    public Map<Integer, Task> getAllTask() {
        return repoImpl.getAllTask();
    }

    public Map<Integer, Task> getTaskByDate(LocalDate date) {
        return repoImpl.getTaskByDay(date);
    }

}
