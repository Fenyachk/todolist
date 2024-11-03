package data.repository;

import data.model.Task;

public interface IRepo {

    void insertTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    void getTask();
}
