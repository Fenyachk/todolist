package data.database;

import data.database.FakeDatabaseConnection;
import data.model.Task;

import java.util.Map;

public class DatabaseHelper {

    private final FakeDatabaseConnection databaseConnector;

    public DatabaseHelper() {
        this.databaseConnector = new FakeDatabaseConnection();
    }

    public void insertTask(Task task) {
        databaseConnector.insert(task);
    }

    public Map<Integer, Task> getAllTask() {
        return databaseConnector.getDatabase();
    }
}
