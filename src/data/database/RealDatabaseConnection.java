package data.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.auth.oauth2.GoogleCredentials;
import data.model.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RealDatabaseConnection implements DatabaseConnection {
    private static final String DATABASE_URL = "../.../../config/todolist-ab5ae-firebase-adminsdk-mtvk2-4ec34dbe8f.json";
    private Map<Integer, Task> database;

    @Override
    public void connect() {
        try {
            FileInputStream refreshToken = new FileInputStream(DATABASE_URL);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
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
    public Map<Integer, Task> getDatabase() {
        return database;
    }

}
