package ui.viewmodel;

import data.model.Task;
import data.database.DatabaseHelper;

import java.util.List;
import java.util.Map;

public class TaskViewModel {
    private final DatabaseHelper dbConnection;

    public TaskViewModel() {
        this.dbConnection = new DatabaseHelper();
    }

    public void addTask(Task task) {
        dbConnection.insertTask(task);
    }

    public Map<Integer, Task> getAllTask() {
        return dbConnection.getAllTask();
    }

}
