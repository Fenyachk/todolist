package data.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;

public class RealDatabaseConnection implements DatabaseConnection {
    private static final String DATABASE_URL = "../.../../config/todolist-ab5ae-firebase-adminsdk-mtvk2-4ec34dbe8f.json";

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

}
