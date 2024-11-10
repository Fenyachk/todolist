package data.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.auth.oauth2.GoogleCredentials;
import data.model.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RealDatabase implements DatabaseConnection {
    private static final String DATABASE_URL = "../.../../config/todolist-ab5ae-firebase-adminsdk-mtvk2-4ec34dbe8f.json";
    private List<Task> database;

    @Override
    public void connect() {
        try {
            FileInputStream refreshToken = new FileInputStream(DATABASE_URL);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(refreshToken))
                    .setDatabaseUrl("https://todolist-ab5ae.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
        }
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void insert(Task task) {
    }

    @Override
    public void update(Task task) {
    }

    @Override
    public void delete(int taskId) {
    }

    @Override
    public List<Task> getAllTask() {
        return database;
    }

    @Override
    public List<Task> getTaskById(int taskId) {
        return database;
    }

    @Override
    public List<Task> getTaskByDay(LocalDate date) {
        return database;
    }

}
