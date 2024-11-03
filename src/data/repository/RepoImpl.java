package data.repository;

import data.database.FakeDatabase;
import data.model.Task;

import java.util.Map;

public class RepoImpl implements IRepo {

    private final FakeDatabase databaseConnector;

    public RepoImpl() {
        this.databaseConnector = new FakeDatabase();
    }

    @Override
    public void insertTask(Task task) {
        databaseConnector.insert(task);
    }

    @Override
    public void updateTask(Task task) {
        databaseConnector.update(task);
    }

    @Override
    public void deleteTask(int taskId) {
        databaseConnector.delete(taskId);
    }

    @Override
    public Map<Integer, Task> getAllTask() {
        return databaseConnector.getAllTask();
    }

    @Override
    public Map<Integer, Task> getTaskById(int taskId) {
        return databaseConnector.getTaskById(taskId);
    }

}
