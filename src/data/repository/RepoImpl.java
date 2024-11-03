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
    public void deleteTask(Task task) {
        databaseConnector.delete(task);
    }

    @Override
    public void getTask() {

    }
}
