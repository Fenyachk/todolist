//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui.viewmodel;

import data.model.Task;
import data.repository.RepoImpl;

import java.time.LocalDate;
import java.util.Map;

public class TaskViewModel {
    private static final RepoImpl repoImpl = new RepoImpl();

    public TaskViewModel() {
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
