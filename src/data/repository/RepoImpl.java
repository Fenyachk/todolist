//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.repository;

import data.database.FakeDatabase;
import data.model.Task;

import java.time.LocalDate;
import java.util.Map;

public class RepoImpl implements IRepo {
    private final FakeDatabase databaseConnector = new FakeDatabase();

    public RepoImpl() {
    }

    public void insertTask(Task task) {
        this.databaseConnector.insert(task);
    }

    public void updateTask(Task task) {
        this.databaseConnector.update(task);
    }

    public void deleteTask(int taskId) {
        this.databaseConnector.delete(taskId);
    }

    public Map<Integer, Task> getAllTask() {
        return this.databaseConnector.getAllTask();
    }

    public Map<Integer, Task> getTaskById(int taskId) {
        return this.databaseConnector.getTaskById(taskId);
    }

    public Map<Integer, Task> getTaskByDay(LocalDate date) {
        return this.databaseConnector.getTaskByDay(date);
    }
}
