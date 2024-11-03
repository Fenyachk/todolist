package ui.viewmodel;

import data.model.Task;
import data.repository.RepoImpl;

import java.util.Map;

public class TaskViewModel {
    private final RepoImpl dbConnection;

    public TaskViewModel() {
        this.dbConnection = new RepoImpl();
    }

    public void addTask(Task task) {
        dbConnection.insertTask(task);
    }

    public Map<Integer, Task> getAllTask() {
        return dbConnection.getAllTask();
    }

}
