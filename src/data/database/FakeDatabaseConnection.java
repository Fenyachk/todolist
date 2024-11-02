package data.database;

import java.util.HashMap;
import java.util.Map;
import data.model.Task;


public class FakeDatabaseConnection implements DatabaseConnection {

    private final Map<Integer, Task> database;
    private boolean isConnected;

    public FakeDatabaseConnection() {
        database = new HashMap<>();
        isConnected = false;

        database.put(1, new Task(1, "Finish report", "22.11.2024 11:14", 2));
        database.put(2, new Task(2, "Attend team meeting", "22.11.2024 09:00", 1));
        database.put(3, new Task(3, "Grocery shopping", "23.11.2024 15:30", 3));
        database.put(4, new Task(4, "Book flight tickets", "29.11.2024 10:00", 1));
        database.put(5, new Task(5, "Call the bank", "27.11.2024 16:45", 2));
    }

    @Override
    public void connect() {
        if (!isConnected) {
            System.out.println("Подключение к базе данных...");
            isConnected = true;
        } else {
            System.out.println("Уже подключен к базе данных");
        }
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            System.out.println("Отключение от базы данных...");
            isConnected = false;
        } else {
            System.out.println("Уже отключен от базы данных");
        }
    }

    @Override
    public void insert(Task task) {
        database.put(task.getId(), task);
    }

    @Override
    public Map<Integer, Task> getDatabase() {
        return database;
    }

}
